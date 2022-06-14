package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.EdicaoUCAlunoDTO;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.UtilizadorDTO;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCAlunoDTOMapper;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCDTOMapper;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import com.grupo2.edicaouc.repository.EdicaoUCAlunoRepository;
import com.grupo2.edicaouc.repository.EdicaoUCRepository;
import com.grupo2.edicaouc.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de Service de EdicaoUC. Possuí endpoints para findAllEdicaoByUCCode,createEdicaoUC e findById
 */
@Service
public class EdicaoUCService
{
    /**
     * O repository a ser utilizado por este Service.
     */
    @Autowired
    private EdicaoUCRepository repository;
    /**
     * O mapper a ser utilizado por este Service.
     */
    @Autowired
    private EdicaoUCDTOMapper mapper;

    @Autowired
    private UnidadeCurricularService ucService;

    @Autowired
    private AnoLetivoService anoLetivoService;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

    @Autowired
    private EdicaoUCAlunoRepository edicaoUCAlunoRepository;

    @Autowired
    private EdicaoUCAlunoDTOMapper edicaoUCAlunoDTOMapper;

    /**
     * Endpoint que possibilita encontrar o EdicaoUC por ucCode existente.
     *
     * @param ucCode um objeto com os dados do ucCode
     * @return uma EdicaoUC
     */
    public List<EdicaoUCDTO> findAllEdicaoByUCCode(String ucCode)
    {

        List<EdicaoUC> opEdicaoUC = repository.findAllEdicaoByUCCode(ucCode);

        return opEdicaoUC.stream().map(mapper::toDTO).toList();
    }

    /**
     * Endpoint que possibilita criar e gravar uma EdicaoUC
     *
     * @param dto objeto com dadtos
     * @return nova EdicaoUC
     */
    public EdicaoUCDTO createEdicaoUC(EdicaoUCDTO dto)
    {

        if (ucService.findBySigla(dto.getUcCode()).isEmpty())
        {
            throw new OptionalVazioException("Não existe Unidade Curricular com esse id " + dto.getUcCode());
        }

        if (anoLetivoService.findByID(dto.getAnoLetivoCode()).isEmpty())
        {
            throw new OptionalVazioException("Não existe Ano Letivo com esse id " + dto.getAnoLetivoCode());
        }

        if (!utilizadorRestRepository.isRole("ROLE_DOCENTE", dto.getRucID()))
        {
            throw new ErroGeralException(dto.getRucID() + " não é um docente");
        }
        EdicaoUC edicaoUC = mapper.toModel(dto);
        EdicaoUC saveEdicaoUC = repository.saveEdicaoUC(edicaoUC);

        return mapper.toDTO(saveEdicaoUC);
    }

    /**
     * Endpoint que possibilita encontrar o EdicaoUC por id existente.
     *
     * @param id um objeto com os dados do id
     * @return uma EdicaoUC
     */
    public Optional<EdicaoUCDTO> findById(Long id)
    {
        Optional<EdicaoUC> edicao = repository.findById(id);

        if (edicao.isPresent())
        {
            return Optional.of(mapper.toDTO(edicao.get()));
        }

        return Optional.empty();
    }

    /**
     * Encontrar todas as EdicaoUC
     *
     * @return Lista de EdicaoUC
     */
    public List<EdicaoUCDTO> findAllEdicaoUC()
    {
        List<EdicaoUC> edicaoUC = repository.findAll();

        return edicaoUC.stream().map(mapper::toDTO).toList();
    }

    public EdicaoUCAlunoDTO addAlunoEdicaoUC(UtilizadorDTO dtoPostedRequest, Long edicaoUCID, Long alunoID)
    {
        Optional<EdicaoUC> edicao = repository.findById(edicaoUCID);

            if (edicao.isEmpty())
            {
                throw new OptionalVazioException("EdiçãoUC com esse " + edicaoUCID + " não existe.");
            }

            if (!edicao.get().getRucID().equals(dtoPostedRequest.getId()))
            {
                throw new ErroGeralException(dtoPostedRequest.getId() + " não é RUC desta edição.");
            }

            if (!utilizadorRestRepository.isRole("ROLE_ALUNO", alunoID))
            {
                throw new ErroGeralException(alunoID + " não é um aluno.");
            }

        Boolean isAvailable = edicaoUCAlunoRepository.isStudentAvailable(alunoID);

        if (!isAvailable)
        {
            throw new ErroGeralException(alunoID + " já está inscrito noutra EdiçãoUC.");
        }

        EdicaoUCAluno saved = edicaoUCAlunoRepository.saveEdicaoUCAluno(edicaoUCID, alunoID);

        return edicaoUCAlunoDTOMapper.toDTO(saved);
    }

    public EdicaoUCDTO activarEdicao(Long edicaoUCID)
    {
        Optional<EdicaoUC> optionalEdicaoUC = repository.findById(edicaoUCID);

        if (optionalEdicaoUC.isEmpty())
        {
            throw new OptionalVazioException("EdiçãoUC com esse " + edicaoUCID + " não existe.");
        }

        if (optionalEdicaoUC.get().getEstadoEdicaoUC() == EstadoEdicaoUC.ATIVA)
        {
            throw new ErroGeralException(edicaoUCID + " ja esta ativo.");
        }

        if (optionalEdicaoUC.get().getEstadoEdicaoUC() == EstadoEdicaoUC.DESATIVA)
        {
            throw new ErroGeralException(edicaoUCID+" não pode ser ativada.");
        }

        optionalEdicaoUC.get().activateEdicaoUC();

        EdicaoUC saved = repository.ativarEdicao(optionalEdicaoUC.get());

        return mapper.toDTO(saved);
    }

    public EdicaoUCDTO desativarEdicao(Long edicaoUCID)
    {
        Optional<EdicaoUC> optionalEdicaoUC = repository.findById(edicaoUCID);

        if (optionalEdicaoUC.isEmpty())
        {
            throw new OptionalVazioException("EdiçãoUC com esse " + edicaoUCID + " não existe.");
        }

        if (optionalEdicaoUC.get().getEstadoEdicaoUC() == EstadoEdicaoUC.PENDENTE)
        {
            throw new ErroGeralException(edicaoUCID + " não pode ser desativada.");
        }

        if (optionalEdicaoUC.get().getEstadoEdicaoUC() == EstadoEdicaoUC.DESATIVA)
        {
            throw new ErroGeralException(edicaoUCID+" ja esta desativada.");
        }

        optionalEdicaoUC.get().deactivateEdicaoUC();

        EdicaoUC saved = repository.desativarEdicao(optionalEdicaoUC.get());

        return mapper.toDTO(saved);
    }

    public Optional<EdicaoUCDTO> findByRucID(Long rucID)
    {
        Optional<EdicaoUC> edicao = repository.findByRucID(rucID);

        if (edicao.isPresent())
        {
            return Optional.of(mapper.toDTO(edicao.get()));
        }

        return Optional.empty();
    }
}

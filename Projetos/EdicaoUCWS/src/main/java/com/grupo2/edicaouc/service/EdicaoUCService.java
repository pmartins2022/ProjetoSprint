package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.EdicaoUCAlunoDTO;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.UtilizadorDTO;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCAlunoDTOMapper;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCDTOMapper;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import com.grupo2.edicaouc.repository.EdicaoUCAlunoRepository;
import com.grupo2.edicaouc.repository.EdicaoUCRepository;
import com.grupo2.edicaouc.repository.rest.ProjetoRestRepository;
import com.grupo2.edicaouc.repository.rest.UtilizadorRestRepository;
import com.grupo2.edicaouc.security.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

/**
 * Classe de Service de EdicaoUC. Permite a ligação entre o EdicaoUCController e o resto da API
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
    @Autowired
    private ProjetoRestRepository projetoRestRepository;

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

        System.out.println(saveEdicaoUC);

        EdicaoUCDTO edicaoUCDTO = mapper.toDTO(saveEdicaoUC);


        return edicaoUCDTO;
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

    /**
     * Devolve EdicaoUCAlunoDTO após persistência.
     * Cria um objeto do tipo EdicaoUCAlunoDTO que faz a relação de um aluno com a EdiçãoUC onde está inscrito
     * @param dtoPostedRequest Utilizador com login efetuado
     * @param edicaoUCID id da edição a inscrever o aluno
     * @param alunoID id do aluno a ser inscrito
     * @return EdicaoUCAlunoDTO guardada
     */
    public EdicaoUCAlunoDTO addAlunoEdicaoUC(UtilizadorDTO dtoPostedRequest, Long edicaoUCID, Long alunoID)
    {
        Optional<EdicaoUC> optionalEdicaoUC = repository.findById(edicaoUCID);

        if (optionalEdicaoUC.isEmpty())
        {
            throw new OptionalVazioException("EdiçãoUC com esse " + edicaoUCID + " não existe.");
        }

            if (!optionalEdicaoUC.get().getRucID().equals(dtoPostedRequest.getId()))
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

    /**
     * Devolve EdicaoUCDTO em estado ATIVA
     * @param edicaoUCID id da edição a ativar
     * @return EdicaoUCDTO em estado ATIVA
     * @throws ValidationException
     */
    public EdicaoUCDTO activarEdicao(Long edicaoUCID) throws ValidationException
    {

        Optional<EdicaoUC> optionalEdicaoUC = repository.findById(edicaoUCID);

        if (optionalEdicaoUC.isEmpty())
        {
            throw new OptionalVazioException("EdiçãoUC com esse " + edicaoUCID + " não existe.");
        }

        Optional<EdicaoUC> edicaoActive = repository.findByRucIDAndEstadoEdicaoUC(LoginContext.getCurrent().getId(),EstadoEdicaoUC.ATIVA);

        if (edicaoActive.isPresent())
        {
            edicaoActive.get().deactivateEdicaoUC();
            repository.desativarEdicao(edicaoActive.get());
        }

        Optional<EdicaoUC> edicaoToActivate = repository.findById(edicaoUCID);

        if (edicaoToActivate.isEmpty())
        {
            throw new OptionalVazioException("EdiçãoUC com esse " + edicaoUCID + " não existe.");
        }

        if (edicaoToActivate.get().getEstadoEdicaoUC() != EstadoEdicaoUC.PENDENTE)
        {
            throw new ValidacaoInvalidaException("EdiçãoUC não está em fase PENDENTE");
        }

        if (!edicaoToActivate.get().getRucID().equals(LoginContext.getCurrent().getId()))
        {
            throw new ValidacaoInvalidaException("ESSA EDICAO UC NAO E SUA");
        }

        EstadoEdicaoUC original = edicaoToActivate.get().getEstadoEdicaoUC();

        edicaoToActivate.get().activateEdicaoUC();

        EdicaoUC edicaoUC = repository.ativarEdicao(edicaoToActivate.get());

        EdicaoUCDTO edicaoUCDTO = mapper.toDTO(edicaoUC);

        try
        {
            projetoRestRepository.saveEdicaoUC(edicaoUCDTO);
        } catch (Exception e)
        {
            //repository.deleteByID(edicaoUC.getId());
            edicaoToActivate.get().setEstado(original);
            repository.desativarEdicao(edicaoToActivate.get());
            throw new ErroGeralException("Nao conseguiu guardar na DB: "+e.getMessage());
        }

        return mapper.toDTO(edicaoUC);
    }
    /**
     * Devolve EdicaoUCDTO em estado DESATIVA
     * @param edicaoUCID id da edição a desativar
     * @return EdicaoUCDTO em estado DESAVTICA
     */
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

        EdicaoUCDTO edicaoUCDTO = mapper.toDTO(saved);

        try
        {
            projetoRestRepository.saveEdicaoUC(edicaoUCDTO);
        } catch (Exception ignored)
        {
            repository.deleteByID(edicaoUCDTO.getId());
        }
        return mapper.toDTO(saved);
    }

    /**
     * Devolve Lista de EdicaoUCDTO filtradas pelo ID do RUC
     * @param rucID id de RUC
     * @return  Lista de EdicaoUCDTO
     */
    public List<EdicaoUCDTO> findByRucID(Long rucID)
    {
        List<EdicaoUC> edicaoList = repository.findByRucID(rucID);

        return edicaoList.stream().map(mapper::toDTO).toList();
    }

    /**
     * Devolve EdicaoUCDTO filtrada pelo id do RUC e estado ou Optional.Empty()
     * @param rucID id do RUC
     * @param estado estado
     * @return EdicaoUCDTO ou Optional.Empty()
     */
    public Optional<EdicaoUCDTO> findByRucIDAndEstadoEdicaoUC(Long rucID, EstadoEdicaoUC estado)
    {
        Optional<EdicaoUC> found = repository.findByRucIDAndEstadoEdicaoUC(rucID, estado);

        if (found.isEmpty())
        {
            return Optional.empty();
        }

        return Optional.of(mapper.toDTO(found.get()));
    }
}

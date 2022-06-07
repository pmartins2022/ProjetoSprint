package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCDTOMapper;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.repository.EdicaoUCRepository;
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

    /**
     * Endpoint que possibilita encontrar o EdicaoUC por ucCode existente.
     * @param ucCode um objeto com os dados do ucCode
     * @return uma EdicaoUC
     */
    public List<EdicaoUCDTO> findAllEdicaoByUCCode(String ucCode ) {

        List<EdicaoUC> opEdicaoUC = repository.findAllEdicaoByUCCode(ucCode);

        return opEdicaoUC.stream().map(mapper::toDTO).toList();
    }

    /**
     * Endpoint que possibilita criar e gravar uma EdicaoUC
     * @param dto objeto com dadtos
     * @return nova EdicaoUC
     */
    public EdicaoUCDTO createEdicaoUC(EdicaoUCDTO dto)
    {
        EdicaoUC edicaoUC = mapper.toModel(dto);

        if (ucService.findBySigla(dto.getUcCode()).isEmpty())
        {
            throw new OptionalVazioException("Não existe Unidade Curricular com esse id "+ dto.getUcCode());
        }

        if (anoLetivoService.findByID(dto.getAnoLetivoCode()).isEmpty())
        {
            throw new OptionalVazioException("Não existe Ano Letivo com esse id "+ dto.getAnoLetivoCode());
        }

        EdicaoUC saveEdicaoUC = repository.saveEdicaoUC(edicaoUC);

        return mapper.toDTO(saveEdicaoUC);
    }
    /**
     * Endpoint que possibilita encontrar o EdicaoUC por id existente.
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
     * @return Lista de EdicaoUC
     */
    public List<EdicaoUCDTO> findAllEdicaoUC()
    {
        List<EdicaoUC> edicaoUC = repository.findAll();

        return edicaoUC.stream().map(mapper::toDTO).toList();
    }
}

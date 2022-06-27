package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.dto.mapper.UnidadeCurricularDTOMapper;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.model.UnidadeCurricular;
import com.grupo2.edicaouc.model.factory.UnidadeCurricularFactory;
import com.grupo2.edicaouc.repository.UnidadeCurricularRepository;
import com.grupo2.edicaouc.repository.rest.ProjetoRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe que permite a ligação entre o UnidadeCurricularController e o resto da API
 */
@Service
public class UnidadeCurricularService
{
    /**
     * objeto do tipo UnidadeCurricularRepository a ser utilizado
     */
    @Autowired
    private UnidadeCurricularRepository repository;

    /**
     * Objeto do tipo UnidadeCurricularDTOMapper a ser utilizado
     */
    @Autowired
    private UnidadeCurricularDTOMapper mapper;

    /**
     * Factory de UnidadeCurricular a ser utilizado
     */
    @Autowired
    private UnidadeCurricularFactory factory;
    @Autowired
    private ProjetoRestRepository projetoRestRepository;


    /**
     * Método que permite encontrar UnidadeCurricular pela sigla
     *
     * @param sigla sigla da UnidadeCurricular
     * @return Optional da UnidadeCurricular encontrada ou Optional vazio
     */
    public Optional<UnidadeCurricularDTO> findBySigla(String sigla)
    {
        Optional<UnidadeCurricular> uc = repository.findBySigla(sigla);

        if (uc.isEmpty())
        {
            return Optional.empty();
        }

        UnidadeCurricularDTO dto = mapper.toDTO(uc.get());

        return Optional.of(dto);
    }

    /**
     * Método que permite criar e guardar UnidadeCurricular na Base de Dados
     *
     * @param unidadeCurricularDTO UnidadeCurricular a ser guardada
     * @return UnidadeCurricular guardada
     * @throws ValidacaoInvalidaException
     */
    public UnidadeCurricularDTO createAndSaveUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws ValidacaoInvalidaException
    {
        UnidadeCurricular unidadeCurricular = mapper.toModel(unidadeCurricularDTO);

        UnidadeCurricular savedUnidadeCurricular = repository.saveUnidadeCurricular(unidadeCurricular);

        UnidadeCurricularDTO uc = mapper.toDTO(savedUnidadeCurricular);

        projetoRestRepository.saveUnidadeCurricular(uc);

        return uc;
    }

    /**
     * Método que permite atualizar atributos de uma UnidadeCurricular
     *
     * @param sigla       sigla da UnidadeCurricular
     * @param denominacao nova denominacao da UnidadeCurricular
     * @return UnidadeCurricular guardada
     * @throws ValidacaoInvalidaException
     */
    public Optional<UnidadeCurricularDTO> update(String sigla, String denominacao) throws ValidacaoInvalidaException
    {
        Optional<UnidadeCurricular> ucRequested = repository.findBySigla(sigla);

        if (ucRequested.isEmpty())
        {
            return Optional.empty();
        }
        ucRequested.get().setDenominacao(denominacao);

        UnidadeCurricular uc = factory.createUnidadeCurricular(sigla, ucRequested.get().getDenominacao());

        UnidadeCurricular ucUpdated = repository.updateUnidadeCurricular(uc);

        UnidadeCurricularDTO dtoUpdated = mapper.toDTO(ucUpdated);

        return Optional.of(dtoUpdated);
    }


    /**
     * Método que permite encontrar todas as UnidadeCurricular's na Base de Dados
     *
     * @return lista com UnidadeCurricular's encontradas
     */
    public List<UnidadeCurricularDTO> findAll()
    {
        List<UnidadeCurricular> lista = repository.findAll();

        List<UnidadeCurricularDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }
}

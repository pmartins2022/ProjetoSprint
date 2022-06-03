package com.grupo2.uc.service;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.dto.mapper.UnidadeCurricularDTOMapper;
import com.grupo2.uc.exception.OptionalVazioException;
import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.model.factory.UnidadeCurricularFactory;
import com.grupo2.uc.repository.UnidadeCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeCurricularService
{
    @Autowired
    private UnidadeCurricularRepository repository;

    @Autowired
    private UnidadeCurricularDTOMapper mapper;

    @Autowired
    private UnidadeCurricularFactory factory;


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

    public UnidadeCurricularDTO createAndSaveUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws ValidacaoInvalidaException
    {
        UnidadeCurricular unidadeCurricular = mapper.toModel(unidadeCurricularDTO);

        UnidadeCurricular savedUnidadeCurricular = repository.saveUnidadeCurricular(unidadeCurricular);

        return mapper.toDTO(savedUnidadeCurricular);
    }


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
    public List<UnidadeCurricularDTO> findAll()
    {
        List<UnidadeCurricular> lista = repository.findAll();

        List<UnidadeCurricularDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }
}

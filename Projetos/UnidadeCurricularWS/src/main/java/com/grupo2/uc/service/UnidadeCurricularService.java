package com.grupo2.uc.service;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.dto.mapper.UnidadeCurricularDTOMapper;
import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.repository.UnidadeCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnidadeCurricularService
{
    @Autowired
    private UnidadeCurricularRepository repository;

    @Autowired
    private UnidadeCurricularDTOMapper mapper;


    public Optional<UnidadeCurricularDTO> findByID(String sigla)
    {
        Optional<UnidadeCurricular> uc = repository.findByID(sigla);

        if (uc.isEmpty())
        {
            return Optional.empty();
        }

        UnidadeCurricularDTO dto = mapper.toDTO(uc.get());

        return Optional.of(dto);
    }

    public UnidadeCurricularDTO createUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws ValidacaoInvalidaException
    {
        UnidadeCurricular unidadeCurricular = mapper.toModel(unidadeCurricularDTO);

        UnidadeCurricular savedUnidadeCurricular = repository.saveUnidadeCurricular(unidadeCurricular);

        return mapper.toDTO(savedUnidadeCurricular);
    }
}

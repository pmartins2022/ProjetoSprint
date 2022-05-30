package com.grupo2.uc.service;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.dto.mapper.UnidadeCurricularDTOMapper;
import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.repository.UnidadeCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UnidadeCurricularService
{
    @Autowired
    private UnidadeCurricularRepository repository;

    @Autowired
    private UnidadeCurricularDTOMapper mapper;

    public UnidadeCurricularDTO createUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws ValidacaoInvalidaException
    {
        UnidadeCurricular unidadeCurricular = mapper.toModel(unidadeCurricularDTO);

        UnidadeCurricular savedUnidadeCurricular = repository.saveUnidadeCurricular(unidadeCurricular);

        return mapper.toDTO(savedUnidadeCurricular);
    }
    }

    //TODO: criar os variados casos de uso no servico
}

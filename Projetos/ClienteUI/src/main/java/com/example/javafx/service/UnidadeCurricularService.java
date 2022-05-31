package com.example.javafx.service;

import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestException;
import com.example.javafx.repository.rest.UnidadeCurricularRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadeCurricularService
{
    @Autowired
    private UnidadeCurricularRestRepo restRepo;

    public UnidadeCurricularDTO createUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws RestException
    {
        return restRepo.createUnidadeCurricular(unidadeCurricularDTO);
    }

}

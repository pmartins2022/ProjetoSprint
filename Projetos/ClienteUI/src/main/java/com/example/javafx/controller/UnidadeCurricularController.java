package com.example.javafx.controller;

import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UnidadeCurricularController
{
    @Autowired
    private UnidadeCurricularService unidadeCurricularService;

    public UnidadeCurricularDTO createUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws RestPostException
    {
        return unidadeCurricularService.createUnidadeCurricular(unidadeCurricularDTO);
    }

}

package com.grupo2.uc.controller;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uc")
public class UnidadeCurricularController
{
    @Autowired
    private UnidadeCurricularService service;

    @PostMapping("/criar")
    public ResponseEntity<UnidadeCurricularDTO> createUnidadeCurricular(@RequestBody UnidadeCurricularDTO unidadeCurricularDTO)
    {
        try
        {
            UnidadeCurricularDTO dto = service.createUnidadeCurricular(unidadeCurricularDTO);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch (ErroGeralException e)
        {
            throw new ErroGeralException(e.getMessage());
        }
    }
}
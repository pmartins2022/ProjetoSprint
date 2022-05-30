package com.grupo2.uc.controller;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.exception.OptionalVazioException;
import com.grupo2.uc.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/uc")
public class UnidadeCurricularController
{
    @Autowired
    private UnidadeCurricularService service;

    //TODO: criar os variados casos de uso no controller

    @GetMapping("/{sigla}")
    public ResponseEntity<Object> findByID(@PathVariable("sigla") String sigla)
    {
        Optional<UnidadeCurricularDTO> dto = service.findByID(sigla);

        if (dto.isEmpty())
        {
            throw new OptionalVazioException("Não existe Unidade Curricular com essa Sigla");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
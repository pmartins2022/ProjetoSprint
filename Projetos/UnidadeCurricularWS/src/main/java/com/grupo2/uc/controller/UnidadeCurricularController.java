package com.grupo2.uc.controller;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.exception.OptionalVazioException;
import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/uc")
public class UnidadeCurricularController
{
    @Autowired
    private UnidadeCurricularService service;

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
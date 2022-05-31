package com.grupo2.uc.controller;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.exception.ListaVaziaException;
import com.grupo2.uc.exception.OptionalVazioException;
import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        Optional<UnidadeCurricularDTO> dto = service.findBySigla(sigla);


        if (dto.isEmpty())
        {
            throw new OptionalVazioException("Não existe Unidade Curricular com essa Sigla");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<UnidadeCurricularDTO> createAndSaveUnidadeCurricular(@RequestBody UnidadeCurricularDTO unidadeCurricularDTO)
    {
        try
        {
            UnidadeCurricularDTO dto = service.createAndSaveUnidadeCurricular(unidadeCurricularDTO);

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

    @PutMapping("/{sigla}")
    public ResponseEntity<Object> updateDenominacao(@PathVariable("sigla") String sigla, @RequestParam String denominacao)
    {
        Optional<UnidadeCurricularDTO> dto = service.update(sigla, denominacao);

        if (dto.isEmpty())
        {
            throw new OptionalVazioException("Ocorreu um erro ao guardar Unidade Curricular");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listAll()
    {
        List<UnidadeCurricularDTO> lista = service.findAll();

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("A lista de Unidades Curriculares encontrasse vazia");
        }

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }
}
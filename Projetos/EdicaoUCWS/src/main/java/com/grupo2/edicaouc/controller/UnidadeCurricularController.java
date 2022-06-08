package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe REST Controller de Unidade Curricular. Possui endpoints para findByID, createAndSaveUnidadeCurricular, updateDenominacao e listAll.
 */
@RestController
@RequestMapping("uc")
public class UnidadeCurricularController
{
    /**
     * Objeto da classe UnidadeCurricularService a ser utilizador pelo REST Controller
     */
    @Autowired
    private UnidadeCurricularService service;

    /**
     * Endpoint que permite encontrar unidade curricular por id (sigla).
     *
     * @param sigla variável com o valor do id(sigla).
     * @return unidade curricular OU exceção da classe OptionalVazioException caso não encontre a unidade curricular com id(sigla) introduzido
     */
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

    /**
     * Endpoint que permite criar e guardar uma unidade curricular.
     *
     * @param unidadeCurricularDTO objeto DTO com dados da unidade curricular a criar.
     * @return unidade curricular OU exceção da classe ValidacaoInvalidaException caso os dados não passem nas validações
     * OU exceção da classe ErroGeralException caso uma unidade curricular com o mesmo id(sigla) exista na base de dados.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/criar")
    public ResponseEntity<UnidadeCurricularDTO> createAndSaveUnidadeCurricular(@RequestBody UnidadeCurricularDTO unidadeCurricularDTO)
    {
        try
        {
            UnidadeCurricularDTO dto = service.createAndSaveUnidadeCurricular(unidadeCurricularDTO);

            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        } catch (ErroGeralException e)
        {
            throw new ErroGeralException(e.getMessage());
        }
    }

    /**
     * Endpoint que permite atualizar a denominação de uma unidade curricular.
     *
     * @param sigla       variável com o valor do id(sigla).
     * @param denominacao variável com o valor da denominacao nova.
     * @return unidade curricular com denominacao atualizada OU exceção da classe OptionalVazioException caso não encontre unidade curricular com esse id(sigla).
     */
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

    /**
     * Endpoint que permite listar todas as unidades curriculares existentes na base de dados.
     *
     * @return lista com todas as unidade curriculares existentes OU exceção da classe ListaVaziaException caso não haja unidades curriculares na base de dados.
     */
    @GetMapping("/listar")
    public ResponseEntity<Object> listAll()
    {
        List<UnidadeCurricularDTO> lista = service.findAll();

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("A lista de Unidades Curriculares encontra-se vazia");
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
package com.grupo2.projeto.controller;


import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController
{
    @Autowired
    private AvaliacaoService service;

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/criar")
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO)
    {
        try
        {
            AvaliacaoDTO avaliacaoDTOSaved = service.createAndSave(avaliacaoDTO);
            return new ResponseEntity<>(avaliacaoDTOSaved, HttpStatus.CREATED);
        }
        catch (OptionalVazioException e)
        {
            throw e;
        }
        catch (ValidacaoInvalidaException e)
        {
            throw e;
        }
        catch (IdInvalidoException e)
        {
            throw e;
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long id)
    {
        Optional<AvaliacaoDTO> optionalAvaliacaoDTO = service.findById(id);

        if (optionalAvaliacaoDTO.isPresent())
        {
            AvaliacaoDTO avaliacaoDTO = optionalAvaliacaoDTO.get();
            return new ResponseEntity<>(avaliacaoDTO, HttpStatus.OK);
        } else
        {
            throw new OptionalVazioException("Nao existe nenhuma avaliacao com esse ID");
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<Object> listAllMomentoAvaliacao()
    {
        List<AvaliacaoDTO> lista = service.findAll();

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Momentos de Avaliação");
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}

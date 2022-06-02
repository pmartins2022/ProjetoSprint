package com.grupo2.organizacao.controller;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.exception.ListaVaziaException;
import com.grupo2.organizacao.exception.ValidacaoInvalidaException;
import com.grupo2.organizacao.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organizacao")
public class OrganizacaoController
{
    @Autowired
    private OrganizacaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByID(@PathVariable("id") Long id)
    {
        Optional<OrganizacaoDTO> optionalOrganizacaoDTO = service.findByID(id);

        if (optionalOrganizacaoDTO.isEmpty())
        {
            throw new ValidacaoInvalidaException("Não existe Organização com esse ID");
        }
        return new ResponseEntity<>(optionalOrganizacaoDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Object> findByNIF(@RequestParam(name = "nif") Integer nif)
    {
        Optional<OrganizacaoDTO> optionalOrganizacaoDTO = service.findByNIF(nif);

        if (optionalOrganizacaoDTO.isEmpty())
        {
            throw new ValidacaoInvalidaException("Não existe Organização com esse NIF");
        }
        return new ResponseEntity<>(optionalOrganizacaoDTO, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OrganizacaoDTO>> findAll()
    {
        List<OrganizacaoDTO> list = service.findAll();

        if (list.isEmpty())
        {
            throw new ListaVaziaException("Nao existem organizacoes");
        }

        return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
    }
}

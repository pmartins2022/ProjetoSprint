package com.grupo2.organizacao.controller;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.exception.ValidacaoInvalidaException;
import com.grupo2.organizacao.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

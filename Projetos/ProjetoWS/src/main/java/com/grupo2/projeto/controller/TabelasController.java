package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.*;
import com.grupo2.projeto.security.LoginContext;
import com.grupo2.projeto.security.SecurityUtils;
import com.grupo2.projeto.service.TabelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tabelas")
public class TabelasController
{
    @Autowired
    private TabelaService service;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCENTE')")
    @PostMapping("/edicaoUC")
    public ResponseEntity<Boolean> save(@RequestBody EdicaoUCDTO edicaoUCDTO, HttpServletRequest req)
    {
        System.out.println("ProjetoWS");
        System.out.println(edicaoUCDTO);
        String encoded =  req.getHeader(SecurityUtils.AUTH);

        LoginContext.setToken(encoded);

        try
        {
            service.save(edicaoUCDTO);
            System.out.println("Depois do service");
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCENTE')")
    @PostMapping("/uc")
    public ResponseEntity<Boolean> save(@RequestBody UnidadeCurricularDTO dto, HttpServletRequest req)
    {
        String encoded =  req.getHeader(SecurityUtils.AUTH);

        LoginContext.setToken(encoded);

        try
        {
            service.save(dto);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCENTE')")
    @PostMapping("/organizacao")
    public ResponseEntity<Boolean> save(@RequestBody OrganizacaoDTO dto, HttpServletRequest req)
    {
        String encoded =  req.getHeader(SecurityUtils.AUTH);

        LoginContext.setToken(encoded);

        System.out.println(dto.toString());
        try
        {
            service.save(dto);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCENTE')")
    @PostMapping("/proposta")
    public ResponseEntity<Boolean> save(@RequestBody PropostaDTO dto, HttpServletRequest req)
    {
        String encoded =  req.getHeader(SecurityUtils.AUTH);

        LoginContext.setToken(encoded);

        try
        {
            service.save(dto);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCENTE')")
    @PostMapping("/momentoAvaliacao")
    public ResponseEntity<Boolean> save(@RequestBody MomentoAvaliacaoDTO dto, HttpServletRequest req)
    {
        String encoded =  req.getHeader(SecurityUtils.AUTH);

        LoginContext.setToken(encoded);

        try
        {
            service.save(dto);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCENTE')")
    @PostMapping("/utilizador")
    public ResponseEntity<Boolean> save(@RequestBody UtilizadorDTO dto, HttpServletRequest req)
    {
        String encoded =  req.getHeader(SecurityUtils.AUTH);

        LoginContext.setToken(encoded);

        try
        {
            service.save(dto);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

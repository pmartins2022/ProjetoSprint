package com.example.javafx.service;

import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.OrganizacaoDTO;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.repository.rest.EdicaoUCRestRepo;
import com.example.javafx.repository.rest.OrganizacaoRestRepo;
import com.example.javafx.repository.rest.PropostaRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PropostaService
{
    @Autowired
    private OrganizacaoRestRepo repo;

    @Autowired
    private EdicaoUCRestRepo edicaoRepo;

    @Autowired
    private PropostaRestRepo propostaRestRepo;

    public List<String> findAllOrganizacao()
    {
        List<OrganizacaoDTO> list = repo.findAll();

        return list.stream().map(OrganizacaoDTO::getDenominacao).toList();
    }

    public List<String> findAllEdicao()
    {
        List<EdicaoUCDTO> list = edicaoRepo.findAll();

        return list.stream().map(EdicaoUCDTO::toString).toList();
    }

    public PropostaDTO saveProposta(PropostaDTO dto)
    {
        return propostaRestRepo.createProposta(dto);
    }
}
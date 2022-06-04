package com.example.javafx.service;

import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.OrganizacaoDTO;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.EdicaoUCRestRepo;
import com.example.javafx.repository.rest.OrganizacaoRestRepo;
import com.example.javafx.repository.rest.PropostaRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Classe service para uma Proposta.
 */
@Service
public class PropostaService
{
    @Autowired
    private OrganizacaoRestRepo repo;

    @Autowired
    private EdicaoUCRestRepo edicaoRepo;

    @Autowired
    private PropostaRestRepo propostaRestRepo;

    /**
     * Obter lista de todas as organizações.
     * @return Lista de organizações.
     */
    public List<String> findAllOrganizacao()
    {
        List<OrganizacaoDTO> list = repo.findAll();

        return list.stream().map(OrganizacaoDTO::getDenominacao).toList();
    }

    /**
     * Obter lista de todas as edições.
     * @return Lista de edições.
     */
    public List<String> findAllEdicao()
    {
        List<EdicaoUCDTO> list = edicaoRepo.findAll();

        return list.stream().map(EdicaoUCDTO::toString).toList();
    }

    /**
     * Gravar uma nova proposta.
     * @param dto Proposta a ser gravada.
     * @return Proposta gravada.
     * @throws RestPostException Exceção ao gravar a proposta.
     */
    public PropostaDTO saveProposta(PropostaDTO dto) throws RestPostException
    {
        return propostaRestRepo.createProposta(dto);
    }
}
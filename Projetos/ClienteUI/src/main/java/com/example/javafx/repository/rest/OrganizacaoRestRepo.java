package com.example.javafx.repository.rest;

import com.example.javafx.dto.OrganizacaoDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.ErrorDetail;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Classe que permite a comunicação com WebService externo de Organizacao.
 */
@Repository
public class OrganizacaoRestRepo
{
    /**
     * Tentar obter lista de todas as organizações.
     * @return Lista de organizações.
     */
    public List<OrganizacaoDTO> findAll()
    {
        final WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/organizacao/listar").get()
                .retrieve();

        responseSpec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return responseSpec.bodyToMono(new ParameterizedTypeReference<List<OrganizacaoDTO>>()
        {
        }).block();
    }
}

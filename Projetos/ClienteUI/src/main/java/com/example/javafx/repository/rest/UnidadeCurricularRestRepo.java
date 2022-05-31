package com.example.javafx.repository.rest;

import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.dto.UnidadeCurricularDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class UnidadeCurricularRestRepo
{
    public List<UnidadeCurricularDTO> findAll()
    {
        final WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8086/uc/listar").get()
                .retrieve();

        responseSpec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return responseSpec.bodyToMono(new ParameterizedTypeReference<List<UnidadeCurricularDTO>>()
        {
        }).block();
    }
}

package com.example.javafx.repository.rest;

import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestPostException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Classe que permite a comunicação com WebService externo de Unidade Curricular.
 */
@Repository
public class UnidadeCurricularRestRepo
{

    /**
     * Tentar obter lista de todas as unidades curriculares.
     * @return Lista de unidades curriculares.
     */
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

    /**
     * Tentar criar uma unidade curricular.
     * @param unidadeCurricularDTO Unidade curricular a criar.
     * @return Unidade curricular criada.
     * @throws RestPostException Erro ao criar unidade curricular.
     */
    public UnidadeCurricularDTO createUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8086/uc/criar").post().
                    body(BodyInserters.fromValue(unidadeCurricularDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(UnidadeCurricularDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }
}
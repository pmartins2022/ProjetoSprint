package com.grupo2.edicaouc.repository.rest;

import com.grupo2.edicaouc.dto.*;
import com.grupo2.edicaouc.exception.ErrorDetail;
import com.grupo2.edicaouc.security.LoginContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Classe ProjetoRestRepository que permite estabeler ligação com o WebService ProjetoWS
 */
@Repository
public class ProjetoRestRepository
{
    /**
     * Guarda EdicaoUCDTO
     * @param edicaoUCDTO EdicaoUCDTO a guardar
     */
    public void saveEdicaoUC(EdicaoUCDTO edicaoUCDTO)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8083/tabelas/edicaoUC" ).
                build().post().header("Authorization", LoginContext.getToken())
                .body(BodyInserters.fromValue(edicaoUCDTO)).retrieve();

        spec.toBodilessEntity().block();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

    }

    /**
     * Guarda UnidadeCurricularDTO
     * @param unidadeCurricularDTO UnidadeCurricularDTO a guardar
     */
    public void saveUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8083/tabelas/uc" ).
                build().post().header("Authorization", LoginContext.getToken())
                .body(BodyInserters.fromValue(unidadeCurricularDTO)).retrieve();

        spec.toBodilessEntity().block();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

    }
    /**
     * Guarda momentoAvaliacaoDTO
     * @param momentoAvaliacaoDTO momentoAvaliacaoDTO a guardar
     */
    public void saveMomentoAvaliacao(MomentoAvaliacaoDTO momentoAvaliacaoDTO)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8083/tabelas/momentoAvaliacao" ).
                build().post().header("Authorization", LoginContext.getToken())
                .body(BodyInserters.fromValue(momentoAvaliacaoDTO)).retrieve();

        spec.toBodilessEntity().block();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

    }

}

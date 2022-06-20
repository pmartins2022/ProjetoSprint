package com.example.javafx.repository.rest;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class ProjetoRestRepository
{
    public AvaliacaoDTO createAvaliacao(AvaliacaoDTO avaliacaoDTO) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/avaliacao/criar").post().
                    body(BodyInserters.fromValue(avaliacaoDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(AvaliacaoDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }
}

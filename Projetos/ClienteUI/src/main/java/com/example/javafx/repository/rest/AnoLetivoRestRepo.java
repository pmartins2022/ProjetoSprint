package com.example.javafx.repository.rest;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class AnoLetivoRestRepo
{
    public AnoLetivoDTO createAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8081/anoLetivo/criar").post().
                    body(BodyInserters.fromValue(anoLetivoDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(AnoLetivoDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: "+e.getMessage());
        }
    }

    public String updateTest(AnoLetivoDTO dto, long pathVariable, long id)
    {
        final WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8081/anoLetivo/" + pathVariable + "?id=" + id).put().
                body(BodyInserters.fromValue(dto)).retrieve();

        responseSpec.onStatus(HttpStatus::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return responseSpec.bodyToMono(String.class).block();
    }

    public List<AnoLetivoDTO> findAll() throws RestPostException
    {
        try
        {
            final WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8081/anoLetivo/listar").get()
                    .retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(new ParameterizedTypeReference<List<AnoLetivoDTO>>()
            {
            }).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }
}
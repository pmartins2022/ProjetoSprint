package com.example.javafx.repository.rest;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class AnoLetivoRestRepo
{
    public String getString()
    {
        return WebClient.create("http://localhost:8081/anoLetivo/test").get().
                retrieve().bodyToMono(String.class).onErrorReturn("Nao deu :(").block();
    }

    public AnoLetivoDTO createAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws RestException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8081/anoLetivo/create").post().
                    body(BodyInserters.fromValue(anoLetivoDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(AnoLetivoDTO.class).block();
        }
        catch (RestException e)
        {
            throw new RestException("Problema no servidor: "+e.getMessage());
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

    public List<AnoLetivoDTO> findAll() throws RestException
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
        catch (RestException e)
        {
            throw new RestException(e.getMessage());
        }
    }
}
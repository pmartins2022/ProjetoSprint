package com.example.javafx.repository.rest;

import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class EdicaoUCRestRepo
{
    public EdicaoUCDTO createEdicaoUC(EdicaoUCDTO edicaoUCDTO) throws RestException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8082/edicaoUC/criar").post().
                    body(BodyInserters.fromValue(edicaoUCDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(EdicaoUCDTO.class).block();
        }
        catch (RestException e)
        {
            throw new RestException("Problema no servidor: "+ e.getMessage());
        }
    }
}

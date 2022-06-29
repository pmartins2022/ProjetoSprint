package com.example.javafx.repository.rest;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.model.LoginContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class AvaliacaoRestRepo
{

    public List<AvaliacaoDTO> findEditableAvaliacoes() throws RestPostException
    {
        try
        {
            final WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/avaliacao/listEditable").get()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(new ParameterizedTypeReference<List<AvaliacaoDTO>>()
            {
            }).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }
}

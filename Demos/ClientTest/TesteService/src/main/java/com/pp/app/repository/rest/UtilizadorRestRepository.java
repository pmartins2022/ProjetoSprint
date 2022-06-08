package com.pp.app.repository.rest;

import com.pp.app.dto.UtilizadorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Repository
public class UtilizadorRestRepository
{
    public UtilizadorDTO findByUsername(String username)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/find?username=" + username).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
            throw new IllegalArgumentException(clientResponse.statusCode().getReasonPhrase());
        });

        return spec.bodyToMono(UtilizadorDTO.class).block();
    }

    public String getAdminMessage(String encoded)
    {
        WebClient.ResponseSpec spec =
                WebClient.builder().baseUrl("http://localhost:8085/utilizador/admin").build().
                        get().header("Authorization",encoded).retrieve();

        spec.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
           throw new IllegalArgumentException(clientResponse.statusCode().getReasonPhrase());
        });

        return spec.bodyToMono(String.class).block();
    }
}
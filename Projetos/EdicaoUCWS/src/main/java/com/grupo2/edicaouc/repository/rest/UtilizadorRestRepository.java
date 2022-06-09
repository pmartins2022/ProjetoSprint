package com.grupo2.edicaouc.repository.rest;

import com.grupo2.edicaouc.dto.UtilizadorDTO;
import com.grupo2.edicaouc.exception.ErrorDetail;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class UtilizadorRestRepository
{
    public UtilizadorDTO findByUsername(String username)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/find?username=" + username).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return spec.bodyToMono(UtilizadorDTO.class).block();
    }

    public UtilizadorDTO findById(Long id)
    {
        WebClient.ResponseSpec spec = WebClient.create("http://localhost:8085/utilizador/"+id).get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return spec.bodyToMono(UtilizadorDTO.class).block();
    }

    public Boolean isRole(String role, Long id)
    {
       WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/"+role +"/"+id).
                build().get().retrieve();

        /*final WebClient.ResponseSpec spec = WebClient.create("http://localhost:8085/utilizador/"+role +"/"+id).get()
                .retrieve();*/

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return spec.bodyToMono(Boolean.class).block();
    }
}
package com.grupo2.projeto.repository.rest;

import com.grupo2.projeto.dto.UtilizadorAuthDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe que permite a comunicação com WebService externo de utilizador.
 */
@Repository
public class UtilizadorRestRepository
{
    public UtilizadorAuthDTO findByUsername(String username)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/find?username=" + username).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
            throw new IllegalArgumentException(clientResponse.statusCode().getReasonPhrase());
        });

        return spec.bodyToMono(UtilizadorAuthDTO.class).block();
    }
}

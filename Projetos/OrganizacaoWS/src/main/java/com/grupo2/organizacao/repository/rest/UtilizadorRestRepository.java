package com.grupo2.organizacao.repository.rest;

import com.grupo2.organizacao.dto.UtilizadorAuthDTO;
import com.grupo2.organizacao.exception.ErrorDetail;
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

    /**
     * Devolve UtilizadorAuthDTO filtrado pelo username
     * @param username username do UtilizadorAuthDTO
     * @return UtilizadorAuthDTO
     */
    public UtilizadorAuthDTO findByUsername(String username)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/find?username=" + username).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
            throw new IllegalArgumentException(clientResponse.statusCode().getReasonPhrase());
        });

        return spec.bodyToMono(UtilizadorAuthDTO.class).block();
    }

    /**
     * Devolve Boolean resultado da comparação entre ROLE pretendido e ROLE obtido pelo id de Utilizador
     * @param role role pretendido
     * @param id id do Utilizador
     * @return Boolen
     */
    public Boolean isRole(String role, Long id)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/"+role +"/"+id).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return spec.bodyToMono(Boolean.class).block();
    }
}

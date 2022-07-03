package com.grupo2.edicaouc.repository.rest;

import com.grupo2.edicaouc.dto.UtilizadorDTO;
import com.grupo2.edicaouc.exception.ErrorDetail;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Classe UtilizadorRestRepository que permite estabeler ligação com o WebService UtilizadorWS
 */
@Repository
public class UtilizadorRestRepository
{
    /**
     * Devolve UtilizadorDTO filtrado pelo username
     * @param username username
     * @return UtilizadorDTO
     */
    public UtilizadorDTO findByUsername(String username)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/find?username=" + username).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return spec.bodyToMono(UtilizadorDTO.class).block();
    }

    /**
     * Devolve UtilizadorDTO filtrado pelo id
     * @param id id
     * @return UtilizadorDTO
     */
    public UtilizadorDTO findById(Long id)
    {
        WebClient.ResponseSpec spec = WebClient.create("http://localhost:8085/utilizador/"+id).get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return spec.bodyToMono(UtilizadorDTO.class).block();
    }

    /**
     * Devolve true ou false caso UtilizadorDTO seja do role especificado por parametro
     * @param role role
     * @param id id do UtilizadorDTO
     * @return boolean
     */
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
package com.grupo2.utilizadores.repository.rest;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.ErrorDetail;
import com.grupo2.utilizadores.security.LoginContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Repository
public class ProjetoRestRepository
{
    public void saveUtilizador(UtilizadorDTO utilizadorDTO)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8083/tabelas/utilizador" ).
                build().post().header("Authorization", LoginContext.getToken())
                .body(BodyInserters.fromValue(utilizadorDTO)).retrieve();

        spec.toBodilessEntity().block();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

    }
}

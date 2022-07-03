package com.grupo2.utilizadores.repository.rest;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.ErrorDetail;
import com.grupo2.utilizadores.security.LoginContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Classe que permite fazer pedidos REST para um outro WebService, neste caso, ProjetoWS
 */
@Repository
public class ProjetoRestRepository
{
    /**
     * Gravar um novo utilizador no WS de Projeto
     * @param utilizadorDTO a informacao do utilizador
     */
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

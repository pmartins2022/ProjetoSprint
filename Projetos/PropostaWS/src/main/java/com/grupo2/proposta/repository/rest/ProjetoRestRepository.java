package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.ProjetoDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import com.grupo2.proposta.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe que permite a comunicação com WebService externo de projeto.
 */
@Repository
public class ProjetoRestRepository
{
    /**
     * Tenta criar um projeto.
     * @param projetoDTO Projeto a criar
     * @return Projeto criado ou erro
     */
    public ProjetoDTO create(ProjetoDTO projetoDTO)
    {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/projeto/create").post().
                    body(BodyInserters.fromValue(projetoDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(ProjetoDTO.class).block();

    }
}

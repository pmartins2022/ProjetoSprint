package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.ProjetoDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import com.grupo2.proposta.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Repository
public class ProjetoRestRepository
{
    public ProjetoDTO create(ProjetoDTO projetoDTO)
    {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8085/projeto/criar").post().
                    body(BodyInserters.fromValue(projetoDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(ProjetoDTO.class).block();

    }
}

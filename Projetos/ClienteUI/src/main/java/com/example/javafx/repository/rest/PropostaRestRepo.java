package com.example.javafx.repository.rest;

import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Classe que permite a comunicação com WebService externo de Proposta.
 */
@Repository
public class PropostaRestRepo
{

    /**
     * Tentar criar uma proposta.
     * @param anoLetivoDTO Proposta a criar.
     * @return Proposta criada.
     * @throws RestPostException Erro ao criar proposta.
     */
    public PropostaDTO createProposta(PropostaDTO anoLetivoDTO) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8085/proposta/create").post().
                    body(BodyInserters.fromValue(anoLetivoDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: "+e.getMessage());
        }
    }
}

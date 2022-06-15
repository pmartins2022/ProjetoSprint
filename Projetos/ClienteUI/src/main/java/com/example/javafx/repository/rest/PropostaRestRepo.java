package com.example.javafx.repository.rest;

import com.example.javafx.dto.PropostaCandidaturaDTO;
import com.example.javafx.dto.PropostaCandidaturaIDDTO;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Classe que permite a comunicação com WebService externo de Proposta.
 */
@Repository
public class PropostaRestRepo
{

    /**
     * Tentar criar uma proposta.
     * @param propostaDTO Proposta a criar.
     * @return Proposta criada.
     * @throws RestPostException Erro ao criar proposta.
     */
    public PropostaDTO createProposta(PropostaDTO propostaDTO) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/create").post().
                    body(BodyInserters.fromValue(propostaDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: "+e.getMessage());
        }
    }

    public List<PropostaDTO> findAllPropostaCandidatura()
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/?estado=0L").get().retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(new ParameterizedTypeReference<List<PropostaDTO>>()
            {
            }).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    public PropostaCandidaturaDTO acceptCandidaturaProposta(PropostaCandidaturaIDDTO propostaCandidaturaID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/aceitarCandidaturaAluno").post().
                    body(BodyInserters.fromValue(propostaCandidaturaID)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaCandidaturaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    public PropostaCandidaturaDTO rejectCandidaturaProposta(PropostaCandidaturaIDDTO propostaCandidaturaID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/rejeitarCandidaturaAluno").post().
                    body(BodyInserters.fromValue(propostaCandidaturaID)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaCandidaturaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }
}

package com.example.javafx.repository.rest;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.AvaliacaoNotaDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.model.LoginContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Classe AvaliacaoRestRepo que permite estabeler ligação com Avaliacao
 */
@Repository
public class AvaliacaoRestRepo
{
    /**
     * Encontrar avaliacoes em que o presidente pode criar/alterar nota de avaliacao
     * @return a lista
     * @throws RestPostException um erro na DB
     */
    public List<AvaliacaoDTO> findEditableAvaliacoes() throws RestPostException
    {
        try
        {
            final WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/avaliacao/listEditable").get()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(new ParameterizedTypeReference<List<AvaliacaoDTO>>()
            {
            }).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Encontrar notas pelo estado da avaliacao e pelo ruc id
     * @param rucID o id do ruc
     * @param estado o estado da nota
     * @return a lista de notas
     */
    public List<AvaliacaoNotaDTO> findAllByEstadoAndRucID(Long rucID, String estado)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/avaliacao/"+ rucID +"?estado="+estado).get()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(new ParameterizedTypeReference<List<AvaliacaoNotaDTO>>()
            {
            }).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Rever uma nota
     * @param id o id da nota
     * @param avaliacao a alteracao da avaliacao
     */
    public void reviewAvaliacaoNota(Long id, String avaliacao)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/avaliacao/reverAvaliacaoNota"+ id
                            +"?avaliacao="+avaliacao).put()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.toBodilessEntity().block();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Encontrar nota a partir do ID da avaliacao
     * @param index o id
     * @return a nota da avaliacao
     */
    public AvaliacaoNotaDTO findNotaByAvaliacaoID(Long index)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/avaliacao/nota/"+index).get()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(AvaliacaoNotaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Atualizar uma nota
     * @param notaAtual a nota a atualizar
     */
    public void atualizarNota(AvaliacaoNotaDTO notaAtual)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/avaliacao/nota").patch()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(notaAtual)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            responseSpec.toBodilessEntity().block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: "+e.getMessage());
        }
    }

    /**
     * Criar uma nova
     * @param avaliacao informacao da nota
     */
    public void criarNota(AvaliacaoNotaDTO avaliacao)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/avaliacao/avaliar").post()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(avaliacao)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            responseSpec.toBodilessEntity().block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: "+e.getMessage());
        }
    }


}


package com.example.javafx.repository.rest;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.ConteudoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.ProjetoDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.model.LoginContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class ProjetoRestRepository
{
    public AvaliacaoDTO createAvaliacao(AvaliacaoDTO avaliacaoDTO) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/avaliacao/criar").post().
                    body(BodyInserters.fromValue(avaliacaoDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(AvaliacaoDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }

    public List<ProjetoDTO> findAllByOrientadorID(Long orientadorID) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/projeto/orientadorID/" + orientadorID).post().
                    header("Authorization", LoginContext.getToken()).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(List.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }

    public List<ConteudoDTO> findAllByIdProjeto(Long projetoID) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/conteudo/projetoID/" + projetoID).post().
                    header("Authorization", LoginContext.getToken()).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(List.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }

    public ConteudoDTO acceptConteudo(Long conteudoID) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/projeto/aceitarConteudo/" + conteudoID).post()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(ConteudoDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }

    public ConteudoDTO rejectConteudo(Long conteudoID) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8083/conteudo/rejeitarConteudo/" + conteudoID).post().
                    header("Authorization", LoginContext.getToken()).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(ConteudoDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }
}

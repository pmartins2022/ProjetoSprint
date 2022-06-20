package com.example.javafx.repository.rest;

import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.OrganizacaoDTO;
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
 * Classe que permite a comunicação com WebService externo de Edição UC.
 */
@Repository
public class EdicaoUCRestRepo
{
    /**
     * Tentar criar uma edição UC.
     * @param edicaoUCDTO Edição UC a criar.
     * @return Edição UC criado.
     * @throws RestPostException Erro ao criar edição UC.
     */
    public EdicaoUCDTO createEdicaoUC(EdicaoUCDTO edicaoUCDTO) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8081/edicaoUC/criar").post()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(edicaoUCDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(EdicaoUCDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }

    /**
     * Tentar obter lista de todas as edição UC.
     * @return Lista de edição UC.
     */
    public List<EdicaoUCDTO> findAll()
    {
        final WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8082/edicaoUC/listar").get()
                .retrieve();

        responseSpec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return responseSpec.bodyToMono(new ParameterizedTypeReference<List<EdicaoUCDTO>>()
        {
        }).block();
    }
}

package com.example.javafx.repository.rest;

import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.UtilizadorAuthDTO;
import com.example.javafx.dto.UtilizadorDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.model.LoginContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe que permite a comunicação com WebService externo de utilizador.
 */
@Repository
public class UtilizadorRestRepository
{
    /**
     * Tenta obter o utilizador pelo id.
     *
     * @param id      Id do utilizador
     * @return Utilizador ou optional vazio
     */
    public Optional<UtilizadorDTO> findById(Long id)
    {
        try
        {
            WebClient.ResponseSpec retrieve = WebClient.create("http://localhost:8085/utilizador/" + id).get()
                .retrieve();


            UtilizadorDTO dto = retrieve.bodyToMono(UtilizadorDTO.class).block();

            if (dto == null)
            {
                return Optional.empty();
            }

            return Optional.of(dto);
        }
        catch (Exception e)
        {
            return Optional.empty();
        }
    }

    public UtilizadorAuthDTO findByUsername(String username)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/find?username=" + username).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return spec.bodyToMono(UtilizadorAuthDTO.class).block();
    }


    public Boolean isRole(String role, Long id)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/"+role +"/"+id).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return spec.bodyToMono(Boolean.class).block();
    }

    public UtilizadorDTO findAllDocente()
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8085/utilizador/docentes").get()
                    .retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(UtilizadorDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }
}

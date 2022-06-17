package com.grupo2.projeto.repository.rest;

import com.grupo2.projeto.dto.UtilizadorAuthDTO;
import com.grupo2.projeto.dto.UtilizadorDTO;
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
    public UtilizadorAuthDTO findByUsername(String username)
    {
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8085/utilizador/find?username=" + username).
                build().get().retrieve();

        spec.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
            throw new IllegalArgumentException(clientResponse.statusCode().getReasonPhrase());
        });

        return spec.bodyToMono(UtilizadorAuthDTO.class).block();
    }

    public Optional<UtilizadorDTO> findByOrientadorID(Long idOrientador)
    {
        UtilizadorDTO dto = WebClient.create("http://localhost:8085/utilizador/orientador/"+idOrientador).get().
                retrieve().bodyToMono(UtilizadorDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }

    public Optional<UtilizadorDTO> findById(Long id)
    {
        UtilizadorDTO dto = WebClient.create("http://localhost:8085/utilizador/"+id).get().
                retrieve().bodyToMono(UtilizadorDTO.class).block();
        if (dto == null)
        {
            return Optional.empty();
        }
        return Optional.of(dto);
    }

}

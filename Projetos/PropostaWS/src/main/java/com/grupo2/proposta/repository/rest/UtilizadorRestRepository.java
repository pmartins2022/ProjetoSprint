package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.UtilizadorDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Repository
public class UtilizadorRestRepository
{
    public Optional<UtilizadorDTO> findById(Long id)
    {
         UtilizadorDTO dto = WebClient.create("http://localhost:8087/utilizador/"+id).get().
                retrieve().bodyToMono(UtilizadorDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }
}

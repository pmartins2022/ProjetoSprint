package com.grupo2.edicaouc.repository.rest;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class AnoLetivoUCRestRepository
{
    public Optional<AnoLetivoDTO> findById(String id)
    {
        AnoLetivoDTO dto = WebClient.create("http://localhost:8081/anoLetivo/"+id).get().
                retrieve().bodyToMono(AnoLetivoDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }
}

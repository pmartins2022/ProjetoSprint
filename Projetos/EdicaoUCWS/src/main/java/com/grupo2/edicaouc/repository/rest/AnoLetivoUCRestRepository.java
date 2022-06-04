package com.grupo2.edicaouc.repository.rest;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe de AnoLetivoUCRestRepository do projeto. Possui endpoints para findById
 */
@Repository
public class AnoLetivoUCRestRepository
{
    /**
     * Encontrar Id noutro serviço
     * @param id id do AnoLetivo
     * @return de um optional de AnoLetivoDTO
     */
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

package com.grupo2.edicaouc.repository.rest;

import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe de AnoLetivoUCRestRepository do projeto. Possui endpoints para findById
 */
@Repository
public class UnidadeCurricularRestRepository
{    /**
     * Encontrar Id noutro serviço
     * @param id id da Unidade Curricular
     * @return de um optional de UnidadeCurricularDTO
     */
    public Optional<UnidadeCurricularDTO> findById(String id)
    {
        UnidadeCurricularDTO dto = WebClient.create("http://localhost:8086/uc/"+id).get().
                retrieve().bodyToMono(UnidadeCurricularDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }
}

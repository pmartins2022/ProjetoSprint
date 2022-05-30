package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.EdicaoUCDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Repository
public class EdicaoUCRestRepository
{
    public Optional<EdicaoUCDTO> findById(Long id)
    {
        EdicaoUCDTO dto = WebClient.create("http://localhost:8082/edicao/{"+id+"}").get().
                retrieve().bodyToMono(EdicaoUCDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }
}
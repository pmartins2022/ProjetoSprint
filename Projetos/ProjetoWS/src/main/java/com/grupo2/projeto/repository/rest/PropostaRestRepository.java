package com.grupo2.projeto.repository.rest;

import com.grupo2.projeto.dto.PropostaDTO;
import com.grupo2.projeto.dto.UtilizadorDTO;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class PropostaRestRepository
{
    public Optional<PropostaDTO> findById(Long id)
    {
        PropostaDTO dto = WebClient.create("http://localhost:8085/proposta/{"+id+"}").get().
                retrieve().bodyToMono(PropostaDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }
}

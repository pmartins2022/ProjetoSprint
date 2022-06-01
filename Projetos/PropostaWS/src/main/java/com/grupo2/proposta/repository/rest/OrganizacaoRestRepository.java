package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.OrganizacaoDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Repository
public class OrganizacaoRestRepository
{
    public Optional<OrganizacaoDTO> findById(Long id)
    {
        OrganizacaoDTO dto = WebClient.create("http://localhost:8083/organizacao/"+id).get().
                retrieve().bodyToMono(OrganizacaoDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }

    public Optional<OrganizacaoDTO> findByNIF(Integer nif)
    {
        OrganizacaoDTO dto = WebClient.create("http://localhost:8083/organizacao?nif="+nif).get().
                retrieve().bodyToMono(OrganizacaoDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }
}

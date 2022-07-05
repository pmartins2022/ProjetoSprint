package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.OrganizacaoDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe que permite a comunicação com WebService externo de organizacao.
 */
@Repository
public class OrganizacaoRestRepository
{
    /**
     * Tenta obter a organizacao pelo id.
     * @param id Id da organizacao
     * @return Organizacao ou optional vazio
     */
    public Optional<OrganizacaoDTO> findById(Long id)
    {
        try
        {
            OrganizacaoDTO dto = WebClient.create("http://localhost:8082/organizacao/" + id).get()
                    .retrieve().bodyToMono(OrganizacaoDTO.class).block();

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

    /**
     * Tenta obter a organizacao pelo nif.
     * @param nif Nif da organizacao
     * @return Organizacao ou optional vazio
     */
    public Optional<OrganizacaoDTO> findByNIF(Integer nif, String encoded)
    {
        try
        {
            OrganizacaoDTO dto = WebClient.create("http://localhost:8082/organizacao?nif=" + nif).get()
                    .header("Authorization",encoded)
                    .retrieve().bodyToMono(OrganizacaoDTO.class).block();

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
}
package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.EdicaoUCDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe que permite a comunicação com WebService externo de edicao UC.
 */
@Repository
public class EdicaoUCRestRepository
{
    /**
     * Tenta obter a edicao da UC pelo id.
     * @param id Id da edicao de UC
     * @return Edicao de UC ou optional vazio
     */
    public Optional<EdicaoUCDTO> findById(Long id)
    {
        EdicaoUCDTO dto = WebClient.create("http://localhost:8082/edicaoUC/"+id).get().
                retrieve().bodyToMono(EdicaoUCDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }
}
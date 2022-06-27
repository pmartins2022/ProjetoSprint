package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.EdicaoUCDTO;
import com.grupo2.proposta.exception.ErrorDetail;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
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
        EdicaoUCDTO dto = WebClient.create("http://localhost:8081/edicaoUC/"+id).get().
                retrieve().bodyToMono(EdicaoUCDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }

    /**
     * Devolve Lista de EdicaoUCDTO filtrada pelo id de RUC
     * @param rucID id de RUC
     * @return Lista de EdicaoUCDTO
     */
    public List<EdicaoUCDTO> findByRucID(Long rucID)
    {
        WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8081/edicaoUC/ruc/" + rucID).get().
                retrieve();

        responseSpec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return responseSpec.bodyToMono(new ParameterizedTypeReference<List<EdicaoUCDTO>>()
        {
        }).block();

    }

    /**
     * Devolve EdicaoUCDTO filtrada por id de RUC e estado ou Optional.empty()
     * @param rucID id de RUC
     * @return EdicaoUCDTO ou Optional.empty()
     */
    public Optional<EdicaoUCDTO> findByRucIDAndEstadoEdicaoUC(Long rucID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8081/edicaoUC/ruc/"+rucID+"/active").get().
                    retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return Optional.ofNullable(responseSpec.bodyToMono(EdicaoUCDTO.class).block());
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
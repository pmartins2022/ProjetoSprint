package com.grupo2.proposta.repository.rest;

import com.grupo2.proposta.dto.UtilizadorDTO;
import com.grupo2.proposta.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe que permite a comunicação com WebService externo de utilizador.
 */
@Repository
public class UtilizadorRestRepository
{
    /**
     * Tenta obter o utilizador pelo id.
     * @param id Id do utilizador
     * @return Utilizador ou optional vazio
     */
    public Optional<UtilizadorDTO> findById(Long id)
    {
        try
        {
            WebClient.ResponseSpec retrieve = WebClient.create("http://localhost:8087/utilizador/" + id).get().
                    retrieve();


            UtilizadorDTO dto = retrieve.bodyToMono(UtilizadorDTO.class).block();

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

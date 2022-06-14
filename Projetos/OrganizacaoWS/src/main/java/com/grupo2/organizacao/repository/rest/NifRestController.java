package com.grupo2.organizacao.repository.rest;

import com.grupo2.organizacao.dto.NifDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Classe de NifRestController do projeto. Possui endpoints para findByNif
 */
@Controller
public class NifRestController {

    /**
     * Encontrar nif noutro serviço
     * @param nif e o nif
     * @return de um optional da NifDTO
     */
    public Optional<NifDTO> findByNif(int nif)
    {
        NifDTO dto = WebClient.create("http://193.136.62.227:8080/nifs/"+nif).get().
                retrieve().bodyToMono(NifDTO.class).block();

        if (dto == null)
        {
            return Optional.empty();
        }

        return Optional.of(dto);
    }
}

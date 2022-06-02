package com.grupo2.organizacao.repository.rest;

import com.grupo2.organizacao.dto.NifDTO;
import com.grupo2.organizacao.model.Organizacao;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Controller
public class NifRestController {

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

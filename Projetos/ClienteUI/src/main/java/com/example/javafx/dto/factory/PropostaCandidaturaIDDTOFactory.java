package com.example.javafx.dto.factory;

import com.example.javafx.dto.PropostaCandidaturaIDDTO;
import org.springframework.stereotype.Component;

@Component
public class PropostaCandidaturaIDDTOFactory
{
    public PropostaCandidaturaIDDTO createPropostaCandidaturaIDDTO(Long idProjeto, Long idAluno)
    {
        return new PropostaCandidaturaIDDTO(idProjeto, idAluno);
    }
}

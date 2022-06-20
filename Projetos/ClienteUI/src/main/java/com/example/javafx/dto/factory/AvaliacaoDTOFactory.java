package com.example.javafx.dto.factory;

import com.example.javafx.dto.AvaliacaoDTO;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoDTOFactory
{

    public AvaliacaoDTO create(Long idMomentoAvaliacao, Long idOrientador, Long idPresidente,
                               Long idArguente, Long idProjeto, Long idConteudo)
    {
        return new AvaliacaoDTO(idMomentoAvaliacao, idPresidente, idOrientador, idArguente, idProjeto, idConteudo, 19);
    }
}

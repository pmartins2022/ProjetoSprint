package com.example.javafx.dto.factory;

import com.example.javafx.dto.AvaliacaoNotaDTO;
import com.example.javafx.model.EstadoAvaliacao;

public class AvaliacaoNotaDTOFactory {

    public AvaliacaoNotaDTO editarMomentoAvaliacao(Long id, Long idAvaliacao, Long nota, String justificacao, EstadoAvaliacao estadoAvaliacao)
    {
        return new AvaliacaoNotaDTO(id, idAvaliacao,nota,justificacao,estadoAvaliacao);
    }
}

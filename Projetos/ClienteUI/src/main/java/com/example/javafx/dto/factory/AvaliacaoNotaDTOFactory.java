package com.example.javafx.dto.factory;

import com.example.javafx.dto.AvaliacaoNotaDTO;
import com.example.javafx.model.EstadoAvaliacao;
import org.springframework.stereotype.Component;

/**
 * Classe factory para criar objetos do tipo AvaliacaoNotaDTO
 */
@Component
public class AvaliacaoNotaDTOFactory {

    /**
     * Criar uma nota de avaliacao
     * @param id id da nota
     * @param idAvaliacao id da avaliacao
     * @param nota a nota
     * @param justificacao justificacao
     * @param estadoAvaliacao o estado
     * @return nota criada
     */
    public AvaliacaoNotaDTO editarMomentoAvaliacao(Long id, Long idAvaliacao, Long nota, String justificacao, EstadoAvaliacao estadoAvaliacao)
    {
        return new AvaliacaoNotaDTO(id, idAvaliacao,nota,justificacao,estadoAvaliacao);
    }
}

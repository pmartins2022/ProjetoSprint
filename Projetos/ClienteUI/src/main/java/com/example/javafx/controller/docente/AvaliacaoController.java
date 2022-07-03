package com.example.javafx.controller.docente;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.AvaliacaoNotaDTO;
import com.example.javafx.dto.factory.AvaliacaoDTOFactory;
import com.example.javafx.dto.factory.AvaliacaoNotaDTOFactory;
import com.example.javafx.model.EstadoAvaliacao;
import com.example.javafx.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe controller para avaliacoes
 */
@Controller
public class AvaliacaoController
{
    @Autowired
    private AvaliacaoService service;

    @Autowired
    private AvaliacaoDTOFactory avaliacaoDTOFactory;

    @Autowired
    private AvaliacaoNotaDTOFactory avaliacaoNotaDTOFactory;

    /**
     * Encontrar avaliacoes em que o presidente pode criar/alterar nota de avaliacao
     * @return a lista
     */
    public List<AvaliacaoDTO> findEditableAvaliacoes()
    {
        return service.findEditableAvaliacoes();
    }

    /**
     * Encontrar uma nota a partir do ID de avaliaao
     * @param id o id da avaliacao
     * @return a nota dessa avaliacao
     */
    public AvaliacaoNotaDTO findNotaByAvaliacaoID(Long id)
    {
        return service.findNotaByAvaliacaoID(id);
    }

    /**
     * Atualizar uma nota existente
     * @param notaAtual informacao da nota
     */
    public void atualizarNota(AvaliacaoNotaDTO notaAtual)
    {
        service.atualizarNota(notaAtual);
    }

    /**
     * Criar uma nova nota
     * @param avaliacao informacao da nota
     */
    public void criarNota(AvaliacaoNotaDTO avaliacao)
    {
        service.criarNota(avaliacao);
    }
}


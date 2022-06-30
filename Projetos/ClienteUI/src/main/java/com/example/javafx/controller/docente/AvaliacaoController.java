package com.example.javafx.controller.docente;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.AvaliacaoNotaDTO;
import com.example.javafx.dto.factory.AvaliacaoDTOFactory;
import com.example.javafx.dto.factory.AvaliacaoNotaDTOFactory;
import com.example.javafx.model.EstadoAvaliacao;
import com.example.javafx.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AvaliacaoController
{
    @Autowired
    private AvaliacaoService service;

    @Autowired
    private AvaliacaoDTOFactory avaliacaoDTOFactory;

    @Autowired
    private AvaliacaoNotaDTOFactory avaliacaoNotaDTOFactory;

    public List<AvaliacaoDTO> findEditableAvaliacoes()
    {
        return service.findEditableAvaliacoes();
    }


    public AvaliacaoNotaDTO findNotaByAvaliacaoID(int index)
    {
        return service.findNotaByAvaliacaoID(index);
    }

    public void atualizarNota(AvaliacaoNotaDTO notaAtual)
    {
        service.atualizarNota(notaAtual);
    }
}


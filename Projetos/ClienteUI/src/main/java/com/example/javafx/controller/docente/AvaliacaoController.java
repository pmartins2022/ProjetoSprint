package com.example.javafx.controller.docente;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.factory.AvaliacaoDTOFactory;
import com.example.javafx.service.AvaliacaoService;
import com.example.javafx.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AvaliacaoController
{
    @Autowired
    private AvaliacaoService service;

    @Autowired
    private AvaliacaoDTOFactory avaliacaoDTOFactory;

    public List<AvaliacaoDTO> findEditableAvaliacoes()
    {
        return service.findEditableAvaliacoes();
    }
}

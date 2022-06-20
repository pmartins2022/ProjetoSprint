package com.example.javafx.controller.docente;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.factory.AvaliacaoDTOFactory;
import com.example.javafx.repository.rest.ProjetoRestRepository;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProjetoController
{
    @Autowired
    private ProjetoRestRepository projetoRestRepository;

    @Autowired
    private AvaliacaoDTOFactory avaliacaoDTOFactory;

    public AvaliacaoDTO createAvaliacao(String idMomentoAvaliacao, String idOrientador, String idPresidente,
                                        String idArguente, String idProjeto, String idConteudo)
    {
        AvaliacaoDTO avaliacaoDTO = avaliacaoDTOFactory.create(Long.parseLong(idMomentoAvaliacao), Long.parseLong(idOrientador),
                Long.parseLong(idPresidente), Long.parseLong(idArguente), Long.parseLong(idProjeto),
                Long.parseLong(idConteudo));

        return projetoRestRepository.createAvaliacao(avaliacaoDTO);
    }
}

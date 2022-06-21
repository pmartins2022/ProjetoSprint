package com.example.javafx.controller.docente;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.ConteudoDTO;
import com.example.javafx.dto.ProjetoDTO;
import com.example.javafx.dto.enums.EstadoConteudo;
import com.example.javafx.dto.factory.AvaliacaoDTOFactory;
import com.example.javafx.repository.rest.ProjetoRestRepository;
import com.example.javafx.service.ProjetoService;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjetoController
{
    @Autowired
    private ProjetoService service;

    @Autowired
    private AvaliacaoDTOFactory avaliacaoDTOFactory;

    public AvaliacaoDTO createAvaliacao(String idMomentoAvaliacao, String idOrientador, String idPresidente,
                                        String idArguente, String idProjeto, String idConteudo)
    {
        AvaliacaoDTO avaliacaoDTO = avaliacaoDTOFactory.create(Long.parseLong(idMomentoAvaliacao), Long.parseLong(idOrientador),
                Long.parseLong(idPresidente), Long.parseLong(idArguente), Long.parseLong(idProjeto),
                Long.parseLong(idConteudo));

        return service.createAvaliacao(avaliacaoDTO);
    }

    public List<ConteudoDTO> findAllConteudoOfOrientador(Long orientadorID)
    {
        List<ProjetoDTO> projetoList = service.findAllByOrientadorID(orientadorID);

        List<ConteudoDTO> conteudoList = new ArrayList<>();

        for(ProjetoDTO proj: projetoList)
        {
            conteudoList.addAll(service.findAllByIdProjeto(proj.getId()));
        }

        conteudoList = conteudoList.stream()
                .filter(conteudo -> conteudo.getEstadoConteudo().equals(EstadoConteudo.PENDENTE)).toList();

        return conteudoList;
    }

    public Boolean acceptConteudo(Long conteudoID)
    {
        return service.acceptConteudo(conteudoID);
    }

    public Boolean rejectConteudo(Long conteudoID)
    {
        return service.rejectConteudo(conteudoID);
    }
}

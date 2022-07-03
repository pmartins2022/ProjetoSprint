package com.example.javafx.controller.docente;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.AvaliacaoNotaDTO;
import com.example.javafx.dto.ConteudoDTO;
import com.example.javafx.dto.ProjetoDTO;
import com.example.javafx.dto.enums.EstadoConteudo;
import com.example.javafx.dto.factory.AvaliacaoDTOFactory;
import com.example.javafx.model.LoginContext;
import com.example.javafx.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controller para projeto
 */
@Controller
public class ProjetoController
{
    @Autowired
    private ProjetoService service;

    @Autowired
    private AvaliacaoDTOFactory avaliacaoDTOFactory;

    /**
     * Criar uma nova avaliacao
     * @param idMomentoAvaliacao id do momento avaliacao
     * @param idOrientador id do orientador
     * @param idPresidente id do presidente
     * @param idArguente id do arguente
     * @param idProjeto id do projeto
     * @param idConteudo id do conteudo
     * @return a avaliacao criada
     */
    public AvaliacaoDTO createAvaliacao(String idMomentoAvaliacao, String idOrientador, String idPresidente,
                                        String idArguente, String idProjeto, String idConteudo)
    {
        AvaliacaoDTO avaliacaoDTO = avaliacaoDTOFactory.create(Long.parseLong(idMomentoAvaliacao), Long.parseLong(idOrientador),
                Long.parseLong(idPresidente), Long.parseLong(idArguente), Long.parseLong(idProjeto),
                Long.parseLong(idConteudo));

        return service.createAvaliacao(avaliacaoDTO);
    }

    /**
     * Encontrar todos os conteudos de um certo orientador
     * @param orientadorID id do orientador
     * @return a lista
     */
    public List<ConteudoDTO> findAllConteudoOfOrientador(Long orientadorID)
    {
        System.out.println("Encontrar por id: "+orientadorID);

        List<ProjetoDTO> projetoList = service.findAllByOrientadorID(orientadorID);

        List<ConteudoDTO> conteudoList = new ArrayList<>();

        for(ProjetoDTO proj: projetoList)
        {
            conteudoList.addAll(service.findAllByIdProjeto(proj.getId()));
        }

        conteudoList = conteudoList.stream()
                .filter(conteudo -> conteudo.getEstadoConteudo().equals(EstadoConteudo.PENDENTE.name())).toList();

        return conteudoList;
    }

    /**
     * Aceitar um conteudo
     * @param conteudoID o id do conteudo
     */
    public void acceptConteudo(Long conteudoID)
    {
        service.acceptConteudo(conteudoID);
    }

    /**
     * Rejeitar um conteudo
     * @param conteudoID id do conteudo
     */
    public void rejectConteudo(Long conteudoID)
    {
        service.rejectConteudo(conteudoID);
    }

    /**
     * Emcontrar todas as notas a partir do ruc id e do estado
     * @param estado o estado
     * @return lista de notas
     */
    public List<AvaliacaoNotaDTO> findAllAvaliacaoNotaByRucIDAndEstado(String estado)
    {
        return service.findAllAvaliacaoNotaByRucIDAndEstado(LoginContext.getCurrentUser().getId(), estado);
    }

    /**
     * Rever uma nota de avaliacao
     * @param dto a nota
     * @param avaliacao informacao da avaliacao
     */
    public void reviewAvaliacaoNota(AvaliacaoNotaDTO dto, String avaliacao)
    {
        service.reviewAvaliacaoNota(dto.getId(), avaliacao);
    }

}

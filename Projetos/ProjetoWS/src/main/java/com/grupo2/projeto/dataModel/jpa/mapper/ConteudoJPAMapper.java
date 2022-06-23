package com.grupo2.projeto.dataModel.jpa.mapper;

import com.grupo2.projeto.dataModel.jpa.ConteudoJPA;
import com.grupo2.projeto.model.Conteudo;
import org.springframework.stereotype.Component;

@Component
public class ConteudoJPAMapper
{
    public Conteudo toModel(ConteudoJPA jpa)
    {
        return new Conteudo(jpa.getId(), jpa.getIdProjeto(),jpa.getTitulo(), jpa.getCaminhoDocumento(),jpa.getDocumento(), jpa.getLinguagemDocumento(), jpa.getEstadoConteudo());
    }
    public ConteudoJPA toJpa(Conteudo conteudo)
    {
        return new ConteudoJPA(conteudo.getId(), conteudo.getIdProjeto(),conteudo.getTitulo(), conteudo.getCaminhoDocumento(),conteudo.getDocumento(), conteudo.getLinguagemDocumento(), conteudo.getEstadoConteudo());
    }
}

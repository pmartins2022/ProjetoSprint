package com.grupo2.projeto.jpa.mapper;

import com.grupo2.projeto.jpa.ConteudoJPA;
import com.grupo2.projeto.model.Conteudo;
import org.springframework.stereotype.Component;

@Component
public class ConteudoJPAMapper
{
    public Conteudo toModel(ConteudoJPA jpa)
    {
        return new Conteudo(jpa.getId(), jpa.getTitulo(), jpa.getDocumento(), jpa.getLinguagemDocumento());
    }
    public ConteudoJPA toJpa(Conteudo conteudo)
    {
        return new ConteudoJPA(conteudo.getId(), conteudo.getTitulo(), conteudo.getDocumento(), conteudo.getLinguagemDocumento());
    }
}

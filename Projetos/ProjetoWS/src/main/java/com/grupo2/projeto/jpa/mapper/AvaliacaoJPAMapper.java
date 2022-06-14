package com.grupo2.projeto.jpa.mapper;

import com.grupo2.projeto.jpa.AvaliacaoJPA;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.factory.AvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoJPAMapper
{
    @Autowired
    private AvaliacaoFactory factory;

    @Autowired
    private ConteudoJPAMapper mapper;

    public Avaliacao toModel(AvaliacaoJPA jpa)
    {
        return factory.createAvaliacao(jpa.getId(),jpa.getIdProjeto(),jpa.getIdMomentoAvaliacao(),jpa.getConteudo().getId(), jpa.getNota());
    }
}

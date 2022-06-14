package com.grupo2.projeto.jpa.mapper;

import com.grupo2.projeto.jpa.MomentoAvaliacaoJPA;
import com.grupo2.projeto.model.MomentoAvaliacao;
import com.grupo2.projeto.model.factory.MomentoAvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoJPAMapper {
    @Autowired
    private MomentoAvaliacaoFactory momentoAvaliacaoFactory;

    public MomentoAvaliacao toModel(MomentoAvaliacaoJPA jpa)
    {
        return momentoAvaliacaoFactory.createMomentoAvaliacao(jpa.getId(), jpa.getProjetoId());
    }

    public MomentoAvaliacaoJPA toJPA(MomentoAvaliacao momento)
    {
        return new MomentoAvaliacaoJPA(momento.getId(), momento.getProjetoId());
    }
}

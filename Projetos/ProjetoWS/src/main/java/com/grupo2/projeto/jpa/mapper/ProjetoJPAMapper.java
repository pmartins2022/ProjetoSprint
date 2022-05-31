package com.grupo2.projeto.jpa.mapper;

import com.grupo2.projeto.jpa.ProjetoJPA;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.model.factory.ProjetoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjetoJPAMapper
{
    @Autowired
    private ProjetoFactory factory;

    public Projeto toModel(ProjetoJPA jpa)
    {
        return factory.createProjeto(jpa.getId(),jpa.getPropostaId(),jpa.getEstudanteId(),jpa.getOrientadorId());
    }

    public ProjetoJPA toJpa(Projeto projeto)
    {
        return new ProjetoJPA(projeto.getId(), projeto.getPropostaId(), projeto.getEstudanteId(), projeto.getOrientadorId());
    }
}

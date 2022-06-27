package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.MomentoAvaliacaoJPA;
import com.grupo2.edicaouc.model.MomentoAvaliacao;
import com.grupo2.edicaouc.model.factory.MomentoAvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoJPAMapper
{
    @Autowired
    private MomentoAvaliacaoFactory factory;

    public MomentoAvaliacao toModel(MomentoAvaliacaoJPA jpa)
    {
        return factory.create(jpa.getId(),jpa.getIdEdicao(), jpa.getDenominacao());
    }

    public MomentoAvaliacaoJPA toJPA (MomentoAvaliacao model)
    {
        return new MomentoAvaliacaoJPA(model.getId(), model.getIdEdicao(), model.getDenominacao());
    }
}

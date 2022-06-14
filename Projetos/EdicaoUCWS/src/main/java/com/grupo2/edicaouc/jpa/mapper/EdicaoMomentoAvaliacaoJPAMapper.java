package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.EdicaoMomentoAvaliacaoID;
import com.grupo2.edicaouc.jpa.EdicaoMomentoAvaliacaoJPA;
import com.grupo2.edicaouc.model.EdicaoMomentoAvaliacao;
import org.springframework.stereotype.Component;

@Component
public class EdicaoMomentoAvaliacaoJPAMapper
{
    public EdicaoMomentoAvaliacao toModel(EdicaoMomentoAvaliacaoJPA jpa)
    {
        return new EdicaoMomentoAvaliacao(jpa.getId().getIdEdicao(),jpa.getId().getIdMomentoAvaliacao());
    }

    public EdicaoMomentoAvaliacaoJPA toJPA(EdicaoMomentoAvaliacao model)
    {
        EdicaoMomentoAvaliacaoID id = new EdicaoMomentoAvaliacaoID(model.getIdEdicao(),model.getIdMomentoAvaliacao());
        return new EdicaoMomentoAvaliacaoJPA(id);
    }
}
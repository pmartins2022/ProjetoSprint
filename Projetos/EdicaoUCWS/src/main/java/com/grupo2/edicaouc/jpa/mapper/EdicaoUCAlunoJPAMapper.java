package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCAlunoJPAMapper
{
    public EdicaoUCAluno toModel(EdicaoUCAlunoJPA edicaoUCAlunoJPA)
    {
        return new EdicaoUCAluno(edicaoUCAlunoJPA.getId().getEdicaoUCID(), edicaoUCAlunoJPA.getId().getAlunoID());
    }

    public EdicaoUCAlunoJPA toJPA(EdicaoUCAluno edicaoUCAluno)
    {
        EdicaoUCAlunoID id = new EdicaoUCAlunoID(edicaoUCAluno.getEdicaoUCID(), edicaoUCAluno.getAlunoID());
        return new EdicaoUCAlunoJPA(id);
    }
}

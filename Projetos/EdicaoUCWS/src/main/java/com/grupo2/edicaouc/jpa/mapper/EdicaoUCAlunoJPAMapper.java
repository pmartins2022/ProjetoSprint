package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCAlunoJPAMapper
{
    public EdicaoUCAlunoJPA toJPA(EdicaoUCAluno edicaoUCAluno)
    {
        return new EdicaoUCAlunoJPA(edicaoUCAluno.getIdEdicaoUC(), edicaoUCAluno.getIdAluno());
    }

    public EdicaoUCAluno toModel(EdicaoUCAlunoJPA edicaoUCAlunoJPA)
    {
        return new EdicaoUCAluno(edicaoUCAlunoJPA.getIdEdicaoUC(), edicaoUCAlunoJPA.getIdAluno());
    }
}

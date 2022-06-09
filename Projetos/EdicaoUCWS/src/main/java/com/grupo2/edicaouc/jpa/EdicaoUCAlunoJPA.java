package com.grupo2.edicaouc.jpa;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EdicaoUCAluno")
public class EdicaoUCAlunoJPA
{
    @EmbeddedId
    private EdicaoUCAlunoID id;

    public EdicaoUCAlunoJPA()
    {
    }

    public EdicaoUCAlunoJPA(EdicaoUCAlunoID id)
    {
        this.id = id;
    }

    public EdicaoUCAlunoID getId()
    {
        return id;
    }
}

package com.grupo2.edicaouc.jpa;

import javax.persistence.*;

@Entity
@Table(name = "MomentoAvaliacaoEdicao")
public class EdicaoMomentoAvaliacaoJPA
{
    @EmbeddedId
    EdicaoMomentoAvaliacaoID id;

    public EdicaoMomentoAvaliacaoJPA()
    {
    }

    public EdicaoMomentoAvaliacaoJPA(EdicaoMomentoAvaliacaoID id)
    {
        this.id = id;
    }

    public EdicaoMomentoAvaliacaoID getId()
    {
        return id;
    }
}
package com.grupo2.edicaouc.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "EdicaoUCAluno")
public class EdicaoUCAlunoJPA
{
    @Id
    private String fakeComposedKey;

    private Long idEdicaoUC;

    private Long idAluno;

    public EdicaoUCAlunoJPA()
    {
    }

    public EdicaoUCAlunoJPA(Long idEdicaoUC, Long idAluno)
    {
        this.idEdicaoUC = idEdicaoUC;
        this.idAluno = idAluno;
        fakeComposedKey = idEdicaoUC + "," + idAluno;
    }

    public Long getIdEdicaoUC()
    {
        return idEdicaoUC;
    }

    public Long getIdAluno()
    {
        return idAluno;
    }

    public String getFakeComposedKey()
    {
        return fakeComposedKey;
    }
}

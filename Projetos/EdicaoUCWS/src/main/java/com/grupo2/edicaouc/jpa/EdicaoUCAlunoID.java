package com.grupo2.edicaouc.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EdicaoUCAlunoID implements Serializable
{
    private Long edicaoUCID;
    private Long alunoID;

    public EdicaoUCAlunoID()
    {
    }

    public EdicaoUCAlunoID(Long edicaoUCID, Long alunoID)
    {
        this.edicaoUCID = edicaoUCID;
        this.alunoID = alunoID;
    }

    public Long getEdicaoUCID()
    {
        return edicaoUCID;
    }

    public Long getAlunoID()
    {
        return alunoID;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdicaoUCAlunoID that = (EdicaoUCAlunoID) o;
        return Objects.equals(edicaoUCID, that.edicaoUCID) && Objects.equals(alunoID, that.alunoID);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(edicaoUCID, alunoID);
    }
}

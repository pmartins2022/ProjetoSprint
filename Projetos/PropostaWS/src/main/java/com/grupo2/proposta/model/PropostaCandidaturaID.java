package com.grupo2.proposta.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PropostaCandidaturaID implements Serializable
{
    private Long idproposta;
    private Long idaluno;


    public PropostaCandidaturaID()
    {
    }

    public PropostaCandidaturaID(Long idproposta, Long idAluno)
    {
        this.idproposta = idproposta;
        this.idaluno = idAluno;
    }

    public Long getidproposta()
    {
        return idproposta;
    }

    public Long getIdaluno()
    {
        return idaluno;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PropostaCandidaturaID)) return false;
        PropostaCandidaturaID that = (PropostaCandidaturaID) o;
        return getidproposta().equals(that.getidproposta()) && getIdaluno().equals(that.getIdaluno());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getidproposta(), getIdaluno());
    }
}

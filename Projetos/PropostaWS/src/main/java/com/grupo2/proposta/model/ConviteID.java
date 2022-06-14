package com.grupo2.proposta.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConviteID implements Serializable
{
    private Long idaluno;
    private Long iddocente;
    private Long idproposta;

    public ConviteID()
    {
    }

    public ConviteID(Long idAluno, Long idDocente, Long idProposta)
    {
        this.idaluno = idAluno;
        this.iddocente = idDocente;
        this.idproposta = idProposta;
    }

    public Long getIdaluno()
    {
        return idaluno;
    }

    public Long getIddocente()
    {
        return iddocente;
    }

    public Long getIdproposta()
    {
        return idproposta;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ConviteID)) return false;
        ConviteID conviteID = (ConviteID) o;
        return getIdaluno().equals(conviteID.getIdaluno()) && getIddocente().equals(conviteID.getIddocente()) && getIdproposta().equals(conviteID.getIdproposta());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getIdaluno(), getIddocente(), getIdproposta());
    }
}

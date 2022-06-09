package com.grupo2.proposta.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConviteID implements Serializable
{
    private Long idAluno;
    private Long idDocente;
    private Long idProposta;

    public ConviteID()
    {
    }

    public ConviteID(Long idAluno, Long idDocente, Long idProposta)
    {
        this.idAluno = idAluno;
        this.idDocente = idDocente;
        this.idProposta = idProposta;
    }

    public Long getIdAluno()
    {
        return idAluno;
    }

    public Long getIdDocente()
    {
        return idDocente;
    }

    public Long getIdProposta()
    {
        return idProposta;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ConviteID)) return false;
        ConviteID conviteID = (ConviteID) o;
        return getIdAluno().equals(conviteID.getIdAluno()) && getIdDocente().equals(conviteID.getIdDocente()) && getIdProposta().equals(conviteID.getIdProposta());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getIdAluno(), getIdDocente(), getIdProposta());
    }
}

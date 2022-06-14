package com.grupo2.proposta.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PropostaInscricaoID implements Serializable
{
    private Long idProposta;
    private Long idAluno;


    public PropostaInscricaoID()
    {
    }

    public PropostaInscricaoID(Long idProposta, Long idAluno)
    {
        this.idProposta = idProposta;
        this.idAluno = idAluno;
    }

    public Long getIdProposta()
    {
        return idProposta;
    }

    public Long getIdAluno()
    {
        return idAluno;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PropostaInscricaoID)) return false;
        PropostaInscricaoID that = (PropostaInscricaoID) o;
        return getIdProposta().equals(that.getIdProposta()) && getIdAluno().equals(that.getIdAluno());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getIdProposta(), getIdAluno());
    }
}

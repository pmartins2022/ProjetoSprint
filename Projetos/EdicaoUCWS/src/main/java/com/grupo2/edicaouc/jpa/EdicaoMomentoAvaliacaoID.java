package com.grupo2.edicaouc.jpa;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class EdicaoMomentoAvaliacaoID implements Serializable
{
    private Long idEdicao;
    private Long idMomentoAvaliacao;

    public EdicaoMomentoAvaliacaoID()
    {
    }

    public EdicaoMomentoAvaliacaoID(Long idEdicao, Long idMomentoAvaliacao)
    {
        this.idEdicao = idEdicao;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
    }

    public Long getIdEdicao()
    {
        return idEdicao;
    }

    public Long getIdMomentoAvaliacao()
    {
        return idMomentoAvaliacao;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EdicaoMomentoAvaliacaoID that = (EdicaoMomentoAvaliacaoID) o;

        if (!idEdicao.equals(that.idEdicao)) return false;
        return idMomentoAvaliacao.equals(that.idMomentoAvaliacao);
    }

    @Override
    public int hashCode()
    {
        int result = idEdicao.hashCode();
        result = 31 * result + idMomentoAvaliacao.hashCode();
        return result;
    }
}

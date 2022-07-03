package com.grupo2.edicaouc.model;

import java.util.Objects;

/**
 * Classe dominio para objetos MomentoAvaliacao
 */
public class MomentoAvaliacao
{
    private Long id;
    private Long idEdicao;
    private String denominacao;

    public MomentoAvaliacao(Long id, Long idEdicao, String denominacao)
    {
        this.id = id;
        this.idEdicao = idEdicao;
        this.denominacao = denominacao;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    public Long getIdEdicao()
    {
        return idEdicao;
    }

    public void setIdEdicao(Long idEdicao)
    {
        this.idEdicao = idEdicao;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MomentoAvaliacao that = (MomentoAvaliacao) o;
        return denominacao.equals(that.denominacao);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, denominacao);
    }
}

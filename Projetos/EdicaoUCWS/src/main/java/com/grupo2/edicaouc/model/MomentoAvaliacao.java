package com.grupo2.edicaouc.model;

public class MomentoAvaliacao
{
    private Long id;
    private String denominacao;

    public MomentoAvaliacao(Long id, String denominacao)
    {
        this.id = id;
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
}

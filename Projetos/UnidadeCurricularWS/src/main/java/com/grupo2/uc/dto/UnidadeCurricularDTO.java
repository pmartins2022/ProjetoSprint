package com.grupo2.uc.dto;

public class UnidadeCurricularDTO
{
    private String sigla;
    private String denominacao;

    public UnidadeCurricularDTO(){}

    public UnidadeCurricularDTO(String sigla, String denominacao)
    {
        this.sigla = sigla;
        this.denominacao = denominacao;
    }

    public String getSigla()
    {
        return sigla;
    }

    public void setSigla(String sigla)
    {
        this.sigla = sigla;
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

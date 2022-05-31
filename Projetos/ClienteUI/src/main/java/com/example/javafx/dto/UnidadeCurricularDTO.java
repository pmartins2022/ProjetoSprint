package com.example.javafx.dto;

public class UnidadeCurricularDTO
{
    private String sigla;
    private String denominacao;

    public UnidadeCurricularDTO()
    {
    }

    public UnidadeCurricularDTO(String sigla, String denominacao)
    {
        this.sigla = sigla;
        this.denominacao = denominacao;
    }

    public String getSigla()
    {
        return sigla;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }

    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    @Override
    public String toString()
    {
        return "UnidadeCurricularDTO{" +
                "sigla='" + sigla + '\'' +
                ", denominacao='" + denominacao + '\'' +
                '}';
    }
}

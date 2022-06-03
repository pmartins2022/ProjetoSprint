package com.grupo2.uc.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadeCurricularDTO that = (UnidadeCurricularDTO) o;
        return sigla.equals(that.sigla) && denominacao.equals(that.denominacao);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(sigla, denominacao);
    }
}

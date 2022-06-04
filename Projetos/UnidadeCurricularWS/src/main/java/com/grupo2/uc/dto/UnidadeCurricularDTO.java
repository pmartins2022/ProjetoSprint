package com.grupo2.uc.dto;

import java.util.Objects;

/**
 * Classe DTO da UnidadeCurricular
 */
public class UnidadeCurricularDTO
{
    /**
     * Sigla da UnidadeCurricular
     */
    private String sigla;
    /**
     * Denominacao da UnidadeCurricular
     */
    private String denominacao;

    /**
     * Instancia UnidadeCurricularDTO sem parametros
     */
    public UnidadeCurricularDTO(){}

    /**
     * Instancia UnidadeCurricularDTO com parametros sigla e denominacao
     */
    public UnidadeCurricularDTO(String sigla, String denominacao)
    {
        this.sigla = sigla;
        this.denominacao = denominacao;
    }

    /**
     * Devolve sigla da UnidadeCurriularDTO
     * @return sigla da UnidadeCurriularDTO
     */
    public String getSigla()
    {
        return sigla;
    }

    /**
     * Modifica sigla da UnidadeCurriularDTO
     * @param sigla nova sigla da UnidadeCurriularDTO
     */
    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }

    /**
     * Devolve denominacao da UnidadeCurriularDTO
     * @return denominacao da UnidadeCurriularDTO
     */
    public String getDenominacao()
    {
        return denominacao;
    }

    /**
     * Modifica denominacao da UnidadeCurriularDTO
     * @param denominacao nova denominacao da UnidadeCurriularDTO
     */
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

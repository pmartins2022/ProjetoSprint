package com.grupo2.edicaouc.dto;

import java.util.Objects;

/**
 * Classe DTO para objetos do tipo AnoLetivo
 */
public class AnoLetivoDTO
{
    private String sigla;

    public AnoLetivoDTO(){}

    public AnoLetivoDTO(String sigla)
    {
        this.sigla = sigla;
    }

    public String getSigla()
    {
        return sigla;
    }

    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnoLetivoDTO that = (AnoLetivoDTO) o;
        return Objects.equals(sigla, that.sigla);
    }


    @Override
    public String toString()
    {
        return "AnoLetivoDTO{" +
                "sigla='" + sigla + '\'' +
                '}';
    }
}
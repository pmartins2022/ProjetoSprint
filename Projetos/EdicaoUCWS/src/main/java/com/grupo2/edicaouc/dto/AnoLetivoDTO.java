package com.grupo2.edicaouc.dto;

import java.util.Objects;

/**
 * Classe DTO de AnoLetivo
 */
public class AnoLetivoDTO
{
    /**
     * Sigla de AnoLetivo
     */
    private String sigla;

    /**
     * Inicializa o AnoLetivoDTO sem parametros
     */
    public AnoLetivoDTO(){}

    /**
     * Inicializa a sigla de Ano LetivoDTO
     * com sigla recebida por parâmetro
     * @param sigla é a sigla de Ano Letivo
     */
    public AnoLetivoDTO(String sigla)
    {
        this.sigla = sigla;
    }

    /**
     * Devolve a sigla de Ano Letivo
     * @return sigla de Ano Letivo
     */
    public String getSigla()
    {
        return sigla;
    }

    /**
     * Modifica a sigla de Ano Letivo
     * @param sigla nova sigla de Ano Letivo
     */
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
package com.example.javafx.dto;

/**
 * Classe de dados para ano letivo.
 */
public class AnoLetivoDTO
{
    private String sigla;

    public AnoLetivoDTO()
    {
    }

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
    public String toString()
    {
        return String.format("Sigla: %s", sigla);
    }
}

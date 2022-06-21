package com.example.javafx.dto;

/**
 * Classe de dados para edicao UC.
 */
public class EdicaoUCDTO
{
    private Long id;
    private String ucCode;
    private String anoLetivoCode;

    private Long rucID;
    public EdicaoUCDTO()
    {
    }

    public EdicaoUCDTO(String ucCode, String anoLetivoCode, Long rucID)
    {
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
    }

    public EdicaoUCDTO(Long id, String ucCode, String anoLetivoCode, Long rucID)
    {
        this.id = id;
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
    }

    public String getUcCode()
    {
        return ucCode;
    }

    public void setUcCode(String ucCode)
    {
        this.ucCode = ucCode;
    }

    public String getAnoLetivoCode()
    {
        return anoLetivoCode;
    }

    public void setAnoLetivoCode(String anoLetivoCode)
    {
        this.anoLetivoCode = anoLetivoCode;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getRucID() {
        return rucID;
    }

    public void setRucID(Long rucID) {
        this.rucID = rucID;
    }

    @Override
    public String toString()
    {
        return String.format("Unidade Curricular: %s; Ano Letivo: %s", ucCode, anoLetivoCode);
    }
}

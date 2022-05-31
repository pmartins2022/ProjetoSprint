package com.example.javafx.dto;

public class EdicaoUCDTO
{
    private Long id;
    private String ucCode;
    private String anoLetivoCode;

    public EdicaoUCDTO()
    {
    }

    public EdicaoUCDTO(String ucCode, String anoLetivoCode)
    {
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
    }

    public EdicaoUCDTO(Long id, String ucCode, String anoLetivoCode)
    {
        this.id = id;
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
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

    @Override
    public String toString()
    {
        return "EdicaoUCDTO{" +
                "UCCode='" + ucCode + '\'' +
                "anoLetivoCode='" + anoLetivoCode + '\'' +
                '}';
    }
}

package com.grupo2.edicaouc.model;

public class EdicaoUC
{
    private Long id;
    private String UCCode;
    private String anoLetivoCode;

    public EdicaoUC()
    {
    }

    public EdicaoUC(String UCCode, String anoLetivoCode)
    {
        this.UCCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
    }

    public EdicaoUC(Long id, String UCCode, String anoLetivoCode)
    {
        this.id = id;
        this.UCCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
    }

    public String getUCCode()
    {
        return UCCode;
    }

    public void setUCCode(String UCCode)
    {
        this.UCCode = UCCode;
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
}

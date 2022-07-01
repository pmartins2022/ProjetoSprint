package com.example.javafx.dto;

import com.example.javafx.dto.enums.EstadoEdicaoUC;

/**
 * Classe de dados para edicao UC.
 */
public class EdicaoUCDTO
{
    private Long id;
    private String ucCode;
    private String anoLetivoCode;

    private Long rucID;
    private EstadoEdicaoUC estadoEdicaoUC;

    public EdicaoUCDTO()
    {
    }

    public EdicaoUCDTO(String ucCode, String anoLetivoCode, Long rucID)
    {
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        this.estadoEdicaoUC = EstadoEdicaoUC.PENDENTE;
    }

    public EdicaoUCDTO(Long id, String ucCode, String anoLetivoCode, Long rucID)
    {
        this.id = id;
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        this.estadoEdicaoUC = EstadoEdicaoUC.PENDENTE;
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

    public EstadoEdicaoUC getEstadoEdicaoUC()
    {
        return estadoEdicaoUC;
    }

    public void setEstadoEdicaoUC(EstadoEdicaoUC estadoEdicaoUC)
    {
        this.estadoEdicaoUC = estadoEdicaoUC;
    }

    @Override
    public String toString()
    {
        return "EdicaoUCDTO{" +
                "id=" + id +
                ", ucCode='" + ucCode + '\'' +
                ", anoLetivoCode='" + anoLetivoCode + '\'' +
                ", rucID=" + rucID +
                ", estadoEdicaoUC=" + estadoEdicaoUC +
                '}';
    }
}

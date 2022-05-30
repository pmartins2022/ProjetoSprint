package com.grupo2.proposta.dto;

public class EdicaoUCDTO
{
    private Long id;
    private String unidadeCurricularId;
    private String anoLetivoId;

    public EdicaoUCDTO()
    {
    }

    public EdicaoUCDTO(Long id, String unidadeCurricularId, String anoLetivoId)
    {
        this.id = id;
        this.unidadeCurricularId = unidadeCurricularId;
        this.anoLetivoId = anoLetivoId;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUnidadeCurricularId()
    {
        return unidadeCurricularId;
    }

    public void setUnidadeCurricularId(String unidadeCurricularId)
    {
        this.unidadeCurricularId = unidadeCurricularId;
    }

    public String getAnoLetivoId()
    {
        return anoLetivoId;
    }

    public void setAnoLetivoId(String anoLetivoId)
    {
        this.anoLetivoId = anoLetivoId;
    }
}

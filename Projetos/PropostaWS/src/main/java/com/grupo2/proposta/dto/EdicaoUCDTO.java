package com.grupo2.proposta.dto;

/**
 * Classe DTO que contem informacao sobre uma edicao de uc.
 */
public class EdicaoUCDTO
{
    private Long id;
    private String unidadeCurricularId;
    private String anoLetivoId;
    private Long rucID;

    public EdicaoUCDTO()
    {
    }

    public EdicaoUCDTO(Long id, String unidadeCurricularId, String anoLetivoId, Long rucID)
    {
        this.id = id;
        this.unidadeCurricularId = unidadeCurricularId;
        this.anoLetivoId = anoLetivoId;
        this.rucID = rucID;
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

    public Long getRucID()
    {
        return rucID;
    }

    public void setRucID(Long rucID)
    {
        this.rucID = rucID;
    }
}

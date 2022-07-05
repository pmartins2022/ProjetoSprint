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

    /**
     * Metodo que inicializa um EdicaoUCDTO sem parametros
     */
    public EdicaoUCDTO()
    {
    }

    /**
     * Inicializa o id, unidadeCurricularId, anoLetivoId e rucID da EdicaoUCDTO com id,
     * unidadeCurricularId, anoLetivoId, rucID
     * @param id e o id da EdicaoUC
     * @param unidadeCurricularId e o id da unidadeCurricular
     * @param anoLetivoId e o id do ano letivo
     * @param rucID e o id do ruc
     */
    public EdicaoUCDTO(Long id, String unidadeCurricularId, String anoLetivoId, Long rucID)
    {
        this.id = id;
        this.unidadeCurricularId = unidadeCurricularId;
        this.anoLetivoId = anoLetivoId;
        this.rucID = rucID;
    }

    /**
     * Devolve o id da EdicaoUC
     * @return o id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id da EdicaoUC
     * @param id o novo id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devolve o id da unidadeCurricular
     * @return o id da unidadeCurricular
     */
    public String getUnidadeCurricularId()
    {
        return unidadeCurricularId;
    }

    /**
     *  Modifica o id da unidadeCurricular
     * @param unidadeCurricularId novo id da unidadeCurricular
     */
    public void setUnidadeCurricularId(String unidadeCurricularId)
    {
        this.unidadeCurricularId = unidadeCurricularId;
    }

    /**
     * Devolve o id do anoLetivo
     * @return o id do anoLetivo
     */
    public String getAnoLetivoId()
    {
        return anoLetivoId;
    }

    /**
     * Modifica o id da anoLetivo
     * @param anoLetivoId o novi id do anoLetivo
     */
    public void setAnoLetivoId(String anoLetivoId)
    {
        this.anoLetivoId = anoLetivoId;
    }

    /**
     * Devolve o id do RUC
     * @return id do RUC
     */
    public Long getRucID()
    {
        return rucID;
    }

    /**
     * Modifica o id da RUC
     * @param rucID o novo id do RUC
     */
    public void setRucID(Long rucID)
    {
        this.rucID = rucID;
    }
}

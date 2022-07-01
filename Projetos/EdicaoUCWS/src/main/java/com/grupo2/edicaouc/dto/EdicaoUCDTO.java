package com.grupo2.edicaouc.dto;

import com.grupo2.edicaouc.model.EstadoEdicaoUC;

/**
 * Classe DTO de EdicaoUC
 */
public class EdicaoUCDTO
{
    /**
     * Id de Edição de Unidade Curricular
     */
    private Long id;
    /**
     * ucCode de Edição de Unidade Curricular
     */
    private String ucCode;
    /**
     * anoLetivoCode de Edição de Unidade Curricular
     */
    private String anoLetivoCode;
    private Long rucID;
    private EstadoEdicaoUC estadoEdicaoUC;

    /**
     * Inicializa a EdicaoUCDTO sem parametros
     */
    public EdicaoUCDTO()
    {
    }

    /**
     * Inicializa o ucCode e anoLetivoCode da EdicaoUCDTO
     * com ucCode e anoLetivoCode recebidos por parâmetros
     * @param ucCode é o ucCode de Edição de Unidade Curricular
     * @param anoLetivoCode é o anoLetivoCode de Edição de Unidade Curricular
     */
    public EdicaoUCDTO(String ucCode, String anoLetivoCode, Long rucID, EstadoEdicaoUC estado)
    {
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        this.estadoEdicaoUC = estado;
    }

    /**
     * Inicializa o id, o ucCode e anoLetivoCode da EdicaoUCDTO
     * @param id é o id de EdicaoUCDTO
     * @param ucCode é o ucCode de EdicaoUCDTO
     * @param anoLetivoCode é o anoLetivoCode de EdicaoUCDTO
     */
    public EdicaoUCDTO(Long id, String ucCode, String anoLetivoCode, Long rucID, EstadoEdicaoUC estado)
    {
        this.id = id;
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        this.estadoEdicaoUC = estado;
    }


    /**
     * Devolve o ucCode de EdicaoUC
     * @return o ucCode de EdicaoUC
     */
    public String getUcCode()
    {
        return ucCode;
    }

    /**
     * Modifica o ucCode de EdicaoUC
     * @param ucCode novo ucCode de EdicaoUC
     */
    public void setUcCode(String ucCode)
    {
        this.ucCode = ucCode;
    }

    /**
     * Devolve o anoLetivoCode de EdicaoUC
     * @return o AnoLetivoCode de EdicaoUC
     */
    public String getAnoLetivoCode()
    {
        return anoLetivoCode;
    }

    /**
     * Modifica o anoLetivoCode de EdicaoUC
     * @param anoLetivoCode novo anoLetivoCode de EdicaoUC
     */
    public void setAnoLetivoCode(String anoLetivoCode)
    {
        this.anoLetivoCode = anoLetivoCode;
    }

    /**
     * Devolve o Id de EdicaoUC
     * @return o Id de EdicaoUC
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id de EdicaoUC
     * @param id novo id de EdicaoUC
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getRucID()
    {
        return rucID;
    }

    public void setRucID(Long rucID)
    {
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
                "UCCode='" + ucCode + '\'' +
                "anoLetivoCode='" + anoLetivoCode + '\'' +
                '}';
    }
}

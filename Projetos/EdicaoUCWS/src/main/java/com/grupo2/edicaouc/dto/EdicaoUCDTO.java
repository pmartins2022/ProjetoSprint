package com.grupo2.edicaouc.dto;

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
    public EdicaoUCDTO(String ucCode, String anoLetivoCode)
    {
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
    }

    /**
     * Inicializa o id, o ucCode e anoLetivoCode da EdicaoUCDTO
     * @param id é o id de EdicaoUCDTO
     * @param ucCode é o ucCode de EdicaoUCDTO
     * @param anoLetivoCode é o anoLetivoCode de EdicaoUCDTO
     */
    public EdicaoUCDTO(Long id, String ucCode, String anoLetivoCode)
    {
        this.id = id;
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
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

    @Override
    public String toString()
    {
        return "EdicaoUCDTO{" +
                "UCCode='" + ucCode + '\'' +
                "anoLetivoCode='" + anoLetivoCode + '\'' +
                '}';
    }
}

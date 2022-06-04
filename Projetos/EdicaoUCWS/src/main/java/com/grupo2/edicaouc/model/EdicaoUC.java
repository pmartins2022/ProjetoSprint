package com.grupo2.edicaouc.model;

/**
 * Classe de domínio de EdicaoUC
 */
public class EdicaoUC
{
    /**
     * Id de EdicaoUc
     */
    private Long id;
    /**
     * UCCode de EdicaoUC
     */
    private String UCCode;
    /**
     * anoLetivoCode de EdicaoUC
     */
    private String anoLetivoCode;

    /**
     * Inicializa a EdicaoUC sem parâmetros
     */
    public EdicaoUC()
    {
    }

    /**
     * Inicializa a EdicaoUC com UCCode e anoLetivoCode
     * @param UCCode Código de Unidade Curricular da Edicao
     * @param anoLetivoCode Ano Letivo da Edição
     */
    public EdicaoUC(String UCCode, String anoLetivoCode)
    {
        this.UCCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
    }
    /**
     * Inicializa a EdicaoUC com Id, UCCode e anoLetivoCode
     * @param id Id da Edicao
     * @param UCCode Código de Unidade Curricular da Edicao
     * @param anoLetivoCode Ano Letivo da Edição
     */
    public EdicaoUC(Long id, String UCCode, String anoLetivoCode)
    {
        this.id = id;
        this.UCCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
    }

    /**
     * Devolve o Código de Unidade Curricular da Edicao
     * @return o UCCode de Edicao
     */
    public String getUCCode()
    {
        return UCCode;
    }
    /**
     * Modifica o UCCode de EdicaoUC
     * @param UCCode novo UCCode de EdicaoUC
     */
    public void setUCCode(String UCCode)
    {
        this.UCCode = UCCode;
    }

    /**
     * Devolve o Ano Letivo da EdicaoUC
     * @return o Ano Letivo da EdicaoUC
     */
    public String getAnoLetivoCode()
    {
        return anoLetivoCode;
    }

    /**
     * Modifica o Ano Letivo da EdicaoUC
     * @param anoLetivoCode novo Ano Letivo da EdicaoUC
     */
    public void setAnoLetivoCode(String anoLetivoCode)
    {
        this.anoLetivoCode = anoLetivoCode;
    }

    /**
     * Devolve o Id da EdicaoUC
     * @return o Id da EdicaoUC
     */
    public Long getId()
    {
        return id;
    }
    /**
     * Modifica o Id da EdicaoUC
     * @param id novo Id da EdicaoUC
     */
    public void setId(Long id)
    {
        this.id = id;
    }
}

package com.grupo2.edicaouc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private Long rucID;

    private EstadoEdicaoUC estadoEdicaoUC;

    /**
     * Inicializa a EdicaoUC sem parâmetros
     */
    public EdicaoUC()
    {
        estadoEdicaoUC = EstadoEdicaoUC.PENDENTE;
    }

    /**
     * Inicializa a EdicaoUC com UCCode e anoLetivoCode
     * @param UCCode Código de Unidade Curricular da Edicao
     * @param anoLetivoCode Ano Letivo da Edição
     */
    public EdicaoUC(String UCCode, String anoLetivoCode, Long rucID)
    {
        this.UCCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        estadoEdicaoUC = EstadoEdicaoUC.PENDENTE;
    }
    /**
     * Inicializa a EdicaoUC com Id, UCCode e anoLetivoCode
     * @param id Id da Edicao
     * @param UCCode Código de Unidade Curricular da Edicao
     * @param anoLetivoCode Ano Letivo da Edição
     */
    public EdicaoUC(Long id, String UCCode, String anoLetivoCode, Long rucID)
    {
        this.id = id;
        this.UCCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        estadoEdicaoUC = EstadoEdicaoUC.PENDENTE;
    }

    public EdicaoUC(Long id, String UCCode, String anoLetivoCode, Long rucID, EstadoEdicaoUC estadoEdicaoUC)
    {
        this.id = id;
        this.UCCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        this.estadoEdicaoUC = estadoEdicaoUC;
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
     *
     * @param id novo Id da EdicaoUC
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

    public void activateEdicaoUC()
    {
        estadoEdicaoUC = EstadoEdicaoUC.ATIVA;
    }

    public void deactivateEdicaoUC()
    {
        estadoEdicaoUC = EstadoEdicaoUC.DESATIVA;
    }


    @Override
    public String toString()
    {
        return "EdicaoUC{" +
                "id=" + id +
                ", UCCode='" + UCCode + '\'' +
                ", anoLetivoCode='" + anoLetivoCode + '\'' +
                ", rucID=" + rucID +
                ", estadoEdicaoUC=" + estadoEdicaoUC +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdicaoUC edicaoUC = (EdicaoUC) o;
        return UCCode.equals(edicaoUC.UCCode) && anoLetivoCode.equals(edicaoUC.anoLetivoCode) && rucID.equals(edicaoUC.rucID) && estadoEdicaoUC == edicaoUC.estadoEdicaoUC;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, UCCode, anoLetivoCode, rucID, estadoEdicaoUC);
    }
}

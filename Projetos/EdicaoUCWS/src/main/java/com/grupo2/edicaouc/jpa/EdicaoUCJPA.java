package com.grupo2.edicaouc.jpa;


import com.grupo2.edicaouc.model.EstadoEdicaoUC;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe JPA de EdicaoUC
 */
@Entity
@Table(name="EdicaoUC")
public class EdicaoUCJPA
{
    /**
     * Id de EdicaoUC
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * ucCode de EdicaoUC
     */
    private String ucCode;

    /**
     * anoLetivoCode de EdicaoUC
     */
    private String anoLetivoCode;
    private Long rucID;
    private EstadoEdicaoUC estadoEdicaoUC;

    /**
     * Inicializa EdicaoUCJPA sem parâmetros
     */
    public EdicaoUCJPA()
    {
    }

    /**
     * Inicializa o UCCODE e anoLetivoCode da EdicaoUCJPA
     * UCCode e anoLetivoCode recebidos
     * @param UCCode é o UCCode de Edicao de Unidade Curricular
     * @param anoLetivoCode é o anoLetivoCode de Edicao de Unidade Curricular
     */
    public EdicaoUCJPA(String UCCode, String anoLetivoCode, Long rucID, EstadoEdicaoUC estadoEdicaoUC)
    {
        this.ucCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        this.estadoEdicaoUC = estadoEdicaoUC;
    }
    /**
     * Inicializa o Id, UCCODE e anoLetivoCode da EdicaoUCJPA
     * Id, UCCode e anoLetivoCode recebidos
     * @param id é o id de Edição de Unidade Curricular
     * @param ucCode é o UCCode de Edicao de Unidade Curricular
     * @param anoLetivoCode é o anoLetivoCode de Edicao de Unidade Curricular
     */
    public EdicaoUCJPA(Long id, String ucCode, String anoLetivoCode, Long rucID, EstadoEdicaoUC estadoEdicaoUC)
    {
        this.id = id;
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
        this.rucID = rucID;
        this.estadoEdicaoUC = estadoEdicaoUC;
    }

    /**
     * Devolve o UCCode de EdicaoUCJPA
     * @return o UCCode de EdicaoUCJPA
     */
    public String getUCCode()
    {
        return ucCode;
    }

    public String getAnoLetivoCode()
    {
        return anoLetivoCode;
    }
    /**
     * Devolve o Id de EdicaoUCJPA
     * @return o Id de EdicaoUCJPA
     */
    public Long getId()
    {
        return id;
    }
    /**
     * Devolve o UCCode de EdicaoUCJPA
     * @return o UCCode de EdicaoUCJPA
     */
    public String getUcCode()
    {
        return ucCode;
    }

    public Long getRucID()
    {
        return rucID;
    }

    public EstadoEdicaoUC getEstadoEdicaoUC()
    {
        return estadoEdicaoUC;
    }
}

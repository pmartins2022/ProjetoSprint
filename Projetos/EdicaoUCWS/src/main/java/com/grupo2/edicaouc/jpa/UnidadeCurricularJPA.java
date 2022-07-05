package com.grupo2.edicaouc.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe UnidadeCurricularJPA
 */
@Entity
@Table(name = "UnidadeCurricular")
public class UnidadeCurricularJPA
{
    /**
     * O id e sigla da UnidadeCurricularJPA
     */
    @Id
    private String sigla;

    /**
     * A denominacao da UnidadeCurricularJPA
     */
    private String denominacao;

    /**
     * Instancia objeto do tipo UnidadeCurricularJPA sem parametros
     */
    public UnidadeCurricularJPA()
    {
    }

    /**
     * Instancia objeto do tipo UnidadeCurricularJPA com parametro sigla e denominacao
     *
     * @param sigla       a sigla da UnidadeCurricularJPA
     * @param denominacao a denominacao da UnidadeCurricularJPA
     */
    public UnidadeCurricularJPA(String sigla, String denominacao)
    {
        this.sigla = sigla;
        this.denominacao = denominacao;
    }

    /**
     * Devolve sigla da UnidadeCurricularJPA
     *
     * @return sigla da UnidadeCurricularJPA
     */
    public String getSigla()
    {
        return sigla;
    }

    /**
     * Devolve denominacao da UnidadeCurricularJPA
     *
     * @return denominacao da UnidadeCurricularJPA
     */
    public String getDenominacao()
    {
        return denominacao;
    }
}
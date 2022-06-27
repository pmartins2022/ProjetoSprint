package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;

/**
 * Classe DTO de Unidade Curricular
 */
@Table(tableName = "UNIDADECURRICULAR")
public class UnidadeCurricularDTO extends JDBCTable
{
    /**
     * Sigla de Unidade Curricular
     */
    @PrimaryKey( generated = false)
    private String sigla;
    /**
     * Denominação de Unidade Curricular
     */
    private String denominacao;

    /**
     * Inicializa a UnidadeCurricularDTO sem parâmetros
     */
    public UnidadeCurricularDTO(){}

    /**
     * Inicializa a sigla e denominaçãoo de UnidadeCurricularDTO
     * com sigla e denominação recebidos
     * @param sigla é a sigla de Unidade Curricular
     * @param denominacao é a denominacao de Unidade Curricular
     */
    public UnidadeCurricularDTO(String sigla, String denominacao)
    {
        this.sigla = sigla;
        this.denominacao = denominacao;
    }

    /**
     * Devolve a sigla de Unidade Curricular
     * @return a sigla de Unidade Curricular
     */
    public String getSigla()
    {
        return sigla;
    }

    /**
     * Modifica a sigla de Unidade Curricular
     * @param sigla nova sigla de Unidade Curricular
     */
    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }

    /**
     * Devolve a denominação de Unidade Curricular
     * @return a denominação de Unidade Curricular
     */
    public String getDenominacao()
    {
        return denominacao;
    }

    /**
     * Modifica a denominação de Unidade Curricular
     * @param denominacao nova denominação de Unidade Curricular
     */
    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    @Override
    public String toString()
    {
        return "UnidadeCurricularDTO{" +
                "sigla='" + sigla + '\'' +
                ", denominacao='" + denominacao + '\'' +
                '}';
    }
}

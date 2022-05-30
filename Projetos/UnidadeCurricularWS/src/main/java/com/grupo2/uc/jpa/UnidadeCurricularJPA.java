package com.grupo2.uc.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UnidadeCurricular")
public class UnidadeCurricularJPA
{
    @Id private String sigla;
    private String denominacao;

    public UnidadeCurricularJPA(){}

    public UnidadeCurricularJPA(String sigla, String denominacao)
    {
        this.sigla = sigla;
        this.denominacao = denominacao;
    }

    public String getSigla()
    {
        return sigla;
    }

    public String getDenominacao()
    {
        return denominacao;
    }
}
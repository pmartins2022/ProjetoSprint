package com.grupo2.anoLetivoWS.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AnoLetivo")
public class AnoLetivoJPA
{
    @Id
    private String sigla;

    public AnoLetivoJPA(){}

    public AnoLetivoJPA(String sigla)
    {
        this.sigla = sigla;
    }

    public String getSigla()
    {
        return sigla;
    }
}
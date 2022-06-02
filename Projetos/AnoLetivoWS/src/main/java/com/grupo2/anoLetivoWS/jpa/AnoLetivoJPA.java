package com.grupo2.anoLetivoWS.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AnoLetivo")
public class AnoLetivoJpa
{
    @Id
    private String sigla;

    public AnoLetivoJpa(){}

    public AnoLetivoJpa(String sigla)
    {
        this.sigla = sigla;
    }

    public String getSigla()
    {
        return sigla;
    }
}
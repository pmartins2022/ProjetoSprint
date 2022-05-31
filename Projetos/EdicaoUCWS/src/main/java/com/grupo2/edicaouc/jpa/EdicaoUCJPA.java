package com.grupo2.edicaouc.jpa;


import javax.persistence.*;

@Entity
@Table(name="EdicaoUC")
public class EdicaoUCJPA
{
    @Id
    @GeneratedValue
    private Long id;
    private String ucCode;

    private String anoLetivoCode;

    public EdicaoUCJPA() {}

    public EdicaoUCJPA(String UCCode, String anoLetivoCode)
    {
        this.ucCode = UCCode;
        this.anoLetivoCode = anoLetivoCode;
    }

    public EdicaoUCJPA(Long id, String ucCode, String anoLetivoCode)
    {
        this.id = id;
        this.ucCode = ucCode;
        this.anoLetivoCode = anoLetivoCode;
    }

    public String getUCCode()
    {
        return ucCode;
    }

    public String getAnoLetivoCode()
    {
        return anoLetivoCode;
    }

    public Long getId()
    {
        return id;
    }

    public String getUcCode()
    {
        return ucCode;
    }
}

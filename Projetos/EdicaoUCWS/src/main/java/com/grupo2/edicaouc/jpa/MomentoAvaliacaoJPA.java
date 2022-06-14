package com.grupo2.edicaouc.jpa;

import javax.persistence.*;

@Entity
@Table(name = "MomentoAvaliacao", uniqueConstraints = @UniqueConstraint(columnNames = {"denominacao"}))
public class MomentoAvaliacaoJPA
{
    @Id
    @GeneratedValue
    @Column(name = "ma_id")
    private Long id;
    private String denominacao;

    public MomentoAvaliacaoJPA()
    {
    }

    public MomentoAvaliacaoJPA(Long id, String denominacao)
    {
        this.id = id;
        this.denominacao = denominacao;
    }

    public Long getId()
    {
        return id;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }
}
package com.grupo2.organizacao.jpa;

import javax.persistence.*;

@Entity
@Table(name="Organizacao")
public class OrganizacaoJPA
{
    @Id
    @GeneratedValue
    private Long id;
    private String denominacao;
    @Column(unique = true)
    private Integer nif;

    public OrganizacaoJPA()
    {
    }
    public OrganizacaoJPA(String denominacao, Integer nif)
    {
        this.denominacao = denominacao;
        this.nif = nif;
    }

    public OrganizacaoJPA(Long id, String denominacao, Integer nif)
    {
        this.id = id;
        this.denominacao = denominacao;
        this.nif = nif;
    }

    public Long getId()
    {
        return id;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public Integer getNif()
    {
        return nif;
    }
}

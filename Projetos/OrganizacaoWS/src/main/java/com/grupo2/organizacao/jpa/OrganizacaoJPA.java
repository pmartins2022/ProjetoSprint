package com.grupo2.organizacao.jpa;

import javax.persistence.*;

/**
 * Classe JPA de organizacao
 */
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

    /**
     * Inicializa OrganizacaoJPA sem parametros
     */
    public OrganizacaoJPA()
    {
    }

    /**
     * Inicializa a denominacao e o nif da OrganizacaoJPA com a denominacao e nif recebidos
     * @param denominacao e a denominacao da OrganizacaoJPA
     * @param nif e o nif da OrganizacaoJPA
     */
    public OrganizacaoJPA(String denominacao, Integer nif)
    {
        this.denominacao = denominacao;
        this.nif = nif;
    }

    /**
     * Inicializa o id, denominacao e o nif da OrganizacaoJPA com o id, denominacao e nif recebidos
     * @param id e a id da OrganizacaoJPA
     * @param denominacao e a denominacao da OrganizacaoJPA
     * @param nif e a nif da OrganizacaoJPA
     */
    public OrganizacaoJPA(Long id, String denominacao, Integer nif)
    {
        this.id = id;
        this.denominacao = denominacao;
        this.nif = nif;
    }

    /**
     * Devolve o id do OrganizacaoJPA
     * @return o id do OrganizacaoJPA
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Devolve o denominacao do OrganizacaoJPA
     * @return o denominacao do OrganizacaoJPA
     */
    public String getDenominacao()
    {
        return denominacao;
    }

    /**
     * Devolve o nif do OrganizacaoJPA
     * @return o nif do OrganizacaoJPA
     */
    public Integer getNif()
    {
        return nif;
    }
}

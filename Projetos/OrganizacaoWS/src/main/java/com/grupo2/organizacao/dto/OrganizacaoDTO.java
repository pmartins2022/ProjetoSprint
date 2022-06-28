package com.grupo2.organizacao.dto;

import java.util.Objects;

/**
 * Classe DTO da organizacao
 */
public class OrganizacaoDTO
{

    private Long id;

    private String denominacao;

    private Integer nif;

    /**
     * Inicializa OrganizacaoDTO sem parametros
     */
    public OrganizacaoDTO()
    {
    }

    /**
     * Inicializa e nif da OrganizacaoDTO com e nif
     * @param nif e a nif da OrganizacaoDTO
     */
    public OrganizacaoDTO(Integer nif)
    {
        this.nif = nif;
    }

    /**
     * Inicializa denominacaoo e nif da OrganizacaoDTO com denominacao e nif
     * @param denominacao e a denominacao da OrganizacaoDTO
     * @param nif e a nif da OrganizacaoDTO
     */
    public OrganizacaoDTO(String denominacao, Integer nif)
    {
        this.denominacao = denominacao;
        this.nif = nif;
    }

    /**
     * Inicializa id, denominacaoo e nif da OrganizacaoDTO com id, denominacao e nif
     * @param id e a id da OrganizacaoDTO
     * @param denominacao e a denominacao da OrganizacaoDTO
     * @param nif e a nif da OrganizacaoDTO
     */
    public OrganizacaoDTO(Long id, String denominacao, Integer nif)
    {
        this.id = id;
        this.denominacao = denominacao;
        this.nif = nif;
    }

    /**
     * Devolve o id da organizacao
     * @return o id do organizacao
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id do organizacao
     * @param id novo nr do organizacao
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devolve o denominacao da organizacao
     * @return o denominacao do organizacao
     */
    public String getDenominacao()
    {
        return denominacao;
    }

    /**
     * Modifica o denominacao do organizacao
     * @param denominacao novo nr do organizacao
     */
    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    /**
     * Devolve o nif da organizacao
     * @return o nif do organizacao
     */
    public Integer getNif()
    {
        return nif;
    }

    /**
     * Modifica o nif do organizacao
     * @param nif novo nr do organizacao
     */
    public void setNif(Integer nif)
    {
        this.nif = nif;
    }

    @Override
    public String toString()
    {
        return "OrganizacaoDTO{" +
                "id=" + id +
                ", denominacao='" + denominacao + '\'' +
                ", nif=" + nif +
                '}';
    }

    /**
     * Compara dois objetos do tipo OrganizacaoDTO
     * @param o e um objeto do tipo OrganizacaoDTO
     * @return true se forem iguais e false se nao forem iguais
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizacaoDTO that = (OrganizacaoDTO) o;
        return denominacao.equals(that.denominacao) && nif.equals(that.nif);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, denominacao, nif);
    }


}

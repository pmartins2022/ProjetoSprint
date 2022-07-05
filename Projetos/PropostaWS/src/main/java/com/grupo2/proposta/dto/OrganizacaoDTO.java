package com.grupo2.proposta.dto;

/**
 * Classe DTO que contem informacao sobre uma organizacao.
 */
public class OrganizacaoDTO
{
    private Long id;
    private String denominacao;
    private Long nif;

    /**
     * Inicializa OrganizacaoDTO sem parametros
     */
    public OrganizacaoDTO()
    {
    }

    /**
     * Inicializa o id, denominacao e nif da OrganizacaoDTO com o id, denominacao e nif
     * @param id é o id da OrganizacaoDTO
     * @param denominacao é a denominacao da OrganizacaoDTO
     * @param nif é o nif da OrganizacaoDTO
     */
    public OrganizacaoDTO(Long id, String denominacao, Long nif)
    {
        this.id = id;
        this.denominacao = denominacao;
        this.nif = nif;
    }

    /**
     * Devolve o id da OrganizacaoDTO
     * @return id da OrganizacaoDTO
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id da OrganizacaoDTO
     * @param id novo id da OrganizacaoDTO
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devolve a denominacao da OrganizacaoDTO
     * @return denominacao da OrganizacaoDTO
     */
    public String getDenominacao()
    {
        return denominacao;
    }

    /**
     * Modifica a denominacao da OrganizacaoDTO
     * @param denominacao nova denominacao da OrganizacaoDTO
     */
    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    /**
     * Devolve o nif da OrganizacaoDTO
     * @return nif da OrganizacaoDTO
     */
    public Long getNif()
    {
        return nif;
    }

    /**
     * Modifica o nif da OrganizacaoDTO
     * @param nif novo nif da OrganizacaoDTO
     */
    public void setNif(Long nif)
    {
        this.nif = nif;
    }
}

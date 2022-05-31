package com.grupo2.organizacao.dto;

public class OrganizacaoDTO
{
    private Long id;
    private String denominacao;
    private Integer nif;

    public OrganizacaoDTO()
    {
    }
    public OrganizacaoDTO(String denominacao, Integer nif)
    {
        this.denominacao = denominacao;
        this.nif = nif;
    }
    public OrganizacaoDTO(Long id, String denominacao, Integer nif)
    {
        this.id = id;
        this.denominacao = denominacao;
        this.nif = nif;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    public Integer getNif()
    {
        return nif;
    }

    public void setNif(Integer nif)
    {
        this.nif = nif;
    }
}

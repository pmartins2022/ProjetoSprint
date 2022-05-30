package com.grupo2.proposta.dto;

public class OrganizacaoDTO
{
    private Long id;
    private String denominacao;
    private Long nif;

    public OrganizacaoDTO()
    {
    }

    public OrganizacaoDTO(Long id, String denominacao, Long nif)
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

    public Long getNif()
    {
        return nif;
    }

    public void setNif(Long nif)
    {
        this.nif = nif;
    }
}

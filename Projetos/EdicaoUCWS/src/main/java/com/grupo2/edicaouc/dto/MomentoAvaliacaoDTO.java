package com.grupo2.edicaouc.dto;

public class MomentoAvaliacaoDTO
{
    private Long id;
    private String denominacao;

    public MomentoAvaliacaoDTO()
    {
    }

    public MomentoAvaliacaoDTO(Long id, String denominacao)
    {
        this.id = id;
        this.denominacao = denominacao;
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

    @Override
    public String toString()
    {
        return "MomentoAvaliacaoDTO{" +
                "id=" + id +
                ", denominacao='" + denominacao + '\'' +
                '}';
    }
}

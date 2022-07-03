package com.grupo2.edicaouc.dto;

/**
 * Classe para objetos do tipo MomentoAvaliacao
 */
public class MomentoAvaliacaoDTO
{
    private Long id;
    private Long edicaoUCID;
    private String denominacao;

    public MomentoAvaliacaoDTO()
    {
    }

    public MomentoAvaliacaoDTO(Long id, Long edicaoUCID, String denominacao)
    {
        this.id = id;
        this.edicaoUCID = edicaoUCID;
        this.denominacao = denominacao;
    }

    public Long getEdicaoUCID()
    {
        return edicaoUCID;
    }

    public void setEdicaoUCID(Long edicaoUCID)
    {
        this.edicaoUCID = edicaoUCID;
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

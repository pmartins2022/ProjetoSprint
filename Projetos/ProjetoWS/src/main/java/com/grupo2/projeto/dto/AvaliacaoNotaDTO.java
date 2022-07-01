package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.EstadoAvaliacao;

public class AvaliacaoNotaDTO
{
    private Long id;

    private Long idAvaliacao;

    private Long nota;

    private String justificacao;

    private EstadoAvaliacao estadoAvaliacao;

    public AvaliacaoNotaDTO(){}

    public AvaliacaoNotaDTO(Long id, Long idAvaliacao, Long nota, String justificacao, EstadoAvaliacao estadoAvaliacao)
    {
        this.id = id;
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.justificacao = justificacao;
        this.estadoAvaliacao = estadoAvaliacao;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getIdAvaliacao()
    {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Long idAvaliacao)
    {
        this.idAvaliacao = idAvaliacao;
    }

    public Long getNota()
    {
        return nota;
    }

    public void setNota(Long nota)
    {
        this.nota = nota;
    }

    public String getJustificacao()
    {
        return justificacao;
    }

    public void setJustificacao(String justificacao)
    {
        this.justificacao = justificacao;
    }

    public EstadoAvaliacao getEstadoAvaliacao()
    {
        return estadoAvaliacao;
    }

    public void setEstadoAvaliacao(EstadoAvaliacao estadoAvaliacao)
    {
        this.estadoAvaliacao = estadoAvaliacao;
    }
}

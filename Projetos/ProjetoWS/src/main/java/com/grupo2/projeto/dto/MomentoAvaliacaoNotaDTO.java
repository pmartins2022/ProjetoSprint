package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.EstadoAvaliacao;
import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;

public class MomentoAvaliacaoNotaDTO
{
    private Long id;

    private Long idAvaliacao;

    private Integer nota;

    private String justificacao;

    private EstadoAvaliacao estadoAvaliacao;

    public MomentoAvaliacaoNotaDTO(){}

    public MomentoAvaliacaoNotaDTO(Long id, Long idAvaliacao, Integer nota, String justificacao, EstadoAvaliacao estadoAvaliacao)
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

    public Integer getNota()
    {
        return nota;
    }

    public void setNota(Integer nota)
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

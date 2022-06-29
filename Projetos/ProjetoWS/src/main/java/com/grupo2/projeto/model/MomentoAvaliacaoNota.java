package com.grupo2.projeto.model;

import com.grupo2.projeto.model.annotations.ForeignKey;
import com.grupo2.projeto.model.annotations.IgnoreField;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;

@Table(tableName = "MOMENTOAVALIACAONOTA")
public class MomentoAvaliacaoNota extends JDBCTable
{
    @IgnoreField
    @PrimaryKey( generated = true)
    private Long id;

    @ForeignKey( className = Avaliacao.class, fieldName = "ID")
    private Long idAvaliacao;

    private Integer nota;

    private String justificacao;

    private EstadoAvaliacao estadoAvaliacao;

    public MomentoAvaliacaoNota(){}

    public MomentoAvaliacaoNota(Long idAvaliacao, Integer nota, String justificacao)
    {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.justificacao = justificacao;
        this.estadoAvaliacao = EstadoAvaliacao.PENDENTE;
    }

    public MomentoAvaliacaoNota(Long id, Long idAvaliacao, Integer nota, String justificacao, String estadoAvaliacao)
    {
        this.id = id;
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.justificacao = justificacao;
        this.estadoAvaliacao = EstadoAvaliacao.valueOf(estadoAvaliacao);
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

    public void updateEstado(EstadoAvaliacao estado)
    {
        this.estadoAvaliacao = estado;
    }
}

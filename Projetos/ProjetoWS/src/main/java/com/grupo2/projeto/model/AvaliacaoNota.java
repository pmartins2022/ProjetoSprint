package com.grupo2.projeto.model;

import com.grupo2.projeto.model.annotations.ForeignKey;
import com.grupo2.projeto.model.annotations.IgnoreField;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;

import java.util.Objects;

@Table(tableName = "AVALIACAONOTA")
public class AvaliacaoNota extends JDBCTable
{
    @IgnoreField
    @PrimaryKey(generated = true)
    private Long id;
    @ForeignKey(className = Avaliacao.class, fieldName = "ID")
    private Long idAvaliacao;
    private Long nota;
    private String justificacao;
    private EstadoAvaliacao estadoAvaliacao;

    public AvaliacaoNota()
    {
    }

    public AvaliacaoNota(Long idAvaliacao, Long nota, String justificacao)
    {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.justificacao = justificacao;
        this.estadoAvaliacao = EstadoAvaliacao.PENDENTE;
    }

    public AvaliacaoNota(Long id, Long idAvaliacao, Long nota, String justificacao, String estadoAvaliacao)
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

    public void updateEstado(EstadoAvaliacao estado)
    {
        this.estadoAvaliacao = estado;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoNota that = (AvaliacaoNota) o;
        return Objects.equals(id, that.id) && Objects.equals(idAvaliacao, that.idAvaliacao) && Objects.equals(nota, that.nota) && Objects.equals(justificacao, that.justificacao) && estadoAvaliacao == that.estadoAvaliacao;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, idAvaliacao, nota, justificacao, estadoAvaliacao);
    }

    @Override
    public String toString()
    {
        return "AvaliacaoNota{" +
                "id=" + id +
                ", idAvaliacao=" + idAvaliacao +
                ", nota=" + nota +
                ", justificacao='" + justificacao + '\'' +
                ", estadoAvaliacao=" + estadoAvaliacao +
                '}';
    }
}

package com.grupo2.projeto.model;

import java.util.Objects;

public class Avaliacao
{
    private Long id;

    private Long idMomentoAvaliacao;

    private Long presidenteId;

    private Long orientadorId;

    private Long arguenteId;

    private Long idProjeto;

    private Long conteudo;

    private int nota;

    public Avaliacao () {}

    public Avaliacao(Long id, Long idMomentoAvaliacao, Long presidenteId, Long orientadorId, Long arguenteId, Long idProjeto, Long conteudo, int nota)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.conteudo = conteudo;
        this.nota = nota;
    }

    public Avaliacao(Long idMomentoAvaliacao,Long presidenteId, Long orientadorId, Long arguenteId, Long idProjeto, Long conteudo, int nota)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.conteudo = conteudo;
        this.nota = nota;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getIdMomentoAvaliacao()
    {
        return idMomentoAvaliacao;
    }

    public void setIdMomentoAvaliacao(Long idMomentoAvaliacao)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
    }

    public Long getPresidenteId() {
        return presidenteId;
    }

    public void setPresidenteId(Long presidenteId) {
        this.presidenteId = presidenteId;
    }

    public Long getOrientadorId() {
        return orientadorId;
    }

    public void setOrientadorId(Long orientadorId) {
        this.orientadorId = orientadorId;
    }

    public Long getArguenteId() {
        return arguenteId;
    }

    public void setArguenteId(Long arguenteId) {
        this.arguenteId = arguenteId;
    }

    public Long getIdProjeto()
    {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto)
    {
        this.idProjeto = idProjeto;
    }

    public Long getConteudo()
    {
        return conteudo;
    }

    public void setConteudo(Long conteudo)
    {
        this.conteudo = conteudo;
    }

    public int getNota()
    {
        return nota;
    }

    public void setNota(int nota)
    {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return nota == avaliacao.nota && idMomentoAvaliacao.equals(avaliacao.idMomentoAvaliacao)
                && presidenteId.equals(avaliacao.presidenteId) && orientadorId.equals(avaliacao.orientadorId)
                && arguenteId.equals(avaliacao.arguenteId) && idProjeto.equals(avaliacao.idProjeto)
                && conteudo.equals(avaliacao.conteudo);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, idMomentoAvaliacao, presidenteId, orientadorId, arguenteId, idProjeto, conteudo, nota);
    }

    @Override
    public String toString()
    {
        return "Avaliacao{" +
                "id=" + id +
                ", idMomentoAvaliacao=" + idMomentoAvaliacao +
                ", presidenteId=" + presidenteId +
                ", orientadorId=" + orientadorId +
                ", arguenteId=" + arguenteId +
                ", idProjeto=" + idProjeto +
                ", conteudo=" + conteudo +
                ", nota=" + nota +
                '}';
    }
}

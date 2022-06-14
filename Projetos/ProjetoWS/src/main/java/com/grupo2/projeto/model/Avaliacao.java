package com.grupo2.projeto.model;

import java.util.Objects;

public class Avaliacao
{
    private Long id;

    private Long idMomentoAvaliacao;

    private Long idProjeto;

    private Long conteudo;

    private int nota;

    public Avaliacao () {}

    public Avaliacao(Long id, Long idMomentoAvaliacao, Long idProjeto, Long conteudo, int nota)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.idProjeto = idProjeto;
        this.conteudo = conteudo;
        this.nota = nota;
    }

    public Avaliacao(Long idMomentoAvaliacao, Long idProjeto, Long conteudo, int nota)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
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
        if (!(o instanceof Avaliacao)) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return getNota() == avaliacao.getNota() && getId().equals(avaliacao.getId()) && getIdMomentoAvaliacao().equals(avaliacao.getIdMomentoAvaliacao()) && getIdProjeto().equals(avaliacao.getIdProjeto()) && getConteudo().equals(avaliacao.getConteudo());
    }
}

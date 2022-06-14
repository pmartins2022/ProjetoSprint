package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.Conteudo;

public class AvaliacaoDTO
{
    private Long id;

    private Long idMomentoAvaliacao;

    private Long idProjeto;

    private Long idConteudo;

    private int nota;

    public AvaliacaoDTO(){}

    public AvaliacaoDTO(Long id, Long idMomentoAvaliacao, Long idProjeto, Long idConteudo, int nota)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.idProjeto = idProjeto;
        this.idConteudo = idConteudo;
        this.nota = nota;
    }

    public AvaliacaoDTO(Long idMomentoAvaliacao, Long idProjeto, Long idConteudo, int nota)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.idProjeto = idProjeto;
        this.idConteudo = idConteudo;
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
        return idConteudo;
    }

    public void setConteudo(Long idConteudo)
    {
        this.idConteudo = idConteudo;
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
    public String toString()
    {
        return "AvaliacaoDTO{" +
                "id=" + id +
                ", idMomentoAvaliacao=" + idMomentoAvaliacao +
                ", idProjeto=" + idProjeto +
                ", conteudo=" + idConteudo +
                ", nota=" + nota +
                '}';
    }
}

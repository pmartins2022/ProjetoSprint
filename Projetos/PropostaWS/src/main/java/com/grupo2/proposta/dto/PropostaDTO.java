package com.grupo2.proposta.dto;

import com.grupo2.proposta.model.PropostaEstado;

public class PropostaDTO
{
    private long id;
    private long utilizadorId;
    private long organizacaoId;
    private String titulo;
    private String problema;
    private String objetivo;
    private long edicaoUCId;
    private PropostaEstado estadoAtual;

    public PropostaDTO()
    {
    }

    public PropostaDTO(long id, long utilizadorId, long organizacaoId, String titulo, String problema, String objetivo, long edicaoUCId, PropostaEstado estadoAtual)
    {
        this.id = id;
        this.utilizadorId = utilizadorId;
        this.organizacaoId = organizacaoId;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.edicaoUCId = edicaoUCId;
        this.estadoAtual = estadoAtual;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getUtilizadorId()
    {
        return utilizadorId;
    }

    public void setUtilizadorId(long utilizadorId)
    {
        this.utilizadorId = utilizadorId;
    }

    public long getOrganizacaoId()
    {
        return organizacaoId;
    }

    public void setOrganizacaoId(long organizacaoId)
    {
        this.organizacaoId = organizacaoId;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getProblema()
    {
        return problema;
    }

    public void setProblema(String problema)
    {
        this.problema = problema;
    }

    public String getObjetivo()
    {
        return objetivo;
    }

    public void setObjetivo(String objetivo)
    {
        this.objetivo = objetivo;
    }

    public long getEdicaoUCId()
    {
        return edicaoUCId;
    }

    public void setEdicaoUCId(long edicaoUCId)
    {
        this.edicaoUCId = edicaoUCId;
    }

    public PropostaEstado getEstadoAtual()
    {
        return estadoAtual;
    }

    public void setEstadoAtual(PropostaEstado estadoAtual)
    {
        this.estadoAtual = estadoAtual;
    }
}
package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.PropostaEstado;

public class PropostaDTO
{
    private Long id;
    private Long utilizadorId;
    private Long organizacaoId;
    private String titulo;
    private String problema;
    private String objetivo;
    private Long edicaoUCId;
    private PropostaEstado estadoAtual;

    public PropostaDTO()
    {
    }

    public PropostaDTO(Long id, Long utilizadorId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUCId, PropostaEstado estadoAtual)
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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUtilizadorId()
    {
        return utilizadorId;
    }

    public void setUtilizadorId(Long utilizadorId)
    {
        this.utilizadorId = utilizadorId;
    }

    public Long getOrganizacaoId()
    {
        return organizacaoId;
    }

    public void setOrganizacaoId(Long organizacaoId)
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

    public Long getEdicaoUCId()
    {
        return edicaoUCId;
    }

    public void setEdicaoUCId(Long edicaoUCId)
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

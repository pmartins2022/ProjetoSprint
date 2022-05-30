package com.grupo2.proposta.jpa;

import com.grupo2.proposta.model.PropostaEstado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Proposta")
public class PropostaJPA
{
    @Id
    @GeneratedValue private Long id;
    private Long utilizadorId;
    private Long organizacaoId;
    private String titulo;
    private String problema;
    private String objetivo;
    private Long edicaoUCId;
    private PropostaEstado estadoAtual;

    public PropostaJPA()
    {
    }

    public PropostaJPA(Long id, Long utilizadorId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUCId, PropostaEstado estadoAtual)
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

    public Long getUtilizadorId()
    {
        return utilizadorId;
    }

    public Long getOrganizacaoId()
    {
        return organizacaoId;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getProblema()
    {
        return problema;
    }

    public String getObjetivo()
    {
        return objetivo;
    }

    public Long getEdicaoUCId()
    {
        return edicaoUCId;
    }

    public PropostaEstado getEstadoAtual()
    {
        return estadoAtual;
    }
}

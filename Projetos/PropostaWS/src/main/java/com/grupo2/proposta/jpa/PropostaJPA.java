package com.grupo2.proposta.jpa;

import com.grupo2.proposta.model.PropostaEstado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade que representa uma Proposta.
 */
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

    /**
     * Inicializa PropostaJPA sem parametros
     */
    public PropostaJPA()
    {
    }

    /**
     * Inicializa id, utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId, estadoAtual de PropostaJPA com
     *  id, utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId, estadoAtual
     * @param id e o id de PropostaJPA
     * @param utilizadorId e o utilizadorId de PropostaJPA
     * @param organizacaoId e o organizacaoId de PropostaJPA
     * @param titulo e o titulo de PropostaJPA
     * @param problema e o problema de PropostaJPA
     * @param objetivo e o objetivo de PropostaJPA
     * @param edicaoUCId e o edicaoUCId de PropostaJPA
     * @param estadoAtual e o estadoAtual de PropostaJPA
     */
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

    /**
     * Devolve o id de PropostaJPA
     * @return id de PropostaJPA
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Devolve o utilizadorId de PropostaJPA
     * @return utilizadorId de PropostaJPA
     */
    public Long getUtilizadorId()
    {
        return utilizadorId;
    }

    /**
     * Devolve o organizacaoId de PropostaJPA
     * @return organizacaoId de PropostaJPA
     */
    public Long getOrganizacaoId()
    {
        return organizacaoId;
    }

    /**
     * Devolve o titulo de PropostaJPA
     * @return titulo de PropostaJPA
     */
    public String getTitulo()
    {
        return titulo;
    }

    /**
     * Devolve o problema de PropostaJPA
     * @return problema de PropostaJPA
     */
    public String getProblema()
    {
        return problema;
    }

    /**
     * Devolve o objetivo de PropostaJPA
     * @return objetivo de PropostaJPA
     */
    public String getObjetivo()
    {
        return objetivo;
    }

    /**
     * Devolve o edicaoUCId de PropostaJPA
     * @return edicaoUCId de PropostaJPA
     */
    public Long getEdicaoUCId()
    {
        return edicaoUCId;
    }

    /**
     * Devolve o estadoAtual de PropostaJPA
     * @return estadoAtual de PropostaJPA
     */
    public PropostaEstado getEstadoAtual()
    {
        return estadoAtual;
    }
}

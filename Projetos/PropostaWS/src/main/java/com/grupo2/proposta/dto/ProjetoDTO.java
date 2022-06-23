package com.grupo2.proposta.dto;

import java.util.Objects;

/**
 * Classe DTO que contem informacao sobre um projeto.
 */
public class ProjetoDTO
{
    private Long id;
    private Long propostaId;
    private Long estudanteId;
    private Long orientadorId;

    /**
     * Inicializa ProjetoDTO sem parametros
     */
    public ProjetoDTO()
    {}

    /**
     * Inicializa id, propostaId, estudanteId, orientadorId de ProjetoDTO com id, propostaId, estudanteId, orientadorId
     * @param id e o id de ProjetoDTO
     * @param propostaId e o id da proposta de ProjetoDTO
     * @param estudanteId e o id do estudante de ProjetoDTO
     * @param orientadorId e o id do orientador de ProjetoDTO
     */
    public ProjetoDTO(Long id, Long propostaId, Long estudanteId, Long orientadorId)
    {
        this.id = id;
        this.propostaId = propostaId;
        this.estudanteId = estudanteId;
        this.orientadorId = orientadorId;
    }

    /**
     * Inicializa propostaId, estudanteId, orientadorId de ProjetoDTO com propostaId, estudanteId, orientadorId
     * @param propostaId e o id da proposta de ProjetoDTO
     * @param estudanteId e o id do estudante de ProjetoDTO
     * @param orientadorId  e o id do orientador de ProjetoDTO
     */
    public ProjetoDTO(Long propostaId, Long estudanteId, Long orientadorId)
    {
        this.propostaId = propostaId;
        this.estudanteId = estudanteId;
        this.orientadorId = orientadorId;
    }

    /**
     * Devolve o id de ProjetoDTO
     * @return id de ProjetoDTO
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Devolve o propostaid de ProjetoDTO
     * @return propostaid de ProjetoDTO
     */
    public Long getPropostaId()
    {
        return propostaId;
    }

    /**
     * Devolve o estudanteid de ProjetoDTO
     * @return estudanteId de ProjetoDTO
     */
    public Long getEstudanteId()
    {
        return estudanteId;
    }

    /**
     * Devolve o orientadorid de ProjetoDTO
     * @return orientadorId de ProjetoDTO
     */
    public Long getOrientadorId()
    {
        return orientadorId;
    }

    /**
     *  Modifica o id de ProjetoDTO
     * @param id e o novo id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Modifica o propostaId de ProjetoDTO
     * @param propostaId e o novo id da proposta
     */
    public void setPropostaId(Long propostaId)
    {
        this.propostaId = propostaId;
    }

    /**
     * Modifica o estudanteId de ProjetoDTO
     * @param estudanteId e o novo id do estudante
     */
    public void setEstudanteId(Long estudanteId)
    {
        this.estudanteId = estudanteId;
    }

    /**
     * Modifica o orientadorId de ProjetoDTO
     * @param orientadorId e o novo id do orientador
     */
    public void setOrientadorId(Long orientadorId)
    {
        this.orientadorId = orientadorId;
    }

    /**
     * Devolve um ProjetoDto com id, id da proposta, id do estudante e id do orientador
     * @return id, propostaId, estudanteId, orientadorId
     */
    @Override
    public String toString()
    {
        return "ProjetoDTO{" +
                "id=" + id +
                ", propostaId=" + propostaId +
                ", estudanteId=" + estudanteId +
                ", orientadorId=" + orientadorId +
                '}';
    }

    /**
     * Verifica se dois objetos são iguias
     * @param o objeto a ser conparado
     * @return true ou false, conforma os objetos comparados são iguais ou não
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjetoDTO that = (ProjetoDTO) o;
        return propostaId.equals(that.propostaId) && estudanteId.equals(that.estudanteId) && orientadorId.equals(that.orientadorId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, propostaId, estudanteId, orientadorId);
    }
}

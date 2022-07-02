package com.grupo2.projeto.dto;

import java.util.Objects;

/**
 * Classe DTO do projeto
 */
public class ProjetoDTO
{
    /**
     *Id do projeto
     */
    private Long id;
    /**
     *Id da proposta
     */
    private Long propostaId;
    /**
     *Id do Estudante
     */
    private Long estudanteId;
    /**
     *Id do orientador
     */
    private Long orientadorId;

    /**
     *Inicializa o ProjetoDto sem parametros
     */
    public ProjetoDTO()
    {}

    /**
     *Inicializa o id, propostaId, estudanteId e orientadorId do ProjetoDTO com id, propostaId, estudanteId e orientadorId recebidos
     * @param id é o id do projeto
     * @param propostaId é o id da proposta
     * @param estudanteId é o id do estudante
     * @param orientadorId é o id do orientador
     */
    public ProjetoDTO(Long id, Long propostaId, Long estudanteId, Long orientadorId)
    {
        this.id = id;
        this.propostaId = propostaId;
        this.estudanteId = estudanteId;
        this.orientadorId = orientadorId;
    }

    /**
     *Inicializa o propostaId, estudanteId e orientadorId do ProjetoDTO com propostaId, estudanteId e orientadorId recebidos
     * @param propostaId é o id da proposta
     * @param estudanteId é o id do estudante
     * @param orientadorId é o id do orientador
     */
    public ProjetoDTO(Long propostaId, Long estudanteId, Long orientadorId)
    {
        this.propostaId = propostaId;
        this.estudanteId = estudanteId;
        this.orientadorId = orientadorId;
    }

    /**
     * Devolve o id do projeto
     * @return o id do projeto
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Devolve o id da proposta
     * @return o id da proposta
     */
    public Long getPropostaId()
    {
        return propostaId;
    }

    /**
     * Devolve o id do estudante
     * @return o id do estudante
     */
    public Long getEstudanteId()
    {
        return estudanteId;
    }

    /**
     * Devolve o id do orientador
     * @return o id do orientador
     */
    public Long getOrientadorId()
    {
        return orientadorId;
    }

    /**
     *  Modifica o id do projeto
     * @param id novo id do projeto
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     *  Modifica o propostaId da proposta
     * @param propostaId novo propostaId da proposta
     */
    public void setPropostaId(Long propostaId)
    {
        this.propostaId = propostaId;
    }

    /**
     *  Modifica o estudanteId do estudante
     * @param estudanteId novo estudanteId do estudante
     */
    public void setEstudanteId(Long estudanteId)
    {
        this.estudanteId = estudanteId;
    }

    /**
     *  Modifica o orientadorId do orientador
     * @param orientadorId novo orientadorId do orientador
     */
    public void setOrientadorId(Long orientadorId)
    {
        this.orientadorId = orientadorId;
    }

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

    /**
     * Devolve um ProjetoDto com id, id da proposta, id do estudante e id do orientador
     * @return id, propostaId, estudanteId e orientadorId
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
}

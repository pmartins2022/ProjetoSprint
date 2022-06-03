package com.grupo2.projeto.model;

import java.util.Objects;

/**
 * Classe de dominio do projeto.
 */
public class Projeto
{
    /*
    Id do projeto
     */
    private Long id;
    /*
    Id da proposta
     */
    private Long propostaId;
    /*
    Id do Estudante
     */
    private Long estudanteId;
    /*
    Id do orientador
     */
    private Long orientadorId;

    /**
     *Inicializa o Projeto sem parametros
     */
    public Projeto()
    {}

    /**
     *Inicializa o id, propostaId, estudanteId e orientadorId do Projeto com id, propostaId, estudanteId e orientadorId recebidos
     * @param id é o id do projeto
     * @param propostaId é o id da proposta
     * @param estudanteId é o id do estudante
     * @param orientadorId é o id do orientador
     */
    public Projeto(Long id, Long propostaId, Long estudanteId, Long orientadorId)
    {
        this.id = id;
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
     *  Modifica o id da proposta
     * @param propostaId novo id da proposta
     */
    public void setPropostaId(Long propostaId)
    {
        this.propostaId = propostaId;
    }

    /**
     *  Modifica o id do estudante
     * @param estudanteId novo id do estudante
     */
    public void setEstudanteId(Long estudanteId)
    {
        this.estudanteId = estudanteId;
    }

    /**
     *  Modifica o id do orientador
     * @param orientadorId novo id do orientador
     */
    public void setOrientadorId(Long orientadorId)
    {
        this.orientadorId = orientadorId;
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
        if (!(o instanceof Projeto)) return false;
        Projeto projeto = (Projeto) o;
        return Objects.equals(getPropostaId(), projeto.getPropostaId()) && Objects.equals(getEstudanteId(), projeto.getEstudanteId()) && Objects.equals(getOrientadorId(), projeto.getOrientadorId());
    }
}

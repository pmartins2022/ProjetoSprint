package com.grupo2.projeto.dto;

/*
Classe DTO de projeto.
 */
public class ProjetoDTO
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

    /*
    Inicializa o ProjetoDto sem parametros
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

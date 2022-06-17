package com.grupo2.projeto.jpa;

import javax.persistence.*;

/**
 * Classe JPA de projeto
 */
@Entity
@Table(name = "Projeto")
public class ProjetoJPA
{
    @Id
    @GeneratedValue
    /*
   Id do projeto
    */
    private Long id;
    /*
    Id da proposta
     */
	@Column(unique = true)
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
    Inicializa o ProjetoJPA sem parametros
     */
    public ProjetoJPA()
    {}

    /**
     *Inicializa o id, propostaId, estudanteId e orientadorId do ProjetoJPA com id, propostaId, estudanteId e orientadorId recebidos
     * @param id é o id do projeto
     * @param propostaId é o id da proposta
     * @param estudanteId é o id do estudante
     * @param orientadorId é o id do orientador
     */
    public ProjetoJPA(Long id, Long propostaId, Long estudanteId, Long orientadorId)
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
}

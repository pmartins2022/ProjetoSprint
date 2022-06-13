package com.grupo2.projeto.jpa;

import javax.persistence.*;

/**
 * Classe JPA do Momento de Avaliação
 */
@Entity
@Table(name="momentoAvaliacao")
public class MomentoAvaliacaoJPA {

    /**
     * Id do Momento de Avaliação
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Id do projeto
     */
    private Long projetoId;
    /**
     * Id do Presidente do Júri
     */
    private Long presidenteId;
    /**
     * Id do Orientador do Projeto em Avaliação
     */
    private Long orientadorId;
    /**
     * Id do arguente
     */
    private Long arguenteId;

    /**
     * Inicializa o MomentoAvaliacaoJPA sem parâmetros
     */
    public MomentoAvaliacaoJPA() {
    }

    /**
     * Inicializa o id, projetoId, presidenteId, orientadorId, arguenteId do MomentoAvaliacaoJPA com o id, projetoId, presidenteId, orientadorId, arguenteId recebidos
     * @param id é o id do Momento de Avaliação
     * @param projetoId é o id do projeto
     * @param presidenteId é o id do Presidente do Júri
     * @param orientadorId é o id do Orientador do Projeto em Avaliação
     * @param arguenteId é o id do Arguente
     */
    public MomentoAvaliacaoJPA(Long id, Long projetoId, Long presidenteId, Long orientadorId, Long arguenteId) {
        this.id = id;
        this.projetoId = projetoId;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
    }

    /**
     * Devolve o id do Momento de Avaliação
     * @return id do Momento de Avaliação
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica o id do Momento de Avaliação
     * @param id novo id do Momento de Avaliação
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devolve o id do Projeto
     * @return id do Projeto
     */
    public Long getProjetoId() {
        return projetoId;
    }

    /**
     * Modifica o id de Projeto
     * @param projetoId novo id de Projeto
     */
    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    /**
     * Devolve o Id do Presidente do Júri
     * @return Id do Presidente do Júri
     */
    public Long getPresidenteId() {
        return presidenteId;
    }

    /**
     * Modifica o id do Presidente do Júri
     * @param presidenteId novo id do Presidente do Júri
     */
    public void setPresidenteId(Long presidenteId) {
        this.presidenteId = presidenteId;
    }

    /**
     * Devolve o Id do Orientador do Projeto em Avaliação
     * @return Id do Orientador do Projeto em Avaliação
     */
    public Long getOrientadorId() {
        return orientadorId;
    }

    /**
     * Modifica o id do Orientador do Projeto em Avaliação
     * @param orientadorId novo id do Orientador do Projeto em Avaliação
     */
    public void setOrientadorId(Long orientadorId) {
        this.orientadorId = orientadorId;
    }

    /**
     * Devolve o id do Arguente
     * @return id do Arguente
     */
    public Long getArguenteId() {
        return arguenteId;
    }

    /**
     * Modifica o id do Arguente
     * @param arguenteId novo id do Arguente
     */
    public void setArguenteId(Long arguenteId) {
        this.arguenteId = arguenteId;
    }
}

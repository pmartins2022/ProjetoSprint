package com.grupo2.projeto.model;

/**
 * Classe de dominio do Momento de Avaliação
 */
public class MomentoAvaliacao {
    /**
     * Id do Momento de Avaliação
     */
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
     * Id do Arguente
     */
    private Long arguenteId;

    /**
     * Inicializa o Projeto de Avaliação sem parâmetros
     */
    public MomentoAvaliacao() {
    }


    /**
     * Inicializa o id, projetoId, presidenteId, orientadorId, arguenteId do Momento de Avaliação com o id, projetoId, presidenteId, orientadorId, arguenteId recebidos
     * @param id é o id do Momento de Avaliação
     * @param projetoId é o id do Projeto
     * @param presidenteId é o id do Presidente do Júri
     * @param orientadorId é o Orientador do Projeto em Avaliação
     * @param arguenteId é o Arguente
     */
    public MomentoAvaliacao(Long id, Long projetoId, Long presidenteId, Long orientadorId, Long arguenteId) {
        this.id = id;
        this.projetoId = projetoId;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
    }

    /**
     * Devolve o Id do Momento de Avaliação
     * @return Id do Momento de Avaliação
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica o Id do Momento de Avaliação
     * @param id novo Id do Momento de Avaliação
     */
    public void setMomentoId(Long id) {
        this.id = id;
    }

    /**
     * Devolve o Id do Projeto
     * @return Id do Projeto
     */
    public Long getProjetoId() {
        return projetoId;
    }

    /**
     * Modifica o Id do Projeto
     * @param projetoId novo Id do Projeto
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
     * Modifica o Id do Presidente do Júri
     * @param presidenteId novo Id do Presidente do Júri
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
     * Modifica o Id do Orientador do Projeto em Avaliação
     * @param orientadorId novo Id do Orientador do Projeto em Avaliação
     */
    public void setOrientadorId(Long orientadorId) {
        this.orientadorId = orientadorId;
    }

    /**
     * Devolve o Id do Arguente
     * @return Id do Arguente
     */
    public Long getArguenteId() {
        return arguenteId;
    }

    /**
     * Modifica o Id do Arguente
     * @param arguenteId novo Id do Arguente
     */
    public void setArguenteId(Long arguenteId) {
        this.arguenteId = arguenteId;
    }
}

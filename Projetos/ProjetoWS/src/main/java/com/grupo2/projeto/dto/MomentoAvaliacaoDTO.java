package com.grupo2.projeto.dto;

import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoDTO {
    /**
     * Id do Momento de Avaliação
     */
    private Long id;
    /**
     * Id do projeto
     */
    private Long projetoId;
    /**
     * Id do presidente do júri
     */
    private Long presidenteId;
    /**
     * Id do orientador do Projeto em Avaliação
     */
    private Long orientadorId;
    /**
     * Id do arguente
     */
    private Long arguenteId;

    /**
     * Inicializa o ProjetoDTO sem parâmetros
     */
    public MomentoAvaliacaoDTO() {
    }

    /**
     * Inicializa o id, projetoId, presidenteId, orientadorId, arguenteId do MomentoAvaliacaoDTO com o id, projetoId, presidenteId, orientadorId, arguenteId recebidos.
     * @param id é o id do momento de Avaliação
     * @param projetoId é o id do projeto
     * @param presidenteId é o id do presidente do júri
     * @param orientadorId é o id do orientador do Projeto em Avaliação
     * @param arguenteId é o id do arguente
     */
    public MomentoAvaliacaoDTO(Long id, Long projetoId, Long presidenteId, Long orientadorId, Long arguenteId) {
        this.id = id;
        this.projetoId = projetoId;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
    }

    /**
     * Devolve o id do momento de Avaliação
     * @return id do momento da Avaliação
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica o id do momento de Avaliação
     * @param id novo id do momento de Avaliação
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devolve o id do projeto
     * @return id do projeto
     */
    public Long getProjetoId() {
        return projetoId;
    }

    /**
     * Modifica o id do projeto
     * @param projetoId novo id do projeto
     */
    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    /**
     * Devolve o id do Presidente do Júri
     * @return id do Presidente do Júri
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
     * Devolve o id do Orientador
     * @return id do Orientador
     */
    public Long getOrientadorId() {
        return orientadorId;
    }

    /**
     * Modifica o id do Orientador
     * @param orientadorId novo id do Orientador
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

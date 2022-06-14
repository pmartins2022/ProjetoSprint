package com.grupo2.projeto.dto;

import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoDTO {
    private Long id;
    private Long projetoId;

    public MomentoAvaliacaoDTO(Long id, Long projetoId) {
        this.id = id;
        this.projetoId = projetoId;
    }

    public MomentoAvaliacaoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

}

package com.grupo2.projeto.dto;

import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoDTO {
    private Long id;
    private Long projetoId;
    private Long presidenteId;
    private Long orientadorId;
    private Long arguenteId;

    public MomentoAvaliacaoDTO(Long id, Long projetoId, Long presidenteId, Long orientadorId, Long arguenteId) {
        this.id = id;
        this.projetoId = projetoId;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
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

    public Long getPresidenteId() {
        return presidenteId;
    }

    public void setPresidenteId(Long presidenteId) {
        this.presidenteId = presidenteId;
    }

    public Long getOrientadorId() {
        return orientadorId;
    }

    public void setOrientadorId(Long orientadorId) {
        this.orientadorId = orientadorId;
    }

    public Long getArguenteId() {
        return arguenteId;
    }

    public void setArguenteId(Long arguenteId) {
        this.arguenteId = arguenteId;
    }
}

package com.grupo2.projeto.model;

public class MomentoAvaliacao {
    private Long id;
    private Long projetoId;

    public MomentoAvaliacao(Long id, Long projetoId) {
        this.id = id;
        this.projetoId = projetoId;
    }

    public MomentoAvaliacao() {
    }

    public Long getId() {
        return id;
    }

    public void setMomentoId(Long id) {
        this.id = id;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

}

package com.grupo2.projeto.jpa;

import javax.persistence.*;

@Entity
@Table(name="momentoAvaliacao")
public class MomentoAvaliacaoJPA {

    @Id
    @GeneratedValue
    private Long id;
    private Long projetoId;
    private Long presidenteId;
    private Long orientadorId;
    private Long arguenteId;

    public MomentoAvaliacaoJPA(Long id, Long projetoId, Long presidenteId, Long orientadorId, Long arguenteId) {
        this.id = id;
        this.projetoId = projetoId;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
    }

    public MomentoAvaliacaoJPA() {
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

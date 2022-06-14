package com.grupo2.projeto.jpa;

import javax.persistence.*;

@Entity
@Table(name="momentoAvaliacao")
public class MomentoAvaliacaoJPA {

    @Id
    @GeneratedValue
    private Long id;
    private Long projetoId;

    public MomentoAvaliacaoJPA(Long id, Long projetoId) {
        this.id = id;
        this.projetoId = projetoId;
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

}

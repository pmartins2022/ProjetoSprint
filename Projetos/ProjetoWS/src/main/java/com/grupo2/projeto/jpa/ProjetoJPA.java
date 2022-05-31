package com.grupo2.projeto.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Projeto")
public class ProjetoJPA
{
    @Id
    @GeneratedValue
    private Long id;
    private Long propostaId;
    private Long estudanteId;
    private Long orientadorId;

    public ProjetoJPA()
    {}

    public ProjetoJPA(Long id, Long propostaId, Long estudanteId, Long orientadorId)
    {
        this.id = id;
        this.propostaId = propostaId;
        this.estudanteId = estudanteId;
        this.orientadorId = orientadorId;
    }

    public Long getId()
    {
        return id;
    }

    public Long getPropostaId()
    {
        return propostaId;
    }

    public Long getEstudanteId()
    {
        return estudanteId;
    }

    public Long getOrientadorId()
    {
        return orientadorId;
    }
}

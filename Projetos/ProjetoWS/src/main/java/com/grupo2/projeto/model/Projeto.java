package com.grupo2.projeto.model;

import java.util.Objects;

public class Projeto
{
    private Long id;
    private Long propostaId;
    private Long estudanteId;
    private Long orientadorId;

    public Projeto()
    {}

    public Projeto(Long id, Long propostaId, Long estudanteId, Long orientadorId)
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setPropostaId(Long propostaId)
    {
        this.propostaId = propostaId;
    }

    public void setEstudanteId(Long estudanteId)
    {
        this.estudanteId = estudanteId;
    }

    public void setOrientadorId(Long orientadorId)
    {
        this.orientadorId = orientadorId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Projeto)) return false;
        Projeto projeto = (Projeto) o;
        return Objects.equals(getId(), projeto.getId()) && Objects.equals(getPropostaId(), projeto.getPropostaId()) && Objects.equals(getEstudanteId(), projeto.getEstudanteId()) && Objects.equals(getOrientadorId(), projeto.getOrientadorId());
    }
}

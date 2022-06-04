package com.grupo2.proposta.dto;

/**
 * Classe DTO que contem informacao sobre um projeto.
 */
public class ProjetoDTO
{
    private Long id;
    private Long propostaId;
    private Long estudanteId;
    private Long orientadorId;

    public ProjetoDTO()
    {}

    public ProjetoDTO(Long id, Long propostaId, Long estudanteId, Long orientadorId)
    {
        this.id = id;
        this.propostaId = propostaId;
        this.estudanteId = estudanteId;
        this.orientadorId = orientadorId;
    }

    public ProjetoDTO(Long propostaId, Long estudanteId, Long orientadorId)
    {
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
    public String toString()
    {
        return "ProjetoDTO{" +
                "id=" + id +
                ", propostaId=" + propostaId +
                ", estudanteId=" + estudanteId +
                ", orientadorId=" + orientadorId +
                '}';
    }
}

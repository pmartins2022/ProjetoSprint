package com.grupo2.proposta.model;

/**
 * Entidade que representa uma ConviteID.
 */
public class PropostaCandidatura
{
    private Long idProposta;
    private Long idAluno;
    private EstadoCandidatura estado;

    public PropostaCandidatura(Long idProposta, Long idAluno, EstadoCandidatura estado)
    {
        this.idProposta = idProposta;
        this.idAluno = idAluno;
        this.estado = estado;
    }

    public Long getIdProposta()
    {
        return idProposta;
    }

    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }

    public Long getIdAluno()
    {
        return idAluno;
    }

    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }

    public EstadoCandidatura getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoCandidatura estado)
    {
        this.estado = estado;
    }
}

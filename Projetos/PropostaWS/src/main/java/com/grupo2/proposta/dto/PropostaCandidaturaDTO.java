package com.grupo2.proposta.dto;

import com.grupo2.proposta.model.EstadoCandidatura;

import java.util.Objects;

public class PropostaCandidaturaDTO
{
    private Long idProposta;
    private Long idAluno;
    private EstadoCandidatura estado;

    public PropostaCandidaturaDTO()
    {
    }

    public PropostaCandidaturaDTO(Long idProposta, Long idAluno, EstadoCandidatura estado)
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropostaCandidaturaDTO that = (PropostaCandidaturaDTO) o;
        return idProposta.equals(that.idProposta) && idAluno.equals(that.idAluno) && estado == that.estado;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(idProposta, idAluno, estado);
    }
}

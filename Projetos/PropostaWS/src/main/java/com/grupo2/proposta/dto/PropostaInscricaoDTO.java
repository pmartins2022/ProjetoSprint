package com.grupo2.proposta.dto;

import com.grupo2.proposta.model.EstadoCandidatura;

public class PropostaInscricaoDTO
{
    private Long idProposta;
    private Long idAluno;
    private EstadoCandidatura estado;

    public PropostaInscricaoDTO()
    {
    }

    public PropostaInscricaoDTO(Long idProposta, Long idAluno, EstadoCandidatura estado)
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

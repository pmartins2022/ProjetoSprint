package com.grupo2.proposta.dto;

import com.grupo2.proposta.model.EstadoConvite;

public class ConviteDTO
{
    private Long idAluno;
    private Long idDocente;
    private Long idProposta;

    private EstadoConvite estado;

    public ConviteDTO(){}

    public ConviteDTO(Long idAluno, Long idDocente, Long idProposta, EstadoConvite estado)
    {
        this.idAluno = idAluno;
        this.idDocente = idDocente;
        this.idProposta = idProposta;
        this.estado = estado;
    }

    public Long getIdAluno()
    {
        return idAluno;
    }

    public Long getIdDocente()
    {
        return idDocente;
    }

    public Long getIdProposta()
    {
        return idProposta;
    }

    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }

    public void setIdDocente(Long idDocente)
    {
        this.idDocente = idDocente;
    }

    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }

    public EstadoConvite getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoConvite estado)
    {
        this.estado = estado;
    }
}

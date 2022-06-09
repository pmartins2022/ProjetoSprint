package com.grupo2.proposta.dto;

public class ConviteDTO
{
    private Long idAluno;
    private Long idDocente;
    private Long idProposta;

    public ConviteDTO(){}

    public ConviteDTO(Long idAluno, Long idDocente, Long idProposta)
    {
        this.idAluno = idAluno;
        this.idDocente = idDocente;
        this.idProposta = idProposta;
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
}

package com.grupo2.proposta.model;

public class Convite
{
    private Long idAluno;
    private Long idDocente;
    private Long idProposta;

    public Convite(Long idAluno, Long idDocente, Long idProposta)
    {
        this.idAluno = idAluno;
        this.idDocente = idDocente;
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

    public Long getIdDocente()
    {
        return idDocente;
    }

    public void setIdDocente(Long idDocente)
    {
        this.idDocente = idDocente;
    }

    public Long getIdProposta()
    {
        return idProposta;
    }

    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }
}

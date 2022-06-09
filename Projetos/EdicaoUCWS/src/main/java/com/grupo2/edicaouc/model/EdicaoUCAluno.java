package com.grupo2.edicaouc.model;

public class EdicaoUCAluno
{
    private Long idEdicaoUC;
    private Long idAluno;

    public EdicaoUCAluno()
    {
    }

    public EdicaoUCAluno(Long idEdicaoUC, Long idAluno)
    {
        this.idEdicaoUC = idEdicaoUC;
        this.idAluno = idAluno;
    }

    public Long getIdEdicaoUC()
    {
        return idEdicaoUC;
    }

    public void setIdEdicaoUC(Long idEdicaoUC)
    {
        this.idEdicaoUC = idEdicaoUC;
    }

    public Long getIdAluno()
    {
        return idAluno;
    }

    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }
}

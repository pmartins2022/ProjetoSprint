package com.grupo2.edicaouc.dto;

public class EdicaoUCAlunoDTO
{
    private Long idEdicaoUC;
    private Long idAluno;

    public EdicaoUCAlunoDTO()
    {
    }

    public EdicaoUCAlunoDTO(Long idEdicaoUC, Long idAluno)
    {
        this.idEdicaoUC = idEdicaoUC;
        this.idAluno = idAluno;
    }

    public Long getIdEdicaoUC()
    {
        return idEdicaoUC;
    }

    public Long getIdAluno()
    {
        return idAluno;
    }

    public void setIdEdicaoUC(Long idEdicaoUC)
    {
        this.idEdicaoUC = idEdicaoUC;
    }

    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }
}

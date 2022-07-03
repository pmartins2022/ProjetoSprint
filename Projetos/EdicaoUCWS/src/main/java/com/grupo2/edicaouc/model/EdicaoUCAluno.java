package com.grupo2.edicaouc.model;

/**
 * Classe dominio para edicao uc aluno
 */
public class EdicaoUCAluno
{
    private Long edicaoUCID;
    private Long alunoID;

    public EdicaoUCAluno()
    {
    }

    public EdicaoUCAluno(Long edicaoUCID, Long alunoID)
    {
        this.edicaoUCID = edicaoUCID;
        this.alunoID = alunoID;
    }

    public Long getEdicaoUCID()
    {
        return edicaoUCID;
    }

    public void setEdicaoUCID(Long edicaoUCID)
    {
        this.edicaoUCID = edicaoUCID;
    }

    public Long getAlunoID()
    {
        return alunoID;
    }

    public void setAlunoID(Long alunoID)
    {
        this.alunoID = alunoID;
    }
}

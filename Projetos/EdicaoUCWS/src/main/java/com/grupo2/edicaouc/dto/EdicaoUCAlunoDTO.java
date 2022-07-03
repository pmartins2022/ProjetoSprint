package com.grupo2.edicaouc.dto;

/**
 * Classe para objetos do tipo EdicaoUCAluno
 */
public class EdicaoUCAlunoDTO
{
    private Long edicaoUCID;
    private Long alunoID;

    public EdicaoUCAlunoDTO()
    {
    }

    public EdicaoUCAlunoDTO(Long edicaoUCID, Long alunoID)
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

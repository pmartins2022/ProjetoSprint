package com.example.javafx.dto;

public class PropostaCandidaturaIDDTO
{
    private Long idProposta;
    private Long idAluno;

    public PropostaCandidaturaIDDTO()
    {
    }

    public PropostaCandidaturaIDDTO(Long idProposta, Long idAluno)
    {
        this.idProposta = idProposta;
        this.idAluno = idAluno;
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
}

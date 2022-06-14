package com.grupo2.edicaouc.model;

public class EdicaoMomentoAvaliacao
{
    private Long idEdicao;
    private Long idMomentoAvaliacao;

    public EdicaoMomentoAvaliacao(Long idEdicao, Long idMomentoAvaliacao)
    {
        this.idEdicao = idEdicao;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
    }

    public Long getIdEdicao()
    {
        return idEdicao;
    }

    public void setIdEdicao(Long idEdicao)
    {
        this.idEdicao = idEdicao;
    }

    public Long getIdMomentoAvaliacao()
    {
        return idMomentoAvaliacao;
    }

    public void setIdMomentoAvaliacao(Long idMomentoAvaliacao)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
    }
}

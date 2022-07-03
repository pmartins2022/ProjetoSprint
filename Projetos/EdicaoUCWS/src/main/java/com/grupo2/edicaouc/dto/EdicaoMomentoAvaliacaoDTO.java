package com.grupo2.edicaouc.dto;

/**
 * Classe DTO para objetos do tipo EdicaoMomentoAvaliacao
 */
public class EdicaoMomentoAvaliacaoDTO
{
    private Long idEdicao;
    private Long idMomentoAvaliacao;

    public EdicaoMomentoAvaliacaoDTO()
    {
    }

    public EdicaoMomentoAvaliacaoDTO(Long idEdicao, Long idMomentoAvaliacao)
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

    @Override
    public String toString()
    {
        return "EdicaoMomentoAvaliacaoDTO{" +
                "idEdicao=" + idEdicao +
                ", idMomentoAvaliacao=" + idMomentoAvaliacao +
                '}';
    }
}

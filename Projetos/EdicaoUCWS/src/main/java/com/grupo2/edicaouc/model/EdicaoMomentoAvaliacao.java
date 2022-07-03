package com.grupo2.edicaouc.model;

import java.util.Objects;

/**
 * Classe dominio para edicao momento avaliacao.
 */
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdicaoMomentoAvaliacao that = (EdicaoMomentoAvaliacao) o;
        return Objects.equals(idEdicao, that.idEdicao) && Objects.equals(idMomentoAvaliacao, that.idMomentoAvaliacao);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(idEdicao, idMomentoAvaliacao);
    }
}

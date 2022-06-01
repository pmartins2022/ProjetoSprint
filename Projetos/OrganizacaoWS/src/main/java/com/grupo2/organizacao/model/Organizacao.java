package com.grupo2.organizacao.model;

import com.grupo2.organizacao.exception.ValidacaoInvalidaException;

public class Organizacao
{
    private Long id;
    private String denominacao;
    private Integer nif;

    private static final int DENOMINACAO_MIN_NUM_CHARACTERS = 3;

    public Organizacao()
    {
    }
    public Organizacao(Long id, String denominacao, Integer nif)
    {
        this.id = id;
        validateDenominacao(denominacao);
        this.denominacao = denominacao;
        this.nif = nif;
    }

    private void validateDenominacao(String denominacao)
    {
        if (denominacao.trim().isEmpty() || denominacao.length() < DENOMINACAO_MIN_NUM_CHARACTERS)
        {
            throw new ValidacaoInvalidaException("Denominação de Organização tem que ter no mínimo 3 caracteres");
        }
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public void setDenominacao(String denominacao)
    {
        validateDenominacao(denominacao);
        this.denominacao = denominacao;
    }

    public Integer getNif()
    {
        return nif;
    }

    public void setNif(Integer nif)
    {
        this.nif = nif;
    }
}

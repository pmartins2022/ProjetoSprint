package com.grupo2.uc.model;

import com.grupo2.uc.exception.ValidacaoInvalidaException;

public class UnidadeCurricular
{
    private String sigla;
    private String denominacao;

    public UnidadeCurricular(String sigla, String denominacao) throws ValidacaoInvalidaException
    {
        validateSigla(sigla);
    }

    public String getSigla()
    {
        return sigla;
    }

    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    private void validateSigla(String sigla)
    {
        validateString(sigla,3);

        this.sigla = sigla;
    }

    private void validateDenominacao(String denominacao)
    {
        validateString(denominacao,10);

        this.denominacao = denominacao;
    }

    private void validateString(String str, int minSize) throws ValidacaoInvalidaException
    {
        if (str.length() < minSize)
        {
            throw new ValidacaoInvalidaException("O tamanho nao deve ser inferior a "+minSize+" catacteres.");
        }

        final char[] chars = str.toCharArray();

        for(char c : chars)
        {
            if (Character.isDigit(c))
            {
                throw new ValidacaoInvalidaException("Valores nao podem conter algarismos.");
            }
            else if (Character.isWhitespace(c))
            {
                throw new ValidacaoInvalidaException("Valores nao podem conter espacos.");
            }
            else if (!Character.isLetter(c))
            {
                throw new ValidacaoInvalidaException("Valores nao podem conter simbolos.");
            }
        }
    }
}

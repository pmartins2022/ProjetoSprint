package com.grupo2.uc.model;

import com.grupo2.uc.exception.ValidacaoInvalidaException;

public class UnidadeCurricular
{
    private String sigla;
    private String denominacao;

    public UnidadeCurricular(String sigla, String denominacao) throws ValidacaoInvalidaException
    {
        validateSigla(sigla);
        validateDenominacao(denominacao);

        this.sigla = sigla;
        this.denominacao = denominacao;
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
        validateString(sigla, 3, true);
    }

    private void validateDenominacao(String denominacao)
    {
        validateString(denominacao, 10, false);

    }

    private void validateString(String str, int minSize, boolean noSpaces) throws ValidacaoInvalidaException
    {
        if (str.trim().length() < minSize)
        {
            throw new ValidacaoInvalidaException("O tamanho nao deve ser inferior a " + minSize + " catacteres.");
        }

        for (char c : str.toCharArray())
        {
            if (Character.isDigit(c))
            {
                throw new ValidacaoInvalidaException("Valores nao podem conter algarismos.");
            } else if (!Character.isAlphabetic(c) && !Character.isWhitespace(c))
            {
                throw new ValidacaoInvalidaException("Valores nao podem conter simbolos. "+str);
            }

            if (noSpaces)
            {
                if (Character.isWhitespace(c))
                {
                    throw new ValidacaoInvalidaException("Valores nao podem conter espacos.");
                }
            }
        }
    }
}
package com.grupo2.edicaouc.model;


import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;

import java.util.Objects;

/**
 * Classe de domínio UnidadeCurricular
 */
public class UnidadeCurricular
{
    /**
     * Sigla da UnidadeCurricular
     */
    private String sigla;

    /**
     * Denominacao da UnidadeCurricular
     */
    private String denominacao;

    /**
     * Instancia objeto do tipo UnidadeCurricular com parametros sigla e denominacao
     *
     * @param sigla
     * @param denominacao
     * @throws ValidacaoInvalidaException
     */
    public UnidadeCurricular(String sigla, String denominacao) throws ValidacaoInvalidaException
    {
        validateSigla(sigla);
        validateDenominacao(denominacao);

        this.sigla = sigla;
        this.denominacao = denominacao;
    }

    /**
     * Devolve sigla da UnidadeCurricular
     *
     * @return sigla da UnidadeCurricular
     */
    public String getSigla()
    {
        return sigla;
    }

    /**
     * Modifica sigla da UnidadeCurricular
     *
     * @param sigla nova sigla da UnidadeCurricular
     */
    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }

    /**
     * Devolve denominacao da UnidadeCurricular
     *
     * @return denominacao da UnidadeCurricular
     */
    public String getDenominacao()
    {
        return denominacao;
    }

    /**
     * Modifica denominacao da UnidadeCurricular
     *
     * @param denominacao nova denominacao da UnidadeCurricular
     */
    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    /**
     * Método que valida sigla da UnidadeCurricular antes de esta ser instanciada
     *
     * @param sigla sigla da UnidadeCurricular
     */
    private void validateSigla(String sigla)
    {
        validateString(sigla, 3, true);
    }

    /**
     * Método que valida denominacao da UnidadeCurricular antes de esta ser instanciada
     *
     * @param denominacao
     */
    private void validateDenominacao(String denominacao)
    {
        validateString(denominacao, 10, false);

    }

    /**
     * Método que valida variáveis do tipo String
     *
     * @param str      string a ser validada
     * @param minSize  tamanho mínimo que a string tem que respeitat
     * @param noSpaces boolean que determina se a string pode ter espaços
     * @throws ValidacaoInvalidaException
     */
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
                throw new ValidacaoInvalidaException("Valores nao podem conter simbolos. " + str);
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

    /**
     * Compara 2 objetos
     *
     * @param o objeto recebido a ser comparado
     * @return boolean do resultado da comparação
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnidadeCurricular that = (UnidadeCurricular) o;
        return Objects.equals(sigla, that.sigla) && Objects.equals(denominacao, that.denominacao);
    }
}
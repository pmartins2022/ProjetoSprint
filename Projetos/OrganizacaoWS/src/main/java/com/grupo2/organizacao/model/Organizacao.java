package com.grupo2.organizacao.model;

import com.grupo2.organizacao.exception.ValidacaoInvalidaException;

import java.util.Objects;

/**
 * Classe de dominio do Organizacao
 */
public class Organizacao
{
    /**
     * Id do Organizacao
     */
    private Long id;
    /**
     * denominacao do Organizacao
     */
    private String denominacao;
    /**
     * nif do Organizacao
     */
    private Integer nif;

    /**
     * Variavel auxiliar para validacao da denominacao
     */
    private static final int DENOMINACAO_MIN_NUM_CHARACTERS = 3;

    /**
     * Inicializa A organizacao sem parametros
     */
    public Organizacao()
    {
    }

    /**
     * Inicializa o id, denominacao e nif da Organizacao com id, denominacao e nif
     * @param id e o id da organizacao
     * @param denominacao e a denominacao da organizacao
     * @param nif e o nif da organizacao
     */
    public Organizacao(Long id, String denominacao, Integer nif)
    {
        this.id = id;
        validateDenominacao(denominacao);
        this.denominacao = denominacao;
        this.nif = nif;
    }

    /**
     * Inicializa a denominacao e nif da Organizacao
     * @param denominacao a denominacao da organizacao
     * @param nif o nif da organizacao
     */
    public Organizacao(String denominacao, Integer nif)
    {
        this.denominacao = denominacao;
        this.nif = nif;
    }

    /**
     * Metodo que valida a denominacao
     * @param denominacao recebe o denominacao a ser validado
     */
    private void validateDenominacao(String denominacao)
    {
        if (denominacao.trim().isEmpty() || denominacao.length() < DENOMINACAO_MIN_NUM_CHARACTERS)
        {
            throw new ValidacaoInvalidaException("Denominação de Organização tem que ter no mínimo 3 caracteres");
        }
    }

    /**
     * Devolve o id da organizacao
     * @return o id da organizacao
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id da organizacao
     * @param id novo id da organizacao
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devolve o denominacao da organizacao
     * @return o denominacao da organizacao
     */
    public String getDenominacao()
    {
        return denominacao;
    }

    /**
     * Devolve o denominacao da organizacao
     * @return o denominacao da organizacao
     */
    public void setDenominacao(String denominacao)
    {
        validateDenominacao(denominacao);
        this.denominacao = denominacao;
    }
    /**
     * Devolve o nif da organizacao
     * @return o nif da organizacao
     */

    public Integer getNif()
    {
        return nif;
    }

    /**
     * Devolve o nif da organizacao
     * @return o nif da organizacao
     */
    public void setNif(Integer nif)
    {
        this.nif = nif;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Organizacao)) return false;
        Organizacao that = (Organizacao) o;
        return Objects.equals(getDenominacao(), that.getDenominacao()) && Objects.equals(getNif(), that.getNif());
    }
}

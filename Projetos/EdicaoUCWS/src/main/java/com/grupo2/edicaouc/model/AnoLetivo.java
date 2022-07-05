package com.grupo2.edicaouc.model;


import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;

import java.util.Objects;

public class AnoLetivo
{
    private static final String REGEX = "\\d{4}-\\d{4}";
    private String sigla;

    public AnoLetivo(String sigla) throws ValidacaoInvalidaException
    {
        validate(sigla);
        this.sigla = sigla;
    }

    private void validate(String sigla) throws ValidacaoInvalidaException
    {
        if (!sigla.matches(REGEX))
            throw new ValidacaoInvalidaException("Sigla inválida. Deve estar no formato: 'YYYY-YYYY'");

        int firstYear = Integer.parseInt(sigla.substring(0, 4));
        int secondYear = Integer.parseInt(sigla.substring(5, 9));

        if (secondYear != firstYear + 1)
            throw new ValidacaoInvalidaException("Sigla inválida. O intervalo de anos deve ser de 1 ano, e o segundo ano deve ser o primeiro ano + 1.");
    }

    public String getSigla()
    {
        return sigla;
    }

    public void setSigla(String sigla)
    {
        validate(sigla);
        this.sigla = sigla;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnoLetivo anoLetivo = (AnoLetivo) o;
        return Objects.equals(sigla, anoLetivo.sigla);
    }
}
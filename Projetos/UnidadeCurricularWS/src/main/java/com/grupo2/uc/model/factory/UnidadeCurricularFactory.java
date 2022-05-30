package com.grupo2.uc.model.factory;

import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.model.UnidadeCurricular;
import org.springframework.stereotype.Component;

@Component
public class UnidadeCurricularFactory
{
    public UnidadeCurricular createUnidadeCurricular(String sigla, String denominacao) throws ValidacaoInvalidaException
    {
        return new UnidadeCurricular(sigla, denominacao);
    }
}
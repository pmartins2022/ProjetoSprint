package com.grupo2.uc.model.factory;

import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.model.UnidadeCurricular;
import org.springframework.stereotype.Component;

/**
 * Classe factory da UnidadeCurricular
 */
@Component
public class UnidadeCurricularFactory
{
    /**
     * Cria objetos UnidadeCurricular
     * @param sigla sigla da UnidadeCurricular
     * @param denominacao denominacao da UnidadeCurricular
     * @return UnidadeCurricular
     * @throws ValidacaoInvalidaException
     */
    public UnidadeCurricular createUnidadeCurricular(String sigla, String denominacao) throws ValidacaoInvalidaException
    {
        return new UnidadeCurricular(sigla, denominacao);
    }
}
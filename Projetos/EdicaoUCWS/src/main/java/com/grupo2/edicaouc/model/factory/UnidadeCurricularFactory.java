package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.model.UnidadeCurricular;
import org.springframework.stereotype.Component;

/**
 * Classe factory para instanciacao de objetos do tipo UnidadeCurricular
 */
@Component
public class UnidadeCurricularFactory
{
    /**
     * Cria objetos UnidadeCurricular
     *
     * @param sigla       sigla da UnidadeCurricular
     * @param denominacao denominacao da UnidadeCurricular
     * @return UnidadeCurricular criado
     * @throws ValidacaoInvalidaException problema de validacao
     */
    public UnidadeCurricular createUnidadeCurricular(String sigla, String denominacao) throws ValidacaoInvalidaException
    {
        return new UnidadeCurricular(sigla, denominacao);
    }
}
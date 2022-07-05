package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.model.AnoLetivo;
import org.springframework.stereotype.Component;

/**
 * Classe que permite a criacao de instancias de AnoLetivo.
 */
@Component
public class AnoLetivoFactory
{
    /**
     * Fazer a criacao de uma classe de dominio AnoLetivo
     *
     * @param sigla a informacao da sigla
     * @return o objeto criado
     * @throws ValidacaoInvalidaException erro de validacao
     */
    public AnoLetivo createAnoLetivo(String sigla) throws ValidacaoInvalidaException
    {
        return new AnoLetivo(sigla);
    }
}
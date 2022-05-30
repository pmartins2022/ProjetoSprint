package com.grupo2.anoLetivoWS.model.factory;

import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import org.springframework.stereotype.Component;

@Component
public class AnoLetivoFactory
{
    public AnoLetivo createAnoLetivo(String sigla) throws ValidacaoInvalidaException
    {
        return new AnoLetivo(sigla);
    }
}
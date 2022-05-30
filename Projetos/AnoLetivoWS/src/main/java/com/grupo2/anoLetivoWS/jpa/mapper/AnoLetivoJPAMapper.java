package com.grupo2.anoLetivoWS.jpa.mapper;

import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.jpa.AnoLetivoJpa;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.model.factory.AnoLetivoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnoLetivoJPAMapper
{
    @Autowired
    private AnoLetivoFactory factory;

    public AnoLetivo toModel(AnoLetivoJpa jpa) throws ValidacaoInvalidaException
    {
        return factory.createAnoLetivo(jpa.getSigla());
    }

    public AnoLetivoJpa toJpa(AnoLetivo model)
    {
        return new AnoLetivoJpa(model.getSigla());
    }
}
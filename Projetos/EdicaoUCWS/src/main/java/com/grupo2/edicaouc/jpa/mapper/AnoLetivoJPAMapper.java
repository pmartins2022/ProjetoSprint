package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.jpa.AnoLetivoJPA;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.model.factory.AnoLetivoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnoLetivoJPAMapper
{
    @Autowired
    private AnoLetivoFactory factory;

    public AnoLetivo toModel(AnoLetivoJPA jpa) throws ValidacaoInvalidaException
    {
        return factory.createAnoLetivo(jpa.getSigla());
    }

    public AnoLetivoJPA toJpa(AnoLetivo model)
    {
        return new AnoLetivoJPA(model.getSigla());
    }
}
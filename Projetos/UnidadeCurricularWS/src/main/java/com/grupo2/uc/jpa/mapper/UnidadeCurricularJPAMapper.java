package com.grupo2.uc.jpa.mapper;

import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.model.factory.UnidadeCurricularFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnidadeCurricularJPAMapper
{
    @Autowired
    private UnidadeCurricularFactory factory;

    public UnidadeCurricular toModel(UnidadeCurricularJPA jpa)
    {
        return factory.createUnidadeCurricular(jpa.getSigla(),jpa.getDenominacao());
    }

    public UnidadeCurricularJPA toJPA(UnidadeCurricular model)
    {
        return new UnidadeCurricularJPA(model.getSigla(),model.getDenominacao());
    }
}

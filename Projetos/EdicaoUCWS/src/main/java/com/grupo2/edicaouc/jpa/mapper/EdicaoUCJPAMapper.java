package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCJPAMapper
{
    @Autowired
    private EdicaoUCFactory factory;

    public EdicaoUC toModel(EdicaoUCJPA jpa)
    {
        return factory.createEdicaoUC(jpa.getId(),jpa.getUCCode(),jpa.getAnoLetivoCode());
    }
    public EdicaoUCJPA toJpa(EdicaoUC model)
    {
        return new EdicaoUCJPA(model.getId(),model.getUCCode(), model.getAnoLetivoCode());
    }

}

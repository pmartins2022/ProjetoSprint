package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Classe para fazer a conversão entre objetos EdicaoUC de JPA para classe de domínio.
 */
@Component
public class EdicaoUCJPAMapper
{
    /**
     * Factory a ser utilizado por EdicaoUCJPAMapper
     */
    @Autowired
    private EdicaoUCFactory factory;

    /**
     * Fazer conversão para classe de domínio
     * @param jpa objeto jpa com os dados
     * @return objeto convertido
     */
    public EdicaoUC toModel(EdicaoUCJPA jpa)
    {
        return factory.createEdicaoUC(jpa.getId(),jpa.getUCCode(),jpa.getAnoLetivoCode());
    }

    /**
     * Fazer conversão para classe JPA
     * @param model o objeto de domínio de dados
     * @return o objeto convertido
     */
    public EdicaoUCJPA toJpa(EdicaoUC model)
    {
        return new EdicaoUCJPA(model.getId(),model.getUCCode(), model.getAnoLetivoCode());
    }

}

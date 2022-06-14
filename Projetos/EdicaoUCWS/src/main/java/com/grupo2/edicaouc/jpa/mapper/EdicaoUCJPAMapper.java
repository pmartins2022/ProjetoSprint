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
        return factory.createEdicaoUC(jpa.getId(),jpa.getUCCode(),jpa.getAnoLetivoCode(), jpa.getRucID(), jpa.getEstadoEdicaoUC());
    }

    /**
     * Fazer conversão para classe JPA
     * @param edicaoUC o objeto de domínio de dados
     * @return o objeto convertido
     */
    public EdicaoUCJPA toJPA(EdicaoUC edicaoUC)
    {
        return new EdicaoUCJPA(edicaoUC.getId(),edicaoUC.getUCCode(), edicaoUC.getAnoLetivoCode(), edicaoUC.getRucID(), edicaoUC.getEstadoEdicaoUC());
    }

}

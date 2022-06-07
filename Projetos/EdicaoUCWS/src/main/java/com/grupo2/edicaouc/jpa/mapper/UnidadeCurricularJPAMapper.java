package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.UnidadeCurricularJPA;
import com.grupo2.edicaouc.model.UnidadeCurricular;
import com.grupo2.edicaouc.model.factory.UnidadeCurricularFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao entre objetos UnidadeCurricularJPA para classe de dominio.
 */
@Component
public class UnidadeCurricularJPAMapper
{
    /**
     * O factory a ser utilizado por este JPA Mapper.
     */
    @Autowired
    private UnidadeCurricularFactory factory;

    /**
     * Fazer a conversao para classe de dominio.
     *
     * @param jpa o objeto jpa com os dados
     * @return o objeto convertido
     */
    public UnidadeCurricular toModel(UnidadeCurricularJPA jpa)
    {
        return factory.createUnidadeCurricular(jpa.getSigla(), jpa.getDenominacao());
    }

    /**
     * Fazer a conversao para classe JPA
     *
     * @param model o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public UnidadeCurricularJPA toJPA(UnidadeCurricular model)
    {
        return new UnidadeCurricularJPA(model.getSigla(), model.getDenominacao());
    }
}

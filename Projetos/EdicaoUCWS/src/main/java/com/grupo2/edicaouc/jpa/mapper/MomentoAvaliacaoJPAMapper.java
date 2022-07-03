package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.MomentoAvaliacaoJPA;
import com.grupo2.edicaouc.model.MomentoAvaliacao;
import com.grupo2.edicaouc.model.factory.MomentoAvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para converter objetos do tipo MomentoAvaliacao
 */
@Component
public class MomentoAvaliacaoJPAMapper
{
    @Autowired
    private MomentoAvaliacaoFactory factory;

    /**
     * Converter para objeto de dominio
     * @param jpa objeto a converter
     * @return objeto convertido
     */
    public MomentoAvaliacao toModel(MomentoAvaliacaoJPA jpa)
    {
        return factory.create(jpa.getId(),jpa.getIdEdicao(), jpa.getDenominacao());
    }

    /**
     * Converter para objeto JPA
     * @param model objeto a converter
     * @return objeto convertido
     */
    public MomentoAvaliacaoJPA toJPA (MomentoAvaliacao model)
    {
        return new MomentoAvaliacaoJPA(model.getId(), model.getIdEdicao(), model.getDenominacao());
    }
}

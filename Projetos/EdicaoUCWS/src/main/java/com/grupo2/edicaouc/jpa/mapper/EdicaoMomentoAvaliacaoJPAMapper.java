package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.EdicaoMomentoAvaliacaoID;
import com.grupo2.edicaouc.jpa.EdicaoMomentoAvaliacaoJPA;
import com.grupo2.edicaouc.model.EdicaoMomentoAvaliacao;
import org.springframework.stereotype.Component;

/**
 * Classe para converter objetos do tipo EdicaoMomentoAvaliacao
 */
@Component
public class EdicaoMomentoAvaliacaoJPAMapper
{
    /**
     * Converter para objeto de modelo
     * @param jpa objeto a converter
     * @return objeto convertido
     */
    public EdicaoMomentoAvaliacao toModel(EdicaoMomentoAvaliacaoJPA jpa)
    {
        return new EdicaoMomentoAvaliacao(jpa.getId().getIdEdicao(),jpa.getId().getIdMomentoAvaliacao());
    }

    /**
     * Converter para JPA
     * @param model objeto a converter
     * @return objeto convertido
     */
    public EdicaoMomentoAvaliacaoJPA toJPA(EdicaoMomentoAvaliacao model)
    {
        EdicaoMomentoAvaliacaoID id = new EdicaoMomentoAvaliacaoID(model.getIdEdicao(),model.getIdMomentoAvaliacao());
        return new EdicaoMomentoAvaliacaoJPA(id);
    }
}
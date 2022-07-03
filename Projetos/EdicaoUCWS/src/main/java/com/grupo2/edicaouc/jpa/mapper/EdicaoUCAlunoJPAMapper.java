package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import org.springframework.stereotype.Component;

/**
 * Classe para converter para objetos do tipo EdicaoUCAluno
 */
@Component
public class EdicaoUCAlunoJPAMapper
{
    /**
     * Converter para objeto de dominio
     * @param edicaoUCAlunoJPA objeto a converter
     * @return objeto convertido
     */
    public EdicaoUCAluno toModel(EdicaoUCAlunoJPA edicaoUCAlunoJPA)
    {
        return new EdicaoUCAluno(edicaoUCAlunoJPA.getId().getEdicaoUCID(), edicaoUCAlunoJPA.getId().getAlunoID());
    }

    /**
     * Converter para JPA
     * @param edicaoUCAluno objeto a converter
     * @return objeto convertido
     */
    public EdicaoUCAlunoJPA toJPA(EdicaoUCAluno edicaoUCAluno)
    {
        EdicaoUCAlunoID id = new EdicaoUCAlunoID(edicaoUCAluno.getEdicaoUCID(), edicaoUCAluno.getAlunoID());
        return new EdicaoUCAlunoJPA(id);
    }
}

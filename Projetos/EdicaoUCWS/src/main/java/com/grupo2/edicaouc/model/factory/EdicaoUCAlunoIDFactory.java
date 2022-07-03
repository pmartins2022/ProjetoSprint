package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import org.springframework.stereotype.Component;

/**
 * Classe factory para instanciacao de objetos do tipo EdicaoUCAlunoID
 */
@Component
public class EdicaoUCAlunoIDFactory
{
    /**
     * Criar um novo objeto EdicaoUCAlunoID
     * @param edicaoUCID edicao id
     * @param alunoID aluno id
     * @return o objeto criado
     */
    public EdicaoUCAlunoID create(Long edicaoUCID, Long alunoID)
    {
        return new EdicaoUCAlunoID(edicaoUCID, alunoID);
    }
}

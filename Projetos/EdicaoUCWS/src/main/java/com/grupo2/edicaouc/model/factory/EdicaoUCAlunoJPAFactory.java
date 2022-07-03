package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import org.springframework.stereotype.Component;

/**
 * Classe factory para instanciacao de objetos do tipo EdicaoUCAlunoJPA
 */
@Component
public class EdicaoUCAlunoJPAFactory
{
    /**
     * Criar um novo objeto
     * @param id a classe ID a usar
     * @return o objeto criado
     */
    public EdicaoUCAlunoJPA create(EdicaoUCAlunoID id)
    {
        return new EdicaoUCAlunoJPA(id);
    }
}

package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCAlunoJPAFactory
{
    public EdicaoUCAlunoJPA create(EdicaoUCAlunoID id)
    {
        return new EdicaoUCAlunoJPA(id);
    }
}

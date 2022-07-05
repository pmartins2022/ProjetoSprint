package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCAlunoIDFactory
{
    public EdicaoUCAlunoID create(Long edicaoUCID, Long alunoID)
    {
        return new EdicaoUCAlunoID(edicaoUCID, alunoID);
    }
}

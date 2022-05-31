package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.model.EdicaoUC;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCFactory
{
    public EdicaoUC createEdicaoUC(Long id, String UCCode, String anoLetivoCode)
    {
        return new EdicaoUC(id, UCCode,anoLetivoCode);
    }

}

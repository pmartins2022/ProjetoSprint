package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.model.EdicaoUC;
import org.springframework.stereotype.Component;

/**
 * Classe que gere a instaciação de objetos do tipo EdicaoUC
 */
@Component
public class EdicaoUCFactory
{
    /**
     * Cria objetos do tipo EdicaoUc
     * @param id recebe o id dos mappers
     * @param UCCode recebe o UCCode dos mappers
     * @param anoLetivoCode recebe o anoLetivoCode dos mappers
     * @return novo objeto do tipo EdicaoUC
     */
    public EdicaoUC createEdicaoUC(Long id, String UCCode, String anoLetivoCode)
    {
        return new EdicaoUC(id, UCCode,anoLetivoCode);
    }
}
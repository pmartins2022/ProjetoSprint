package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
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
    public EdicaoUC createEdicaoUC(Long id, String UCCode, String anoLetivoCode, Long rucID, EstadoEdicaoUC estado)
    {
        return new EdicaoUC(id, UCCode,anoLetivoCode, rucID,estado);
    }

    public EdicaoUC createEdicaoUC(String UCCode, String anoLetivoCode, Long rucID, EstadoEdicaoUC estado)
    {
        return new EdicaoUC(null, UCCode,anoLetivoCode, rucID, estado);
    }

    public EdicaoUC createEdicaoUC(String UCCode, String anoLetivoCode, Long rucID)
    {
        return new EdicaoUC(null, UCCode,anoLetivoCode, rucID, EstadoEdicaoUC.PENDENTE);
    }

    public EdicaoUC createEdicaoUC(Long id, String UCCode, String anoLetivoCode, Long rucID)
    {
        return new EdicaoUC(id, UCCode,anoLetivoCode, rucID, EstadoEdicaoUC.PENDENTE);
    }
}
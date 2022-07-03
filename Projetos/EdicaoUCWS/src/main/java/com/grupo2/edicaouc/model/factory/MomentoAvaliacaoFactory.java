package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.model.MomentoAvaliacao;
import org.springframework.stereotype.Component;

/**
 * Classe factory para instanciacao de objetos do tipo MomentoAvaliacao
 */
@Component
public class MomentoAvaliacaoFactory
{
    /**
     * Criar um novo objeto
     * @param id o id
     * @param edicaoUCID id da edicao
     * @param denominacao denominacao do momento
     * @return o objeto criado
     */
    public MomentoAvaliacao create(Long id, Long edicaoUCID,  String denominacao)
    {
        return new MomentoAvaliacao(id, edicaoUCID, denominacao);
    }
}

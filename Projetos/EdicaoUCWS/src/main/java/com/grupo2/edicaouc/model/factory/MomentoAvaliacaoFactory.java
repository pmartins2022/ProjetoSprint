package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.model.MomentoAvaliacao;
import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoFactory
{
    public MomentoAvaliacao create(Long id, String denominacao)
    {
        return new MomentoAvaliacao(id, denominacao);
    }
}

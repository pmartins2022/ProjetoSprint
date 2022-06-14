package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.MomentoAvaliacao;
import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoFactory {

    public MomentoAvaliacao createMomentoAvaliacao (Long id, Long projetoId){
        return new MomentoAvaliacao(id,projetoId);
    }
}

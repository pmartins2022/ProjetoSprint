package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.MomentoAvaliacao;
import org.springframework.stereotype.Component;

/**
 * Classe Factory do Momento de Avaliação
 */
@Component
public class MomentoAvaliacaoFactory {
    /**
     * Cria objetos do tipo MomentoAvaliacao
     * @param id recebe o id dos mappers
     * @param projetoId recebe o projetoId dos mappers
     * @param presidenteId recebe o presidenteId dos mappers
     * @param orientadorId recebe o orientadorId dos mappers
     * @param arguenteId recebe o arguenteId dos mappers
     * @return um novo objeto do tipo MomentoAvaliacao
     */
    public MomentoAvaliacao createMomentoAvaliacao (Long id, Long projetoId, Long presidenteId, Long orientadorId, Long arguenteId){
        return new MomentoAvaliacao(id,projetoId,presidenteId,orientadorId,arguenteId);
    }
}

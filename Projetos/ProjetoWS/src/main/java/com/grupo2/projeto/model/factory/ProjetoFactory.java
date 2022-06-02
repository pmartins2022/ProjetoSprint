package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.Projeto;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ProjetoFactory
{
    /**
     * Cria objetos do tipo projeto
     * @param id recebe o id dos mappers
     * @param propostaId recebe o propostaId dos mappers
     * @param estudanteId recebe o estudanteId dos mappers
     * @param orientadorId recebe o orientadorId dos mappers
     * @return um novo objeto do tipo Projeto
     */
    public Projeto createProjeto(Long id, Long propostaId, Long estudanteId, Long orientadorId)
    {
        return new Projeto(id,propostaId,estudanteId,orientadorId);
    }
}

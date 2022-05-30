package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.Projeto;
import org.springframework.stereotype.Component;

@Component
public class ProjetoFactory
{
    public Projeto createProjeto(Long id, Long propostaId, Long estudanteId, Long orientadorId)
    {
        Projeto projeto = new Projeto(id,propostaId,estudanteId,orientadorId);
        return projeto;
    }
}

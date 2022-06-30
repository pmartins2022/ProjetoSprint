package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.AvaliacaoNota;
import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoNotaFactory
{
    public AvaliacaoNota createMomentoAvaliacaoNota(Long idAvaliacao, Integer nota, String justificacao)
    {
        return new AvaliacaoNota(idAvaliacao, nota, justificacao);
    }
}

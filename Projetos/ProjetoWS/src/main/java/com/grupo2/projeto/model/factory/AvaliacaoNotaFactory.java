package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.AvaliacaoNota;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoNotaFactory
{
    public AvaliacaoNota createMomentoAvaliacaoNota(Long idAvaliacao, Long nota, String justificacao)
    {
        return new AvaliacaoNota(idAvaliacao, nota, justificacao);
    }
}

package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.EstadoAvaliacao;
import com.grupo2.projeto.model.MomentoAvaliacaoNota;

public class MomentoAvaliacaoNotaFactory
{
    public MomentoAvaliacaoNota createMomentoAvaliacaoNota(Long idAvaliacao, Integer nota, String justificacao)
    {
        return new MomentoAvaliacaoNota(idAvaliacao, nota, justificacao);
    }
}

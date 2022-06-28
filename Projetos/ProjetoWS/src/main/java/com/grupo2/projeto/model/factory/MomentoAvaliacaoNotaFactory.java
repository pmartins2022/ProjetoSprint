package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.EstadoAvaliacao;
import com.grupo2.projeto.model.MomentoAvaliacaoNota;

public class MomentoAvaliacaoNotaFactory
{
    public MomentoAvaliacaoNota createMomentoAvaliacaoNota(Long id, Long idAvaliacao, Integer nota, String justificacao, EstadoAvaliacao estadoAvaliacao)
    {
        return new MomentoAvaliacaoNota(id, idAvaliacao, nota, justificacao, estadoAvaliacao);
    }
}

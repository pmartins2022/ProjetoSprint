package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoFactory
{
    public Avaliacao createAvaliacao(Long id, Long idMomentoAvaliacao, Long idProjeto, Long conteudo, int nota)
    {
        return new Avaliacao(id, idMomentoAvaliacao, idProjeto, conteudo, nota);
    }
}

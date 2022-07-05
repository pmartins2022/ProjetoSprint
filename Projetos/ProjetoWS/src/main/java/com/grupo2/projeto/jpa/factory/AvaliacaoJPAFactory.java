package com.grupo2.projeto.jpa.factory;

import com.grupo2.projeto.jpa.AvaliacaoJPA;
import com.grupo2.projeto.jpa.ConteudoJPA;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoJPAFactory
{

    public AvaliacaoJPA create(Long id, Long idMomentoAvaliacao, Long presidenteId, Long orientadorId, Long arguenteId, Long idProjeto, ConteudoJPA conteudoJPA, int nota)
    {
        return new AvaliacaoJPA(id, idMomentoAvaliacao, presidenteId, orientadorId, arguenteId, idProjeto, conteudoJPA, nota);
    }
}

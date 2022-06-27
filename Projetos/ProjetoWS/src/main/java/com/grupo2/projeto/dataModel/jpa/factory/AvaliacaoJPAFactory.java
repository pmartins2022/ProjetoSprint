package com.grupo2.projeto.dataModel.jpa.factory;

import com.grupo2.projeto.dataModel.jpa.AvaliacaoJPA;
import com.grupo2.projeto.dataModel.jpa.ConteudoJPA;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoJPAFactory
{

    public AvaliacaoJPA create(Long id, Long idMomentoAvaliacao, Long presidenteId, Long orientadorId, Long arguenteId, Long idProjeto, ConteudoJPA conteudoJPA)
    {
        return new AvaliacaoJPA(id, idMomentoAvaliacao, presidenteId, orientadorId, arguenteId, idProjeto, conteudoJPA);
    }
}

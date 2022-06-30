package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoFactory
{
    public Avaliacao createAvaliacao(Long id,Long idProjeto, Long idMomentoAvaliacao,
                                     Long presidenteId, Long orientadorId, Long arguenteId,Long conteudo, String date)
    {
        return new Avaliacao(id, idProjeto,idMomentoAvaliacao,presidenteId,orientadorId,arguenteId, conteudo, date);
    }
}

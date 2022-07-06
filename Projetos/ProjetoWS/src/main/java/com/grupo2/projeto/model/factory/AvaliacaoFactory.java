package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoFactory
{
    public Avaliacao createAvaliacao(Long id, Long idMomentoAvaliacao,
                                     Long orientadorId, Long presidenteId, Long arguenteId,Long projetoID, Long conteudo, String estado, String date)
    {
        return new Avaliacao(id,idMomentoAvaliacao,orientadorId, presidenteId, arguenteId, projetoID, conteudo, estado, date);
    }
}

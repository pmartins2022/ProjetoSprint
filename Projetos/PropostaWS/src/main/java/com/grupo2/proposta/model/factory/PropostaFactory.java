package com.grupo2.proposta.model.factory;

import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.PropostaEstado;
import org.springframework.stereotype.Component;

@Component
public class PropostaFactory
{
    public Proposta createProposta(long id, long utilizadorId, long organizacaoId, String titulo, String problema, String objetivo, long edicaoUcId, PropostaEstado estadoAtual)
    {
        return new Proposta(id, utilizadorId,organizacaoId,titulo,problema,objetivo,edicaoUcId,estadoAtual);
    }

    public Proposta createProposta(long utilizadorId, long organizacaoId, String titulo, String problema, String objetivo, long edicaoUcId, PropostaEstado estadoAtual)
    {
        return new Proposta(utilizadorId,organizacaoId,titulo,problema,objetivo,edicaoUcId,estadoAtual);
    }
}
package com.grupo2.proposta.jpa.mapper;

import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.factory.PropostaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaJPAMapper
{
    @Autowired
    private PropostaFactory factory;

    public Proposta toModel(PropostaJPA jpa)
    {
        return factory.createProposta(jpa.getId(),jpa.getUtilizadorId(),jpa.getOrganizacaoId(),
                jpa.getTitulo(),jpa.getProblema(),jpa.getObjetivo(),
                jpa.getEdicaoUCId(),jpa.getEstadoAtual());
    }

    public PropostaJPA toJPA(Proposta model)
    {
        return new PropostaJPA(model.getId(),model.getUtilizadorId(),model.getOrganizacaoId(),
                model.getTitulo(),model.getProblema(),model.getObjetivo(),
                model.getEdicaoUCId(),model.getEstadoAtual());
    }
}

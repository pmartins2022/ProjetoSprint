package com.grupo2.proposta.jpa.mapper;

import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.factory.PropostaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe que mapeia uma PropostaJPA para uma Proposta ou vice-versa.
 */
@Component
public class PropostaJPAMapper
{
    /**
     * Objeto do tipo PropostaFactory a ser utilizado por PropostaJPAMapper
     */
    @Autowired
    private PropostaFactory factory;

    /**
     * Mapeia uma PropostaJPA para uma Proposta.
     * @param jpa PropostaJPA
     * @return Proposta
     */
    public Proposta toModel(PropostaJPA jpa)
    {
        return factory.createProposta(jpa.getId(),jpa.getUtilizadorId(),jpa.getOrganizacaoId(),
                jpa.getTitulo(),jpa.getProblema(),jpa.getObjetivo(),
                jpa.getEdicaoUCId(),jpa.getEstadoAtual());
    }

    /**
     * Mapeia uma Proposta para uma PropostaJPA.
     * @param model Proposta
     * @return PropostaJPA
     */
    public PropostaJPA toJPA(Proposta model)
    {
        return new PropostaJPA(model.getId(),model.getUtilizadorId(),model.getOrganizacaoId(),
                model.getTitulo(),model.getProblema(),model.getObjetivo(),
                model.getEdicaoUCId(),model.getEstadoAtual());
    }
}

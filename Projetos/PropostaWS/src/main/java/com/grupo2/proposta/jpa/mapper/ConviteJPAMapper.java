package com.grupo2.proposta.jpa.mapper;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.ConviteID;
import org.springframework.stereotype.Component;

/**
 * Classe que mapeia um Convite para um ConviteJPAMapper ou vice-versa.
 */
@Component
public class ConviteJPAMapper
{
    /**
     * Mapeia uma ConviteJPA para uma Convite.
     * @param jpa ConviteJPA
     * @return Convite
     */
    public Convite toModel(ConviteJPA jpa)
    {
        return new Convite(jpa.getId().getIdaluno(), jpa.getId().getIddocente(), jpa.getId().getIdproposta(), jpa.getEstado());
    }

    /**
     * Mapeia uma Convite para uma ConviteJPA.
     * @param conv Convite
     * @return ConviteJPA
     */
    public ConviteJPA toJPA(Convite conv)
    {
        return new ConviteJPA(new ConviteID(conv.getIdAluno(), conv.getIdDocente(), conv.getIdProposta()), conv.getEstado());
    }
}
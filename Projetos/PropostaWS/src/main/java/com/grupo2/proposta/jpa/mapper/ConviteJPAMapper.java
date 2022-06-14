package com.grupo2.proposta.jpa.mapper;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.ConviteID;
import org.springframework.stereotype.Component;

@Component
public class ConviteJPAMapper
{
    public Convite toModel(ConviteJPA jpa)
    {
        return new Convite(jpa.getId().getIdaluno(), jpa.getId().getIddocente(), jpa.getId().getIdproposta(), jpa.getEstado());
    }

    public ConviteJPA toJPA(Convite conv)
    {
        return new ConviteJPA(new ConviteID(conv.getIdAluno(), conv.getIdDocente(), conv.getIdProposta()), conv.getEstado());
    }
}
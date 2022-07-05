package com.grupo2.proposta.jpa;

import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.model.EstadoConvite;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Convite")
public class ConviteJPA
{
    @EmbeddedId
    private ConviteID id;

    private EstadoConvite estado;

    public ConviteJPA()
    {
    }

    public ConviteJPA(ConviteID id, EstadoConvite estado)
    {
        this.id = id;
        this.estado = estado;
    }

    public ConviteID getId()
    {
        return id;
    }

    public EstadoConvite getEstado()
    {
        return estado;
    }
}

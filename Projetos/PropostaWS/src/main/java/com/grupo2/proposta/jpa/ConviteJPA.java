package com.grupo2.proposta.jpa;

import com.grupo2.proposta.model.ConviteID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Convite")
public class ConviteJPA
{
    @EmbeddedId
    private ConviteID id;

    public ConviteJPA()
    {
    }

    public ConviteJPA(ConviteID id)
    {
        this.id = id;
    }

    public ConviteID getId()
    {
        return id;
    }
}

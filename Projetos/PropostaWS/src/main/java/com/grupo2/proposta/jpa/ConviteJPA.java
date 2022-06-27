package com.grupo2.proposta.jpa;

import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.model.EstadoConvite;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidade que representa uma ConviteJPA.
 */
@Entity
@Table(name = "Convite")
public class ConviteJPA
{
    /**
     * id de ConviteJPA
     */
    @EmbeddedId
    private ConviteID id;
    /**
     * estado de ConviteJPA
     */
    private EstadoConvite estado;

    /**
     * Inicializa um ConviteJPA sem parametros
     */
    public ConviteJPA()
    {
    }

    /**
     * Inicializa o id e o estado de ConviteJPA com o id e o estado
     * @param id e o id de ConviteJPA
     * @param estado e o estado de ConviteJPA
     */
    public ConviteJPA(ConviteID id, EstadoConvite estado)
    {
        this.id = id;
        this.estado = estado;
    }

    /**
     * Devolve o id de ConviteJPA
     * @return o id de ConviteJPA
     */
    public ConviteID getId()
    {
        return id;
    }

    /**
     * Devolve o estado de ConviteJPA
     * @return o estado de ConviteJPA
     */
    public EstadoConvite getEstado()
    {
        return estado;
    }
}

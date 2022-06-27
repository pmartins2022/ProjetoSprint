package com.grupo2.proposta.jpa;

import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidade que representa uma PropostaCandidaturaJPA.
 */
@Entity
@Table(name = "PropostaCandidatura")
public class PropostaCandidaturaJPA
{
    /**
     * PropostaCandidaturaJPA
     */
    @EmbeddedId
    private PropostaCandidaturaID id;
    /**
     * estado de PropostaCandidaturaJPA
     */
    private EstadoCandidatura estado;

    /**
     * Inicializa PropostaCandidaturaJPA sem parametros
     */
    public PropostaCandidaturaJPA()
    {
    }

    /**
     * Inicializa o id e o estado de PropostaCandidaturaJPA co o id e o estado
     * @param id e o id de PropostaCandidaturaJPA
     * @param estado e o estado de PropostaCandidaturaJPA
     */
    public PropostaCandidaturaJPA(PropostaCandidaturaID id, EstadoCandidatura estado)
    {
        this.id = id;
        this.estado = estado;
    }

    /**
     * Devolve o id de PropostaCandidaturaJPA
     * @return id de PropostaCandidaturaJPA
     */
    public PropostaCandidaturaID getId()
    {
        return id;
    }

    /**
     * Devolve o estado de PropostaCandidaturaJPA
     * @return estodo de PropostaCandidaturaJPA
     */
    public EstadoCandidatura getEstado()
    {
        return estado;
    }
}

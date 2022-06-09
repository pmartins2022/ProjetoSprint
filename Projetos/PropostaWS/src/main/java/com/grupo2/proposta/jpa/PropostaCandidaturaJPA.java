package com.grupo2.proposta.jpa;

import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PropostaCandidatura")
public class PropostaCandidaturaJPA
{
    @EmbeddedId
    private PropostaCandidaturaID id;
    private EstadoCandidatura estado;

    public PropostaCandidaturaJPA()
    {
    }

    public PropostaCandidaturaJPA(PropostaCandidaturaID id, EstadoCandidatura estado)
    {
        this.id = id;
        this.estado = estado;
    }

    public PropostaCandidaturaID getId()
    {
        return id;
    }

    public EstadoCandidatura getEstado()
    {
        return estado;
    }
}

package com.grupo2.proposta.jpa;

import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaInscricaoID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PropostaCandidatura")
public class PropostaInscricaoJPA
{
    @EmbeddedId
    private PropostaInscricaoID id;
    private EstadoCandidatura estado;

    public PropostaInscricaoJPA()
    {
    }

    public PropostaInscricaoJPA(PropostaInscricaoID id, EstadoCandidatura estado)
    {
        this.id = id;
        this.estado = estado;
    }

    public PropostaInscricaoID getId()
    {
        return id;
    }

    public EstadoCandidatura getEstado()
    {
        return estado;
    }
}

package com.grupo2.proposta.jpa.mapper;

import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;

public class PropostaCandidaturaJPAMapper
{
    public PropostaCandidatura toModel(PropostaCandidaturaJPA jpa)
    {
        return new PropostaCandidatura(jpa.getId().getIdProposta(), jpa.getId().getIdAluno(), jpa.getEstado());
    }

    public PropostaCandidaturaJPA toJPA(PropostaCandidatura cand)
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(cand.getIdProposta(), cand.getIdAluno());
        return new PropostaCandidaturaJPA(id,cand.getEstado());
    }
}
package com.grupo2.proposta.jpa.mapper;

import com.grupo2.proposta.jpa.PropostaInscricaoJPA;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaInscricaoID;
import org.springframework.stereotype.Component;

@Component
public class PropostaCandidaturaJPAMapper
{
    public PropostaCandidatura toModel(PropostaInscricaoJPA jpa)
    {
        return new PropostaCandidatura(jpa.getId().getIdProposta(), jpa.getId().getIdAluno(), jpa.getEstado());
    }

    public PropostaInscricaoJPA toJPA(PropostaCandidatura cand)
    {
        PropostaInscricaoID id = new PropostaInscricaoID(cand.getIdProposta(), cand.getIdAluno());
        return new PropostaInscricaoJPA(id,cand.getEstado());
    }
}
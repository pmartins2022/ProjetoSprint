package com.grupo2.proposta.jpa.mapper;

import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.stereotype.Component;

/**
 * Classe que mapeia um PropostaCandidatura para um PropostaCandidaturaJPAMapper ou vice-versa.
 */
@Component
public class PropostaCandidaturaJPAMapper
{
    /**
     * Mapeia uma PropostaCandidaturaJPA para uma PropostaCandidatura.
     * @param jpa PropostaCandidaturaJPA
     * @return PropostaCandidatura
     */
    public PropostaCandidatura toModel(PropostaCandidaturaJPA jpa)
    {
        return new PropostaCandidatura(jpa.getId().getidproposta(), jpa.getId().getIdaluno(), jpa.getEstado());
    }

    /**
     * Mapeia uma PropostaCandidatura para uma PropostaCandidaturaJPA.
     * @param cand PropostaCandidatura
     * @return PropostaCandidaturaJPA
     */
    public PropostaCandidaturaJPA toJPA(PropostaCandidatura cand)
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(cand.getIdProposta(), cand.getIdAluno());
        return new PropostaCandidaturaJPA(id,cand.getEstado());
    }
}
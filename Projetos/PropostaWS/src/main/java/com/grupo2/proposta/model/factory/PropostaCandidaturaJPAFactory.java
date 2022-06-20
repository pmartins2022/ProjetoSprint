package com.grupo2.proposta.model.factory;


import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.stereotype.Component;

@Component
public class PropostaCandidaturaJPAFactory
{

    public PropostaCandidaturaJPA create(PropostaCandidaturaID id, EstadoCandidatura estado)
    {

        return new PropostaCandidaturaJPA(id, estado);
    }
}

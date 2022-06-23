package com.grupo2.proposta.model.factory;


import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.stereotype.Component;

/**
 * Classe que cria objetos do tipo PropostaCandidaturaJPA
 */
@Component
public class PropostaCandidaturaJPAFactory
{
    /**
     * Cria uma PropostaCandidaturaJPA
     * @param id e o id de PropostaCandidaturaJPA
     * @param estado e o estado de PropostaCandidaturaJPA
     * @return PropostaCandidaturaJPA
     */
    public PropostaCandidaturaJPA create(PropostaCandidaturaID id, EstadoCandidatura estado)
    {

        return new PropostaCandidaturaJPA(id, estado);
    }
}

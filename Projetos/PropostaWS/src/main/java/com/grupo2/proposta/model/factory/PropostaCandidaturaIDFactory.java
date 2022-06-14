package com.grupo2.proposta.model.factory;

import com.grupo2.proposta.model.PropostaInscricaoID;
import org.springframework.stereotype.Component;

@Component
public class PropostaCandidaturaIDFactory
{
    public PropostaInscricaoID create(Long propostaId, Long alunoId)
    {
        return new PropostaInscricaoID(propostaId,alunoId);
    }

}

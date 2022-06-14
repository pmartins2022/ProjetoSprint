package com.grupo2.proposta.model.factory;

import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.stereotype.Component;

@Component
public class PropostaCandidaturaIDFactory
{
    public PropostaCandidaturaID create(Long propostaId, Long alunoId)
    {
        return new PropostaCandidaturaID(propostaId,alunoId);
    }

}

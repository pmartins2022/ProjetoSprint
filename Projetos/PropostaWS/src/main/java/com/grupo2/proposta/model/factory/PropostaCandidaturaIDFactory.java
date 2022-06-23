package com.grupo2.proposta.model.factory;

import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.EstadoConvite;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.stereotype.Component;

/**
 * Classe que cria objetos do tipo PropostaCandidaturaID
 */
@Component
public class PropostaCandidaturaIDFactory
{
    /**
     * Cria uma PropostaCandidaturaID
     * @param propostaId e o id da proposta
     * @param alunoId e o id do aluno
     * @return PropostaCandidaturaID
     */
    public PropostaCandidaturaID create(Long propostaId, Long alunoId)
    {
        return new PropostaCandidaturaID(propostaId,alunoId);
    }

}

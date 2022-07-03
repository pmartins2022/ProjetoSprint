package com.example.javafx.dto.factory;

import com.example.javafx.dto.PropostaCandidaturaIDDTO;
import org.springframework.stereotype.Component;

/**
 * Classe factory para criar objetos do tipo PropostaCandidaturaIDDTO
 */
@Component
public class PropostaCandidaturaIDDTOFactory
{
    /**
     * Criar um id de proposta candidatura
     * @param idProjeto id do projeto
     * @param idAluno id do aluno
     * @return proposta candidatura id criada
     */
    public PropostaCandidaturaIDDTO createPropostaCandidaturaIDDTO(Long idProjeto, Long idAluno)
    {
        return new PropostaCandidaturaIDDTO(idProjeto, idAluno);
    }
}

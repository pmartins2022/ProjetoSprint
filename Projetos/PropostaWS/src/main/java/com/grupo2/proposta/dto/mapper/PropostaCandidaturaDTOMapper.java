package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.PropostaCandidaturaDTO;
import com.grupo2.proposta.dto.PropostaCandidaturaIDDTO;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.stereotype.Component;

/**
 * Classe que mapeia um PropostaCandidatura para um PropostaCandidaturaDTO ou vice-versa.
 * Classe que mapeia um PropostaCandidaturaIDDTO para um PropostaCandidaturaID
 */
@Component
public class PropostaCandidaturaDTOMapper
{
    /**
     * Mapeia um PropostaCandidaturaDTO para uma PropostaCandidatura.
     * @param dto e o PropostaCandidaturaDTO
     * @return PropostaCandidatura
     */
    public PropostaCandidatura toModel(PropostaCandidaturaDTO dto)
    {
        return new PropostaCandidatura(dto.getIdProposta(),dto.getIdAluno(),dto.getEstado());
    }

    /**
     * Mapeia um PropostaCandidaturaDTO para uma PropostaCandidatura.
     * @param cand e o PropostaCandidatura
     * @return PropostaCandidaturaDTO
     */
    public PropostaCandidaturaDTO toDTO(PropostaCandidatura cand)
    {
        return new PropostaCandidaturaDTO(cand.getIdProposta(),cand.getIdAluno(),cand.getEstado());
    }

    /**
     * Mapeia um PropostaCandidaturaID para uma PropostaCandidaturaIDDTO.
     * @param dto e o PropostaCandidaturaIDDTO
     * @return PropostaCandidaturaID
     */
    public PropostaCandidaturaID toModelID(PropostaCandidaturaIDDTO dto)
    {
        return new PropostaCandidaturaID(dto.getIdProposta(),dto.getIdAluno());
    }
}
package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.PropostaCandidaturaDTO;
import com.grupo2.proposta.dto.PropostaCandidaturaIDDTO;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.stereotype.Component;

@Component
public class PropostaCandidaturaDTOMapper
{
    public PropostaCandidatura toModel(PropostaCandidaturaDTO dto)
    {
        return new PropostaCandidatura(dto.getIdProposta(),dto.getIdAluno(),dto.getEstado());
    }

    public PropostaCandidaturaDTO toDTO(PropostaCandidatura cand)
    {
        return new PropostaCandidaturaDTO(cand.getIdProposta(),cand.getIdAluno(),cand.getEstado());
    }

    public PropostaCandidaturaID toModelID(PropostaCandidaturaIDDTO dto)
    {
        return new PropostaCandidaturaID(dto.getIdProposta(),dto.getIdAluno());
    }
}
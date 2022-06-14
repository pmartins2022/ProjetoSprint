package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.PropostaInscricaoDTO;
import com.grupo2.proposta.model.PropostaCandidatura;
import org.springframework.stereotype.Component;

@Component
public class PropostaCandidaturaDTOMapper
{
    public PropostaCandidatura toModel(PropostaInscricaoDTO dto)
    {
        return new PropostaCandidatura(dto.getIdProposta(),dto.getIdAluno(),dto.getEstado());
    }

    public PropostaInscricaoDTO toDTO(PropostaCandidatura cand)
    {
        return new PropostaInscricaoDTO(cand.getIdProposta(),cand.getIdAluno(),cand.getEstado());
    }
}
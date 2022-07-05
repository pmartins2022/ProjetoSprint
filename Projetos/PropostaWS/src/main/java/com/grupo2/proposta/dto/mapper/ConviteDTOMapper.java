package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.model.Convite;
import org.springframework.stereotype.Component;

/**
 * Classe que mapeia um Convite para um ConviteDTO ou vice-versa.
 */
@Component
public class ConviteDTOMapper
{
    /**
     * Mapeia um Convite para uma ConviteDTO.
     * @param conv e o Convite
     * @return ConviteDTO
     */
    public ConviteDTO toDTO (Convite conv)
    {
        return new ConviteDTO(conv.getIdAluno(), conv.getIdDocente(), conv.getIdProposta(), conv.getEstado());
    }

    /**
     * Mapeia um PropostaDTO para uma Proposta.
     * @param dto e o ConviteDTO
     * @return Convite
     */
    public Convite toModel(ConviteDTO dto)
    {
        return new Convite(dto.getIdAluno(),dto.getIdDocente(), dto.getIdProposta(),dto.getEstado());
    }
}
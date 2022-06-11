package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.model.Convite;
import org.springframework.stereotype.Component;

@Component
public class ConviteDTOMapper
{
    public ConviteDTO toDTO (Convite conv)
    {
        return new ConviteDTO(conv.getIdAluno(), conv.getIdDocente(), conv.getIdProposta(), conv.getEstado());
    }

    public Convite toModel(ConviteDTO dto)
    {
        return new Convite(dto.getIdAluno(),dto.getIdDocente(), dto.getIdProposta(),dto.getEstado());
    }
}
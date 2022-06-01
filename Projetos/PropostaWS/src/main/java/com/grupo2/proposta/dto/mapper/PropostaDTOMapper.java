package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.factory.PropostaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaDTOMapper
{
    @Autowired
    private PropostaFactory factory;

    public Proposta toModel(PropostaDTO dto)
    {
        if (dto.getId() != null)
        {
            return factory.createProposta(dto.getId(), dto.getUtilizadorId(), dto.getOrganizacaoId(),
                    dto.getTitulo(), dto.getProblema(), dto.getObjetivo(),
                    dto.getEdicaoUCId(), dto.getEstadoAtual());
        }
        return factory.createProposta(dto.getUtilizadorId(), dto.getOrganizacaoId(),
                dto.getTitulo(), dto.getProblema(), dto.getObjetivo(),
                dto.getEdicaoUCId(), dto.getEstadoAtual());
    }

    public PropostaDTO toDTO(Proposta model)
    {
        return new PropostaDTO(model.getId(),model.getUtilizadorId(),model.getOrganizacaoId(),
                model.getTitulo(),model.getProblema(),model.getObjetivo(),
                model.getEdicaoUCId(),model.getEstadoAtual());
    }

}

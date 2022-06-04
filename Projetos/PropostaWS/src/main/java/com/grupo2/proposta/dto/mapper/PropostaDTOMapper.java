package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.factory.PropostaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe que mapeia um Proposta para um PropostaDTO ou vice-versa.
 */
@Component
public class PropostaDTOMapper
{
    @Autowired
    private PropostaFactory factory;

    /**
     * Mapeia um PropostaDTO para uma Proposta.
     * @param dto o PropostaDTO
     * @return a Proposta
     */
    public Proposta toModel(PropostaDTO dto)
    {
        return factory.createProposta(dto.getId(), dto.getUtilizadorId(), dto.getOrganizacaoId(),
                dto.getTitulo(), dto.getProblema(), dto.getObjetivo(),
                dto.getEdicaoUCId(), dto.getEstadoAtual());
    }

    /**
     * Mapeia uma Proposta para um PropostaDTO.
     * @param model a Proposta
     * @return o PropostaDTO
     */
    public PropostaDTO toDTO(Proposta model)
    {
        return new PropostaDTO(model.getId(), model.getUtilizadorId(), model.getOrganizacaoId(),
                model.getTitulo(), model.getProblema(), model.getObjetivo(),
                model.getEdicaoUCId(), model.getEstadoAtual());
    }

}

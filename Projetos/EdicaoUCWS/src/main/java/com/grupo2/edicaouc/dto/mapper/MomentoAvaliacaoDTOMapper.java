package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.MomentoAvaliacaoDTO;
import com.grupo2.edicaouc.model.MomentoAvaliacao;
import com.grupo2.edicaouc.model.factory.MomentoAvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer conversao de objetos do tipo MomentoAvaliacao
 */
@Component
public class MomentoAvaliacaoDTOMapper
{
    @Autowired
    private MomentoAvaliacaoFactory factory;

    /**
     * Converter para objeto de dominio
     * @param dto objeto a converter
     * @return objeto convertido
     */
    public MomentoAvaliacao toModel(MomentoAvaliacaoDTO dto)
    {
        return factory.create(dto.getId(),dto.getEdicaoUCID(),  dto.getDenominacao());
    }

    /**
     * Converter para objeto DTO
     * @param model objeto a converter
     * @return objeto convertido
     */
    public MomentoAvaliacaoDTO toDTO(MomentoAvaliacao model)
    {
        return new MomentoAvaliacaoDTO(model.getId(),model.getIdEdicao(), model.getDenominacao());
    }
}

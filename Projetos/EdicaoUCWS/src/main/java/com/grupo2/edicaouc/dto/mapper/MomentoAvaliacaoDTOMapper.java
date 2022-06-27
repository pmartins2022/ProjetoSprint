package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.MomentoAvaliacaoDTO;
import com.grupo2.edicaouc.model.MomentoAvaliacao;
import com.grupo2.edicaouc.model.factory.MomentoAvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoDTOMapper
{
    @Autowired
    private MomentoAvaliacaoFactory factory;

    public MomentoAvaliacao toModel(MomentoAvaliacaoDTO dto)
    {
        return factory.create(dto.getId(),dto.getEdicaoUCID(),  dto.getDenominacao());
    }

    public MomentoAvaliacaoDTO toDTO(MomentoAvaliacao model)
    {
        return new MomentoAvaliacaoDTO(model.getId(),model.getIdEdicao(), model.getDenominacao());
    }
}

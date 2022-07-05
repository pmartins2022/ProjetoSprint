package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.EdicaoMomentoAvaliacaoDTO;
import com.grupo2.edicaouc.model.EdicaoMomentoAvaliacao;
import org.springframework.stereotype.Component;

@Component
public class EdicaoMomentoAvaliacaoDTOMapper
{
    public EdicaoMomentoAvaliacao toModel(EdicaoMomentoAvaliacaoDTO dto)
    {
        return new EdicaoMomentoAvaliacao(dto.getIdEdicao(),dto.getIdMomentoAvaliacao());
    }

    public EdicaoMomentoAvaliacaoDTO toDTO(EdicaoMomentoAvaliacao model)
    {
        return new EdicaoMomentoAvaliacaoDTO(model.getIdEdicao(),model.getIdMomentoAvaliacao());
    }
}

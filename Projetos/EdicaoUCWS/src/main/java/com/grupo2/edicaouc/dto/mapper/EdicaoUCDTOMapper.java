package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCDTOMapper
{
    @Autowired
    private EdicaoUCFactory edicaoUCFactory;

    public EdicaoUC toModel(EdicaoUCDTO dto)
    {
        return edicaoUCFactory.createEdicaoUC(dto.getId(),dto.getUcCode(), dto.getAnoLetivoCode());
    }

    public EdicaoUCDTO toDTO(EdicaoUC edicao)
    {
        return new EdicaoUCDTO(edicao.getId(), edicao.getUCCode(),edicao.getAnoLetivoCode());
    }
}

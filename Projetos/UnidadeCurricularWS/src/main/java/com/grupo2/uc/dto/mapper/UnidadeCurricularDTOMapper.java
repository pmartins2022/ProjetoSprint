package com.grupo2.uc.dto.mapper;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.model.factory.UnidadeCurricularFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnidadeCurricularDTOMapper
{
    @Autowired
    private UnidadeCurricularFactory factory;

    public UnidadeCurricular toModel(UnidadeCurricularDTO dto)
    {
        return factory.createUnidadeCurricular(dto.getSigla(),dto.getDenominacao());
    }

    public UnidadeCurricularDTO toDTO(UnidadeCurricular model)
    {
        return new UnidadeCurricularDTO(model.getSigla(),model.getDenominacao());
    }
}
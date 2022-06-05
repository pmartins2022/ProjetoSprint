package com.grupo2.uc.dto.mapper;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.model.factory.UnidadeCurricularFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao entre objetos UnidadeCurricular de DTO para classe de dominio.
 */
@Component
public class UnidadeCurricularDTOMapper
{

    /**
     * O factory a ser utilizado por este DTO Mapper.
     */
    @Autowired
    private UnidadeCurricularFactory factory;

    /**
     *Fazer a conversao para classe de dominio.
     * @param dto o objeto dto com os dados
     * @return o objeto convertido
     */
    public UnidadeCurricular toModel(UnidadeCurricularDTO dto)
    {
        return factory.createUnidadeCurricular(dto.getSigla(),dto.getDenominacao());
    }

    /**
     * Fazer a conversao para classe DTO
     * @param model o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public UnidadeCurricularDTO toDTO(UnidadeCurricular model)
    {
        return new UnidadeCurricularDTO(model.getSigla(),model.getDenominacao());
    }
}
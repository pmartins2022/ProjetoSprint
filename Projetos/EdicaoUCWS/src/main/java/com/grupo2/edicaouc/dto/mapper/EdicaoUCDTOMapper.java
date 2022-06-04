package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Classe para fazer a conversao entre objetos EdicaoUC de DTO para classe de dominio.
 */
@Component
public class EdicaoUCDTOMapper
{
    /**
     * O factory a ser utilizado por este DTO Mapper.
     */
    @Autowired
    private EdicaoUCFactory edicaoUCFactory;

    /**
     * Fazer conversao para classe de dominio
     * @param dto o objeto dto com os dados
     * @return o objeto convertido
     */
    public EdicaoUC toModel(EdicaoUCDTO dto)
    {
        return edicaoUCFactory.createEdicaoUC(dto.getId(),dto.getUcCode(), dto.getAnoLetivoCode());
    }

    /**
     * Fazer a conversao para classe DTO
     * @param edicao o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public EdicaoUCDTO toDTO(EdicaoUC edicao)
    {
        return new EdicaoUCDTO(edicao.getId(), edicao.getUCCode(),edicao.getAnoLetivoCode());
    }
}

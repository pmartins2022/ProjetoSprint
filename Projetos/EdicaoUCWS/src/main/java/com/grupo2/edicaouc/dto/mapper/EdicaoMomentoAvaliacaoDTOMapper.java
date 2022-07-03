package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.EdicaoMomentoAvaliacaoDTO;
import com.grupo2.edicaouc.model.EdicaoMomentoAvaliacao;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer conversao de objetos do tipo EdicaoMomentoAvaliacao
 */
@Component
public class EdicaoMomentoAvaliacaoDTOMapper
{
    /**
     * Converter para objeto DTO
     * @param dto objeto a converter
     * @return objeto convertido
     */
    public EdicaoMomentoAvaliacao toModel(EdicaoMomentoAvaliacaoDTO dto)
    {
        return new EdicaoMomentoAvaliacao(dto.getIdEdicao(),dto.getIdMomentoAvaliacao());
    }

    /**
     * Converter para objeto de dominio
     * @param model objeto a converter
     * @return objeto convertido
     */
    public EdicaoMomentoAvaliacaoDTO toDTO(EdicaoMomentoAvaliacao model)
    {
        return new EdicaoMomentoAvaliacaoDTO(model.getIdEdicao(),model.getIdMomentoAvaliacao());
    }
}

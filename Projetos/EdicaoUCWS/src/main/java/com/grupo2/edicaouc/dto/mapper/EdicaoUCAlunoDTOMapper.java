package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.EdicaoUCAlunoDTO;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer conversao de objetos do tipo EdicaoUCAluno
 */
@Component
public class EdicaoUCAlunoDTOMapper
{
    /**
     * Converter para objeto DTO
     * @param edicaoUCAluno objeto a converter
     * @return objeto convertido
     */
    public EdicaoUCAlunoDTO toDTO(EdicaoUCAluno edicaoUCAluno)
    {
        return new EdicaoUCAlunoDTO(edicaoUCAluno.getEdicaoUCID(), edicaoUCAluno.getAlunoID());
    }


    /**
     * Converter para objeto DTO
     * @param edicaoUCAlunoDTO objeto a converter
     * @return objeto convertido
     */
    public EdicaoUCAluno toModel(EdicaoUCAlunoDTO edicaoUCAlunoDTO)
    {
        return new EdicaoUCAluno(edicaoUCAlunoDTO.getEdicaoUCID(), edicaoUCAlunoDTO.getAlunoID());
    }
}

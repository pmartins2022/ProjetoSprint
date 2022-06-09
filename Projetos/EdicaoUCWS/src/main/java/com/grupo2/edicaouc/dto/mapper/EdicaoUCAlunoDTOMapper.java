package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.EdicaoUCAlunoDTO;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCAlunoDTOMapper
{
    public EdicaoUCAlunoDTO toDTO(EdicaoUCAluno edicaoUCAluno)
    {
        return new EdicaoUCAlunoDTO(edicaoUCAluno.getEdicaoUCID(), edicaoUCAluno.getAlunoID());
    }

    public EdicaoUCAluno toModel(EdicaoUCAlunoDTO edicaoUCAlunoDTO)
    {
        return new EdicaoUCAluno(edicaoUCAlunoDTO.getEdicaoUCID(), edicaoUCAlunoDTO.getAlunoID());
    }
}

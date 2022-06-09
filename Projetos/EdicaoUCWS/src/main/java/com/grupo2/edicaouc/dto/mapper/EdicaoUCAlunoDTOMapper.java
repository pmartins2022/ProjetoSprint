package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.EdicaoUCAlunoDTO;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCAlunoDTOMapper
{
    public EdicaoUCAluno toModel(EdicaoUCAlunoDTO edicaoUCAlunoDTO)
    {
        return new EdicaoUCAluno(edicaoUCAlunoDTO.getIdEdicaoUC(), edicaoUCAlunoDTO.getIdAluno());
    }

    public EdicaoUCAlunoDTO toDTO(EdicaoUCAluno edicaoUCAluno)
    {
        return new EdicaoUCAlunoDTO(edicaoUCAluno.getIdEdicaoUC(), edicaoUCAluno.getIdAluno());
    }
}

package com.grupo2.projeto.dto.mapper;

import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
import com.grupo2.projeto.model.MomentoAvaliacao;
import com.grupo2.projeto.model.factory.MomentoAvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoDTOMapper {

    @Autowired
    MomentoAvaliacaoFactory momentoAvaliacaoFactory;

    public MomentoAvaliacao toModel(MomentoAvaliacaoDTO dto){
        return momentoAvaliacaoFactory.createMomentoAvaliacao(dto.getId(),dto.getProjetoId());
    }

    public MomentoAvaliacaoDTO toDTO(MomentoAvaliacao momentoAvaliacao){
        return new MomentoAvaliacaoDTO(momentoAvaliacao.getId(), momentoAvaliacao.getProjetoId());
    }
}

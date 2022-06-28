package com.grupo2.projeto.dto.mapper;


import com.grupo2.projeto.dto.MomentoAvaliacaoNotaDTO;
import com.grupo2.projeto.model.MomentoAvaliacaoNota;
import com.grupo2.projeto.model.factory.MomentoAvaliacaoNotaFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MomentoAvaliacaoNotaMapper
{
    @Autowired
    private MomentoAvaliacaoNotaFactory factory;

    public MomentoAvaliacaoNota toModel(MomentoAvaliacaoNotaDTO dto)
    {
        return factory.createMomentoAvaliacaoNota(dto.getId(), dto.getIdAvaliacao(), dto.getNota(), dto.getJustificacao(), dto.getEstadoAvaliacao());
    }

    public MomentoAvaliacaoNotaDTO toDTO(MomentoAvaliacaoNota saved)
    {
        return new MomentoAvaliacaoNotaDTO(saved.getId(), saved.getIdAvaliacao(), saved.getNota(), saved.getJustificacao(), saved.getEstadoAvaliacao());
    }
}

package com.grupo2.projeto.dto.mapper;


import com.grupo2.projeto.dto.MomentoAvaliacaoNotaDTO;
import com.grupo2.projeto.model.AvaliacaoNota;
import com.grupo2.projeto.model.factory.MomentoAvaliacaoNotaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MomentoAvaliacaoNotaMapper
{
    @Autowired
    private MomentoAvaliacaoNotaFactory factory;

    public AvaliacaoNota toModel(MomentoAvaliacaoNotaDTO dto)
    {
        return factory.createMomentoAvaliacaoNota(dto.getIdAvaliacao(), dto.getNota(), dto.getJustificacao());
    }

    public MomentoAvaliacaoNotaDTO toDTO(AvaliacaoNota saved)
    {
        return new MomentoAvaliacaoNotaDTO(saved.getId(), saved.getIdAvaliacao(), saved.getNota(), saved.getJustificacao(), saved.getEstadoAvaliacao());
    }
}

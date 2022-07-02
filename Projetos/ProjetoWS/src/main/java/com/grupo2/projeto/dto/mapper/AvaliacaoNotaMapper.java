package com.grupo2.projeto.dto.mapper;


import com.grupo2.projeto.dto.AvaliacaoNotaDTO;
import com.grupo2.projeto.model.AvaliacaoNota;
import com.grupo2.projeto.model.factory.AvaliacaoNotaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoNotaMapper
{
    @Autowired
    private AvaliacaoNotaFactory factory;

    public AvaliacaoNota toModel(AvaliacaoNotaDTO dto)
    {
        return factory.createMomentoAvaliacaoNota(dto.getIdAvaliacao(), dto.getNota(), dto.getJustificacao());
    }

    public AvaliacaoNotaDTO toDTO(AvaliacaoNota saved)
    {
        return new AvaliacaoNotaDTO(saved.getId(), saved.getIdAvaliacao(), saved.getNota(), saved.getJustificacao(), saved.getEstadoAvaliacao());
    }
}

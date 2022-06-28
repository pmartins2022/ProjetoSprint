package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.MomentoAvaliacaoNotaDTO;
import com.grupo2.projeto.dto.mapper.MomentoAvaliacaoNotaMapper;
import com.grupo2.projeto.model.MomentoAvaliacaoNota;
import com.grupo2.projeto.repository.jdbc.RepositoryJDBCMomentoAvaliacaoNota;
import org.springframework.stereotype.Service;

@Service
public class MomentoAvaliacaoNotaService
{
    RepositoryJDBCMomentoAvaliacaoNota repository;

    MomentoAvaliacaoNotaMapper mapper;
    public int createAvaliacaoNota(MomentoAvaliacaoNotaDTO dto) throws IllegalAccessException
    {
        MomentoAvaliacaoNota mom = mapper.toModel(dto);
        return repository.createAvaliacaoNota(mom);
    }
}

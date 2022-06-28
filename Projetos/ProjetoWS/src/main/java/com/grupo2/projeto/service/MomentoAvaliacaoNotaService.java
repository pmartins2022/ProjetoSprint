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
        //idAvaliacao
        //Presidente está em várias avaliacoes

        //pelo idAvalicao findByID o Presinete  e ver se é idAvaliacao.idPresidente == LoginContext.presidente
        //ver se NAO está em REVISAO OU CONCLUIDA
        //Se ESTIVER em REVISAO a nota tem que ser NULL

        MomentoAvaliacaoNota mom = mapper.toModel(dto);
        return repository.createAvaliacaoNota(mom);
    }
}

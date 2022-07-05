package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.MomentoAvaliacaoJPA;
import com.grupo2.edicaouc.jpa.mapper.MomentoAvaliacaoJPAMapper;
import com.grupo2.edicaouc.model.MomentoAvaliacao;
import com.grupo2.edicaouc.repository.jpa.MomentoAvaliacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MomentoAvaliacaoRepository
{
    @Autowired
    private MomentoAvaliacaoJPARepository repo;

    @Autowired
    private MomentoAvaliacaoJPAMapper mapper;

    public MomentoAvaliacao createAndSaveMomentoAvaliacao(MomentoAvaliacao momento)
    {
        MomentoAvaliacaoJPA jpa = mapper.toJPA(momento);

        MomentoAvaliacaoJPA save = repo.save(jpa);

        return mapper.toModel(save);
    }

    public Optional<MomentoAvaliacao> findById(Long idMomentoAvaliacao)
    {
        Optional<MomentoAvaliacaoJPA> id = repo.findById(idMomentoAvaliacao);

        if (id.isPresent())
        {
            return Optional.of(mapper.toModel(id.get()));
        }

        return Optional.empty();
    }
}
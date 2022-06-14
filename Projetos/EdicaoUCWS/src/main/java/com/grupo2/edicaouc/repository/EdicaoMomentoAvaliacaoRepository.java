package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoMomentoAvaliacaoJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoMomentoAvaliacaoJPAMapper;
import com.grupo2.edicaouc.model.EdicaoMomentoAvaliacao;
import com.grupo2.edicaouc.repository.jpa.EdicaoMomentoAvaliacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EdicaoMomentoAvaliacaoRepository
{
    @Autowired
    private EdicaoMomentoAvaliacaoJPARepository jpaRepo;

    @Autowired
    private EdicaoMomentoAvaliacaoJPAMapper mapper;

    public EdicaoMomentoAvaliacao createAndSaveEdicaoMomentoAvaliacao(EdicaoMomentoAvaliacao model)
    {
        EdicaoMomentoAvaliacaoJPA jpa = mapper.toJPA(model);

        EdicaoMomentoAvaliacaoJPA save = jpaRepo.save(jpa);

        return mapper.toModel(save);
    }
}

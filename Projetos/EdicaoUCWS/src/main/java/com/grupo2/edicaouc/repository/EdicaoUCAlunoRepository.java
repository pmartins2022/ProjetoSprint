package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCAlunoJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCAlunoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EdicaoUCAlunoRepository
{
    @Autowired
    private EdicaoUCAlunoJPAMapper mapper;
    @Autowired
    private EdicaoUCAlunoJPARepository jpaRepository;


    public EdicaoUCAluno saveEdicaoUCAluno(Long edicaoUCID, Long alunoID)
    {
        EdicaoUCAlunoID id = new EdicaoUCAlunoID(edicaoUCID, alunoID);

        EdicaoUCAlunoJPA jpa = new EdicaoUCAlunoJPA(id);

        EdicaoUCAlunoJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    public Boolean isStudentAvailable(Long alunoID)
    {

        return null;
    }
}

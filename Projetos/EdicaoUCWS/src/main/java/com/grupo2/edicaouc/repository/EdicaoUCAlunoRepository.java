package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCAlunoJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCAlunoJPARepository;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class EdicaoUCAlunoRepository
{
    @Autowired
    private EdicaoUCAlunoJPAMapper mapper;
    @Autowired
    private EdicaoUCAlunoJPARepository jpaRepository;
    @Autowired
    private EdicaoUCJpaRepository jpaEdicaoUCRepository;


    public EdicaoUCAluno saveEdicaoUCAluno(Long edicaoUCID, Long alunoID)
    {
        EdicaoUCAlunoID id = new EdicaoUCAlunoID(edicaoUCID, alunoID);

        EdicaoUCAlunoJPA jpa = new EdicaoUCAlunoJPA(id);

        EdicaoUCAlunoJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    public Boolean isStudentAvailable(Long alunoID)
    {
        List<EdicaoUCAlunoJPA> list = jpaRepository.findAll();

        List<EdicaoUCJPA> edicaoList = jpaEdicaoUCRepository.findAll();

        for (EdicaoUCJPA edicaoUC : edicaoList)
        {
            if (edicaoUC.getEstadoEdicaoUC().ordinal() == 1)
            {
                for (EdicaoUCAlunoJPA jpa : list
                )
                {
                    if (Objects.equals(jpa.getId().getAlunoID(), alunoID))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

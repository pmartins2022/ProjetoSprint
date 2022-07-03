package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCAlunoJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCAlunoIDFactory;
import com.grupo2.edicaouc.model.factory.EdicaoUCAlunoJPAFactory;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCAlunoJPARepository;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Classe EdicaoUCAlunoRepository que permite estabeler ligação com EdicaoUCAlunoJPARepository
 */
@Repository
public class EdicaoUCAlunoRepository
{
    @Autowired
    private EdicaoUCAlunoJPAMapper mapper;
    @Autowired
    private EdicaoUCAlunoJPARepository jpaRepository;
    @Autowired
    private EdicaoUCJpaRepository jpaEdicaoUCRepository;
    @Autowired
    private EdicaoUCAlunoIDFactory idFactory;
    @Autowired
    private EdicaoUCAlunoJPAFactory jpaFactory;

    /**
     * Devolve EdicaoUCAluno após persistência
     * @param edicaoUCID id de Edição
     * @param alunoID id de Aluno
     * @return EdicaoUCAluno
     */
    public EdicaoUCAluno saveEdicaoUCAluno(Long edicaoUCID, Long alunoID)
    {
        EdicaoUCAlunoID id = idFactory.create(edicaoUCID, alunoID);

        EdicaoUCAlunoJPA jpa = jpaFactory.create(id);

        EdicaoUCAlunoJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    /**
     * Devolve true ou false caso o aluno esteja inscrito em alguma EdiçãoUC
     * @param alunoID id de Aluno
     * @return boolean
     */
    public boolean isStudentAvailable(Long alunoID)
    {
        List<EdicaoUCAlunoJPA> list = jpaRepository.findAll();

        List<EdicaoUCJPA> edicaoList = jpaEdicaoUCRepository.findAll();

        for (EdicaoUCJPA edicaoUC : edicaoList)
        {
            if (edicaoUC.getEstadoEdicaoUC() == EstadoEdicaoUC.ATIVA)
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

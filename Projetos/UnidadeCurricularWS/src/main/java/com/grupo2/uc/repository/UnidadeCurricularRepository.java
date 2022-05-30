package com.grupo2.uc.repository;

import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import com.grupo2.uc.jpa.mapper.UnidadeCurricularJPAMapper;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.repository.jpa.UnidadeCurricularJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnidadeCurricularRepository
{
    @Autowired
    private UnidadeCurricularJPARepository jpaRepository;

    @Autowired
    private UnidadeCurricularJPAMapper mapper;

    public UnidadeCurricular saveUnidadeCurricular(UnidadeCurricular unidadeCurricular)
    {
        if (findById(unidadeCurricular.getSigla()).isPresent())
        {
            throw new ErroGeralException("Ano Letivo já existente.");
        }

        UnidadeCurricularJPA jpa = mapper.toJPA(unidadeCurricular);

        UnidadeCurricularJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    //TODO: criar os variados casos de uso no repositorio
}
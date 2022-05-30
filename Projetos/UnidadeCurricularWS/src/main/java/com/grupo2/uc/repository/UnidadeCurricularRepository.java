package com.grupo2.uc.repository;

import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import com.grupo2.uc.jpa.mapper.UnidadeCurricularJPAMapper;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.repository.jpa.UnidadeCurricularJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UnidadeCurricularRepository
{
    @Autowired
    private UnidadeCurricularJPARepository jpaRepository;

    @Autowired
    private UnidadeCurricularJPAMapper mapper;

    public Optional<UnidadeCurricular> findByID(String sigla)
    {
        Optional<UnidadeCurricularJPA> jpa = jpaRepository.findBySigla(sigla);

        if (jpa.isEmpty())
        {
            return Optional.empty();
        }

        UnidadeCurricular unidadeCurricular = mapper.toModel(jpa.get());


        return Optional.of(unidadeCurricular);
    }

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
}
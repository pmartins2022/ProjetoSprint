package com.grupo2.uc.repository;

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

    //TODO: criar os variados casos de uso no repositorio
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


}
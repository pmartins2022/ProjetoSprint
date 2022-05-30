package com.grupo2.uc.repository;

import com.grupo2.uc.repository.jpa.UnidadeCurricularJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnidadeCurricularRepository
{
    @Autowired
    private UnidadeCurricularJPARepository jpaRepository;

    //TODO: criar os variados casos de uso no repositorio
}
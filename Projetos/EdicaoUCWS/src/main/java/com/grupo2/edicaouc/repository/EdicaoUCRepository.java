package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.exception.BaseDadosException;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import com.grupo2.edicaouc.repository.rest.AnoLetivoUCRestRepository;
import com.grupo2.edicaouc.repository.rest.UnidadeCurricularRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoUCRepository
{
    @Autowired
    private EdicaoUCJpaRepository jpaRepository;

    @Autowired
    private AnoLetivoUCRestRepository anoLetivoUCRestRepository;

    @Autowired
    private UnidadeCurricularRestRepository unidadeCurricularRestRepository;
    @Autowired
    private EdicaoUCJPAMapper mapper;

    public EdicaoUC saveEdicaoUC(EdicaoUC edicaoUC) throws BaseDadosException
    {
        Optional<AnoLetivoDTO> anoLetivoId = anoLetivoUCRestRepository.findById(edicaoUC.getAnoLetivoCode());

        if (anoLetivoId.isEmpty()){
            throw new BaseDadosException("ID de Ano Letivo " +edicaoUC.getAnoLetivoCode()+ " não existe");
        }

        Optional<UnidadeCurricularDTO> unidadeCurricular = unidadeCurricularRestRepository.findById(edicaoUC.getUCCode());

        if (unidadeCurricular.isEmpty()){
            throw new BaseDadosException("ID de Unidade Curricular "+edicaoUC.getUCCode()+" não existe");
        }
        EdicaoUCJPA jpa = mapper.toJpa(edicaoUC);
        EdicaoUCJPA saved = jpaRepository.save(jpa);
        return mapper.toModel(saved);
    }


    public List<EdicaoUC> findAllEdicaoByUCCode(String UCCode)
    {
        List<EdicaoUCJPA> lista = jpaRepository.findAllByucCode(UCCode);

        return lista.stream().map(mapper::toModel).toList();
    }

    public Optional<EdicaoUC> findByAnoLetivoCode(String UCCode)
    {
        Optional<EdicaoUCJPA> jpa = jpaRepository.findByanoLetivoCode(UCCode);
        if (jpa.isPresent())
        {
            return jpa.map(mapper::toModel);
        }
        else
        {
            return Optional.empty();
        }
    }

    public Optional<EdicaoUC> findById(Long id)
    {
        Optional<EdicaoUCJPA> jpa = jpaRepository.findById(id);

        if (jpa.isPresent())
        {
            return Optional.of(mapper.toModel(jpa.get()));
        }

        return Optional.empty();

    }
}

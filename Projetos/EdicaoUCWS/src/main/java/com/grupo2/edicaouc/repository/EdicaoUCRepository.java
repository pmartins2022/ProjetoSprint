package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoUCRepository
{
    @Autowired
    private EdicaoUCJpaRepository jpaRepository;

    @Autowired
    private EdicaoUCJPAMapper mapper;

    public EdicaoUC saveEdicaoUC(EdicaoUC edicaoUC)
    {
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

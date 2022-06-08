package com.pp.utilizadorWS.repository.jpa;

import com.pp.utilizadorWS.jpa.UtilizadorJPA;
import com.pp.utilizadorWS.jpa.mapper.UtilizadorJPAMapper;
import com.pp.utilizadorWS.model.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UtilizadorRepository
{
    @Autowired
    private UtilizadorJpaRepository utilizadorJpaRepository;

    @Autowired
    private UtilizadorJPAMapper mapper;

    public Optional<Utilizador> findByUsername(String username)
    {
        Optional<UtilizadorJPA> jpa = utilizadorJpaRepository.findByUsername(username);

        if (jpa.isPresent())
        {
            return Optional.of(mapper.toModel(jpa.get()));
        }
        else
        {
            return Optional.empty();
        }
    }

    public Utilizador save(Utilizador utilizador)
    {
        UtilizadorJPA jpa = mapper.toJPA(utilizador);
        jpa = utilizadorJpaRepository.save(jpa);
        return mapper.toModel(jpa);
    }
}
package com.grupo2.utilizadores.repository;

import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.jpa.mapper.UtilizadorJPAMapper;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.jpa.UtilizadorJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public class UtilizadorRepository
{
    @Autowired
    private UtilizadorJPARepository jpaRepository;

    @Autowired
    private UtilizadorJPAMapper mapper;


    public Optional<Utilizador> findByID(Long id)
    {
        Optional<UtilizadorJPA> utilizadorJPA = jpaRepository.findById(id);

        if (utilizadorJPA.isEmpty())
        {
            return Optional.empty();
        }

        Utilizador utilizador = mapper.toModel(utilizadorJPA.get());
        return Optional.of(utilizador);
    }

    public Utilizador save(Utilizador utilizador)
    {
        UtilizadorJPA jpa = mapper.toJPA(utilizador);

        UtilizadorJPA jpaSaved = jpaRepository.save(jpa);

        Utilizador saved = mapper.toModel(jpaSaved);

        return saved;
    }
}

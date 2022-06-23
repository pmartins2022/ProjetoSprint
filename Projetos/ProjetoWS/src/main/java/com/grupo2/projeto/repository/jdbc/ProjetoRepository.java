package com.grupo2.projeto.repository.jdbc;

import com.grupo2.projeto.model.Projeto;

import java.util.List;
import java.util.Optional;

public interface ProjetoRepository
{
    Integer count();

    int save(Projeto Projeto);

    int update(Projeto Projeto);

    int deleteById(Long id);

    List<Projeto> findAll();

    Optional<Projeto> findById(Long id);

    int deleteAll();

}

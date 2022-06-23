package com.grupo2.projeto.repository.jdbc;

import com.grupo2.projeto.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryJDBCProjeto implements ProjetoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count()
    {
        return jdbcTemplate
                .queryForObject("select count(*) from projeto", Integer.class);
    }

    @Override
    public int save(Projeto projeto)
    {
        return jdbcTemplate.update(
                "insert into projeto values(?, ?,?)",
                projeto.getId(), projeto.getOrientadorId(), projeto.getEstudanteId());
    }

    @Override
    public int update(Projeto projeto)
    {
        return 0;
    }

    @Override
    public int deleteById(Long id)
    {
        return 0;
    }

    @Override
    public List<Projeto> findAll()
    {
        return null;
    }

    @Override
    public Optional<Projeto> findById(Long id)
    {
        return Optional.empty();
    }
}
package com.grupo2.projeto.repository.jdbc;

import com.grupo2.projeto.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryJDBCProjeto implements ProjetoRepository
{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer count()
    {
        return jdbcTemplate
                .queryForObject("select count(*) from projeto", Integer.class);
    }


    @Override
    public int save(Projeto projeto)
    {
        return jdbcTemplate.update(
                "insert into projeto values (?, ?, ?, ?)",
                projeto.getId(), projeto.getPropostaId(), projeto.getEstudanteId(), projeto.getOrientadorId());
    }

    @Override
    public int update(Projeto projeto)
    {
        return jdbcTemplate.update("UPDATE tutorials SET propostaId=? estudanteId=?, orientadorID=? WHERE id=?",
                projeto.getPropostaId(), projeto.getEstudanteId(), projeto.getOrientadorId(), projeto.getId());
    }


    @Override
    public int deleteById(Long id)
    {
        return jdbcTemplate.update("DELETE FROM projeto WHERE id=?", id);
    }

    public List<Projeto> findAllAnotherWay()
    {
        return jdbcTemplate.query("SELECT * from projeto", BeanPropertyRowMapper.newInstance(Projeto.class));
    }

    @Override
    public List<Projeto> findAll()
    {
        return jdbcTemplate.query(
                "select * from projeto",
                (rs, rowNum) ->
                        new Projeto(
                                rs.getLong("id"),
                                rs.getLong("propostaId"),
                                rs.getLong("estudanteId"),
                                rs.getLong("orientadorId")
                        )
        );
    }

    @Override
    public Optional<Projeto> findById(Long id)
    {
        try
        {
            Projeto proj = jdbcTemplate.queryForObject("SELECT * FROM projeto WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Projeto.class), id);
            return Optional.of(proj);
        } catch (IncorrectResultSizeDataAccessException e)
        {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

  /*
   EXEMPLO CONTAING

   @Override
    public List<Projeto> findByTituloContaining(String title)
    {
        String q = "SELECT * from projeto WHERE titulo LIKE '%" + title + "%' collate binary_ci";
        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Projeto.class));
    }*/

    @Override
    public int deleteAll()
    {
        return jdbcTemplate.update("DELETE from projeto");
    }
}
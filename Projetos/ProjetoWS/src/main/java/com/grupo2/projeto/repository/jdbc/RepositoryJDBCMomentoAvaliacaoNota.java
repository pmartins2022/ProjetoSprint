package com.grupo2.projeto.repository.jdbc;

import com.grupo2.projeto.model.MomentoAvaliacaoNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryJDBCMomentoAvaliacaoNota
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createAvaliacaoNota(MomentoAvaliacaoNota mom) throws IllegalAccessException
    {
        return GenericJDBCRepository.from(jdbcTemplate,mom).save();
    }
}

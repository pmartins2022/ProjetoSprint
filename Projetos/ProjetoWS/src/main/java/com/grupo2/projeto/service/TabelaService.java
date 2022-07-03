package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.EdicaoUCDTO;
import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.repository.jdbc.GenericJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TabelaService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public <T extends JDBCTable> void save(T dto)
    {
        try
        {
            GenericJDBCRepository.from(jdbcTemplate,dto).save();
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
}

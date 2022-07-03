package com.grupo2.projeto.model.factory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class SimpleJDBCCallFactory
{
    public SimpleJdbcCall create(JdbcTemplate jdbcTemplate)
    {
        return new SimpleJdbcCall(jdbcTemplate);
    }
}

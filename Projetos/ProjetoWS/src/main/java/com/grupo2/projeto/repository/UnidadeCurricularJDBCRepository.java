package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.UnidadeCurricularDTO;
import com.grupo2.projeto.model.factory.SimpleJDBCCallFactory;
import com.grupo2.projeto.repository.jdbc.GenericRepository;
import com.grupo2.projeto.repository.jdbc.reflection.ObjectMapper;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository
public class UnidadeCurricularJDBCRepository implements GenericRepository<UnidadeCurricularDTO>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SimpleJDBCCallFactory factory;

    @Override
    public List<UnidadeCurricularDTO> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate);

                jdbcCall.withFunctionName("FUNC_FIND_ALL_UNIDADECURRICULAR");

        return objectMapper.mapToObjectList(jdbcCall.execute(),UnidadeCurricularDTO.class);
    }

    @Override
    public UnidadeCurricularDTO findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate);
                jdbcCall.withFunctionName("FNC_FIND_UNIDADECURRICULAR_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.VARCHAR))
                .withReturnValue();
        return objectMapper.mapToObject(jdbcCall.execute(id),UnidadeCurricularDTO.class);
    }


    @Override
    public void insert(UnidadeCurricularDTO dto)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_UNIDADECURRICULAR")
                .declareParameters(
                        new SqlParameter("sigla",OracleTypes.VARCHAR),
                        new SqlParameter("denominacao",OracleTypes.VARCHAR));
        jdbcCall.execute(dto.getSigla(), dto.getDenominacao());
    }

    @Override
    public void update(UnidadeCurricularDTO dto)
    {

    }

    @Override
    public void deleteById(Long id)
    {

    }
}

package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.OrganizacaoDTO;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizacaoJDBCRepository implements GenericRepository<OrganizacaoDTO>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SimpleJDBCCallFactory factory;

    @Override
    public List<OrganizacaoDTO> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withFunctionName("FUNC_FIND_ALL_ORGANIZACAO");
        return objectMapper.mapToObjectList(jdbcCall.execute(),OrganizacaoDTO.class);
    }

    @Override
    public OrganizacaoDTO findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withFunctionName("FNC_FIND_ORGANIZACAO_ID")
                .declareParameters(new SqlParameter("idIn",OracleTypes.NUMBER))
                .withReturnValue();
        return objectMapper.mapToObject(jdbcCall.execute(id),OrganizacaoDTO.class);
    }


    @Override
    public void insert(OrganizacaoDTO dto)
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withProcedureName("PROC_INSERT_ORGANIZACAO")
                .declareParameters(
                        new SqlParameter("denominacao",OracleTypes.VARCHAR),
                        new SqlParameter("nif",OracleTypes.NUMBER));
        jdbcCall.execute(dto.getDenominacao(),dto.getNif());
    }

    @Override
    public void update(OrganizacaoDTO dto)
    {

    }

}
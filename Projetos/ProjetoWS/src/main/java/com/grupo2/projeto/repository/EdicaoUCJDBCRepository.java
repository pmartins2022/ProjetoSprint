package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.EdicaoUCDTO;
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
public class EdicaoUCJDBCRepository implements GenericRepository<EdicaoUCDTO>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EdicaoUCDTO> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("functionFindAll");
        return ObjectMapper.mapToObjectList(jdbcCall.execute(),EdicaoUCDTO.class);
    }

    @Override
    public EdicaoUCDTO findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_EDICAOUC_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(id),EdicaoUCDTO.class);
    }


    @Override
    public void insert(EdicaoUCDTO dto)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_EDICAOUC")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("ucCode",OracleTypes.VARCHAR),
                        new SqlParameter("anoLetivoCode",OracleTypes.VARCHAR),
                        new SqlParameter("rucID",OracleTypes.NUMBER));
        jdbcCall.execute(dto.getId(), dto.getUcCode(), dto.getAnoLetivoCode(), dto.getRucID());
    }

    @Override
    public void update(EdicaoUCDTO dto)
    {

    }

    @Override
    public void deleteById(Long id)
    {

    }

    public EdicaoUCDTO findByRucIDAndEdicaoUCActive(Long rucID) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_EDICAOUC_RUCID_ATIVA")
                .declareParameters(new SqlParameter("rucID", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(rucID),EdicaoUCDTO.class);
    }
}

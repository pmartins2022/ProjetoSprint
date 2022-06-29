package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.EdicaoUCDTO;
import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
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
public class MomentoAvaliacaoJDBCRepository implements GenericRepository<MomentoAvaliacaoDTO>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MomentoAvaliacaoDTO> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("functionFindAll");
        return ObjectMapper.mapToObjectList(jdbcCall.execute(),MomentoAvaliacaoDTO.class);
    }

    @Override
    public MomentoAvaliacaoDTO findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_MOMENTOAVALIACAO_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(id),MomentoAvaliacaoDTO.class);
    }


    @Override
    public void insert(MomentoAvaliacaoDTO dto)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_MOMENTOAVALIACAO")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("denominacao",OracleTypes.VARCHAR));
        jdbcCall.execute(dto.getId(), dto.getDenominacao());
    }

    @Override
    public void update(MomentoAvaliacaoDTO dto)
    {

    }

    @Override
    public void deleteById(Long id)
    {

    }
}

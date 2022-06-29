package com.grupo2.projeto.repository;


import com.grupo2.projeto.dto.MomentoAvaliacaoNotaDTO;
import com.grupo2.projeto.model.MomentoAvaliacaoNota;
import com.grupo2.projeto.model.Projeto;
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
public class MomentoAvaliacaoNotaJDBCRepository implements GenericRepository<MomentoAvaliacaoNota>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MomentoAvaliacaoNota> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("functionFindAll");
        return ObjectMapper.mapToObjectList(jdbcCall.execute(),MomentoAvaliacaoNota.class);
    }

    @Override
    public MomentoAvaliacaoNota findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_MOMENTOAVALIACAONOTA_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(id),MomentoAvaliacaoNota.class);
    }


    @Override
    public void insert(MomentoAvaliacaoNota model)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_MOMENTOAVALIACAONOTA")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("idAvaliacao",OracleTypes.NUMBER),
                        new SqlParameter("nota",OracleTypes.NUMBER),
                        new SqlParameter("justificacao",OracleTypes.VARCHAR),
                        new SqlParameter("estadoAvaliacao",OracleTypes.VARCHAR));
        jdbcCall.execute(model.getId(), model.getIdAvaliacao(), model.getNota(), model.getJustificacao(), model.getEstadoAvaliacao());
    }

    @Override
    public void update(MomentoAvaliacaoNota model)
    {

    }

    @Override
    public void deleteById(Long id)
    {

    }

    public MomentoAvaliacaoNota findByIdAvaliacao(Long idAvaliacao) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_MOMENTOAVALIACAONOTA_IDAVALIACAO")
                .declareParameters(new SqlParameter("idAvaliacao", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(idAvaliacao),MomentoAvaliacaoNota.class);
    }
}

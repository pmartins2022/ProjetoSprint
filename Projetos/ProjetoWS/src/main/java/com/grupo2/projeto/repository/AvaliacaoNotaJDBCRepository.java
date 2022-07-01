package com.grupo2.projeto.repository;


import com.grupo2.projeto.model.AvaliacaoNota;
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
public class AvaliacaoNotaJDBCRepository implements GenericRepository<AvaliacaoNota>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AvaliacaoNota> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("functionFindAll");
        return ObjectMapper.mapToObjectList(jdbcCall.execute(), AvaliacaoNota.class);
    }

    @Override
    public AvaliacaoNota findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_MOMENTOAVALIACAONOTA_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(id), AvaliacaoNota.class);
    }


    @Override
    public void insert(AvaliacaoNota model)
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
    public void update(AvaliacaoNota model)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_UPDATE_NOTA")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("nota",OracleTypes.NUMBER),
                        new SqlParameter("justificacao",OracleTypes.VARCHAR));
        jdbcCall.execute(model.getId(),model.getNota(),model.getJustificacao());

    }

    @Override
    public void deleteById(Long id)
    {

    }

    public AvaliacaoNota findByIdAvaliacao(Long idAvaliacao) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_MOMENTOAVALIACAONOTA_IDAVALIACAO")
                .declareParameters(new SqlParameter("idAvaliacao", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(idAvaliacao), AvaliacaoNota.class);
    }

    public List<AvaliacaoNota> findAllByEstado(String estado) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_MOMENTOAVALIACAONOTA_ESTADO")
                .declareParameters(new SqlParameter("estado", OracleTypes.VARCHAR))
                .withReturnValue();
        return ObjectMapper.mapToObjectList(jdbcCall.execute(estado), AvaliacaoNota.class);
    }

    public AvaliacaoNota findAvaliacaoNotaByAvaliacaoID(Long idAvaliacao) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_AVALIACAONOTA_AVALIACAOID")
                .declareParameters(new SqlParameter("idAvaliacao", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(idAvaliacao), AvaliacaoNota.class);
    }
}

package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.model.Avaliacao;
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
public class ProjetoJDBCRepository implements GenericRepository<Projeto>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Projeto> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FUNC_FIND_ALL_PROJETO");
        return objectMapper.mapToObjectList(jdbcCall.execute(),Projeto.class);
    }

    public List<Projeto> findAllFilter(String query, Object[] params)
    {
        return jdbcTemplate.queryForList(query,Projeto.class,params);
    }

    @Override
    public Projeto findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_PROJETO_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return objectMapper.mapToObject(jdbcCall.execute(id),Projeto.class);
    }


    @Override
    public void insert(Projeto model)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_PROJETO")
                .declareParameters(
                        new SqlParameter("propostaId",OracleTypes.NUMBER),
                        new SqlParameter("estudanteId",OracleTypes.NUMBER),
                        new SqlParameter("orientadorId",OracleTypes.NUMBER));
        jdbcCall.execute(model.getPropostaId(), model.getEstudanteId(), model.getOrientadorId());
    }

    @Override
    public void update(Projeto model)
    {

    }

    @Override
    public void deleteById(Long id)
    {

    }

    public Projeto findByPropostaId(Long propostaID) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_PROJETO_PROPOSTAID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return objectMapper.mapToObject(jdbcCall.execute(propostaID),Projeto.class);
    }

    public List<Projeto> findAllByOrientadorId(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_PROJETO_ORIENTADORID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return objectMapper.mapToObjectList(jdbcCall.execute(id),Projeto.class);
    }
}

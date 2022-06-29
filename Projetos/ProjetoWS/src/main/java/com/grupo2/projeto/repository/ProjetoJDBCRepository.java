package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
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

    @Override
    public List<Projeto> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("functionFindAll");
        return ObjectMapper.mapToObjectList(jdbcCall.execute(),Projeto.class);
    }

    @Override
    public Projeto findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_PROJETO_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(id),Projeto.class);
    }


    @Override
    public void insert(Projeto model)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_PROJETO")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("propostaId",OracleTypes.NUMBER),
                        new SqlParameter("estudanteId",OracleTypes.NUMBER),
                        new SqlParameter("orientadorId",OracleTypes.NUMBER));
        jdbcCall.execute(model.getId(), model.getPropostaId(), model.getEstudanteId(), model.getOrientadorId());
    }

    @Override
    public void update(Projeto model)
    {

    }

    @Override
    public void deleteById(Long id)
    {

    }
}

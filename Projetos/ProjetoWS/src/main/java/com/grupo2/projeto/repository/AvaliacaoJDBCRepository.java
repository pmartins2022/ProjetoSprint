package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.OrganizacaoDTO;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
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
public class AvaliacaoJDBCRepository implements GenericRepository<Avaliacao>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Avaliacao> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("functionFindAll");
        return ObjectMapper.mapToObjectList(jdbcCall.execute(),Avaliacao.class);
    }

    @Override
    public Avaliacao findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_AVALIACAO_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(id),Avaliacao.class);
    }


    @Override
    public void insert(Avaliacao dto)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_AVALIACAO")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("idMomentoAvaliacao",OracleTypes.NUMBER),
                        new SqlParameter("presidenteId",OracleTypes.NUMBER),
                        new SqlParameter("orientadorId",OracleTypes.NUMBER),
                        new SqlParameter("arguenteId",OracleTypes.NUMBER),
                        new SqlParameter("idProjeto",OracleTypes.NUMBER),
                        new SqlParameter("conteudo",OracleTypes.NUMBER));
        jdbcCall.execute(dto.getId(), dto.getIdMomentoAvaliacao(), dto.getPresidenteId(), dto.getOrientadorId(),
                dto.getArguenteId(), dto.getIdProjeto(), dto.getConteudo());
    }

    public void insert(Avaliacao dto, Conteudo conteudo)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_AVALIACAO")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("idMomentoAvaliacao",OracleTypes.NUMBER),
                        new SqlParameter("presidenteId",OracleTypes.NUMBER),
                        new SqlParameter("orientadorId",OracleTypes.NUMBER),
                        new SqlParameter("arguenteId",OracleTypes.NUMBER),
                        new SqlParameter("idProjeto",OracleTypes.NUMBER),
                        new SqlParameter("conteudo",OracleTypes.NUMBER));
        jdbcCall.execute(dto.getId(), dto.getIdMomentoAvaliacao(), dto.getPresidenteId(), dto.getOrientadorId(),
                dto.getArguenteId(), dto.getIdProjeto(), conteudo.getId());
    }

    @Override
    public void update(Avaliacao dto)
    {

    }

    @Override
    public void deleteById(Long id)
    {

    }
    public Avaliacao findAllByProjetoId(Long projetoID) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FUNC_FINDALL_AVALIACAO_PROJETOID")
                .declareParameters(new SqlParameter("projetoID", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(projetoID),Avaliacao.class);
    }

    public List <Avaliacao> findByPresidenteId(Long presidenteId) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FUNC_FIND_AVALIACAO_PRESIDENTEID")
                .declareParameters(new SqlParameter("presidenteID", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObjectList(jdbcCall.execute(presidenteId),Avaliacao.class);
    }

}

package com.grupo2.projeto.repository;

import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.jdbc.GenericRepository;
import com.grupo2.projeto.repository.jdbc.reflection.ObjectMapper;
import javassist.Loader;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository
public class ConteudoJDBCRepository implements GenericRepository<Conteudo>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Conteudo> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FUNC_FIND_ALL_CONTEUDO");
        return ObjectMapper.mapToObjectList(jdbcCall.execute(),Conteudo.class);
    }

    @Override
    public Conteudo findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_CONTEUDO_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(id),Conteudo.class);
    }


    @Override
    public void insert(Conteudo model)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_CONTEUDO")
                .declareParameters(
                        new SqlParameter("idProjeto",OracleTypes.NUMBER),
                        new SqlParameter("titulo",OracleTypes.VARCHAR),
                        new SqlParameter("caminhoDocumento",OracleTypes.VARCHAR),
                        new SqlParameter("documento",OracleTypes.VARCHAR),
                        new SqlParameter("linguagemDocumento",OracleTypes.VARCHAR),
                        new SqlParameter("estadoConteudo",OracleTypes.VARCHAR));
        jdbcCall.execute(model.getIdProjeto(), model.getTitulo(), model.getCaminhoDocumento(), model.getDocumento(),
                model.getLinguagemDocumento(), model.getEstadoConteudo().name());
    }

    @Override
    public void update(Conteudo dto)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_UPDATE_CONTEUDO")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("estadoConteudo",OracleTypes.VARCHAR));

        jdbcCall.execute(dto.getId(),dto.getEstadoConteudo().name());
    }

    @Override
    public void deleteById(Long id)
    {
        /*SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_DELETE_CONTEUDO")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER));

        jdbcCall.execute(id);*/
    }

    public List<Conteudo> findAllByIdProjeto(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_CONTEUDO_FINDALL_IDPROJETO")
                .declareParameters(new SqlParameter("idIn",OracleTypes.NUMBER));

        return ObjectMapper.mapToObjectList(jdbcCall.execute(id),Conteudo.class);
    }
}

package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.PropostaDTO;
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
public class PropostaJDBCRepository implements GenericRepository<PropostaDTO>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PropostaDTO> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("functionFindAll");
        return ObjectMapper.mapToObjectList(jdbcCall.execute(),PropostaDTO.class);
    }

    @Override
    public PropostaDTO findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FIND_PROPOSTA_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObject(jdbcCall.execute(id),PropostaDTO.class);
    }


    @Override
    public void insert(PropostaDTO dto)
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("PROC_INSERT_PROPOSTA")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("utilizadorId",OracleTypes.NUMBER),
                        new SqlParameter("organizacaoId",OracleTypes.NUMBER),
                        new SqlParameter("titulo",OracleTypes.VARCHAR),
                        new SqlParameter("problema",OracleTypes.VARCHAR),
                        new SqlParameter("objetivo",OracleTypes.VARCHAR),
                        new SqlParameter("edicaoUCId",OracleTypes.NUMBER),
                        new SqlParameter("estadoAtual",OracleTypes.VARCHAR));
        jdbcCall.execute(dto.getId(), dto.getUtilizadorId(), dto.getOrganizacaoId(), dto.getTitulo(), dto.getProblema(),
                dto.getObjetivo(), dto.getEdicaoUCId(), dto.getEstadoAtual());
    }

    @Override
    public void update(PropostaDTO dto)
    {

    }

    @Override
    public void deleteById(Long id)
    {

    }

    public List<PropostaDTO> findAllByEdicaoUCID(Long edicaoUCID) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FNC_FINDALL_PROPOSTA_EDICAOID")
                .declareParameters(new SqlParameter("EDICAOUCID", OracleTypes.NUMBER))
                .withReturnValue();
        return ObjectMapper.mapToObjectList(jdbcCall.execute(edicaoUCID),PropostaDTO.class);
    }
}

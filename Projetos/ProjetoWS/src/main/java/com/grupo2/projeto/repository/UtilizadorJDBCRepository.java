package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.OrganizacaoDTO;
import com.grupo2.projeto.dto.UtilizadorDTO;
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
public class UtilizadorJDBCRepository implements GenericRepository<UtilizadorDTO>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SimpleJDBCCallFactory factory;

    @Override
    public List<UtilizadorDTO> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate);

        jdbcCall.withFunctionName("FUNC_FIND_ALL_UTILIZADOR");

        return objectMapper.mapToObjectList(jdbcCall.execute(),UtilizadorDTO.class);
    }

    @Override
    public UtilizadorDTO findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate);

                jdbcCall.withFunctionName("FNC_FIND_UTILIZADOR_ID")
                .declareParameters(new SqlParameter("idIn",OracleTypes.NUMBER))
              .withReturnValue();

        return objectMapper.mapToObject(jdbcCall.execute(id),UtilizadorDTO.class);
    }

    @Override
    public void insert(UtilizadorDTO dto)
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate);

        jdbcCall.withProcedureName("PROC_INSERT_UTILIZADOR")
                .declareParameters(
                        new SqlParameter("id",OracleTypes.NUMBER),
                        new SqlParameter("nome",OracleTypes.VARCHAR),
                        new SqlParameter("sobrenome",OracleTypes.VARCHAR),
                        new SqlParameter("email",OracleTypes.VARCHAR),
                        new SqlParameter("tipoUtilizador",OracleTypes.VARCHAR));
        jdbcCall.execute(dto.getId(), dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getTipoUtilizador().name());
    }

    @Override
    public void update(UtilizadorDTO dto)
    {

    }
}
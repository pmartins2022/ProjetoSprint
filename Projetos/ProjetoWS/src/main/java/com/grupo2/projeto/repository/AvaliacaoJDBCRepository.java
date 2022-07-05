package com.grupo2.projeto.repository;

import com.grupo2.projeto.model.Avaliacao;
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
import java.time.LocalDate;
import java.util.List;

@Repository
public class AvaliacaoJDBCRepository implements GenericRepository<Avaliacao>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SimpleJDBCCallFactory factory;

    @Override
    public List<Avaliacao> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withFunctionName("FUNC_FIND_ALL_AVALIACAO");
        return objectMapper.mapToObjectList(jdbcCall.execute(),Avaliacao.class);
    }

    @Override
    public Avaliacao findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withFunctionName("FNC_FIND_AVALIACAO_ID")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return objectMapper.mapToObject(jdbcCall.execute(id),Avaliacao.class);
    }

    @Override
    public void insert(Avaliacao avaliacao)
    {//(IDMOMENTOAVALIACAO, PRESIDENTEID, ORIENTADORID, ARGUENTEID, IDPROJETO, CONTEUDO, ESTADOAVALIACAO, DATAAVALIACAO)
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withProcedureName("PROC_INSERT_AVALIACAO")
                .declareParameters(
                        new SqlParameter("idMomentoAvaliacao", OracleTypes.NUMBER),
                        new SqlParameter("orientadorId", OracleTypes.NUMBER),
                        new SqlParameter("presidenteId", OracleTypes.NUMBER),
                        new SqlParameter("arguenteId", OracleTypes.NUMBER),
                        new SqlParameter("idProjeto", OracleTypes.NUMBER),
                        new SqlParameter("conteudo", OracleTypes.NUMBER),
                        new SqlParameter("estadoAvaliacao", OracleTypes.VARCHAR),
                        new SqlParameter("dataAvaliacao", OracleTypes.DATE));

        System.out.println(avaliacao.getDateString());

        jdbcCall.execute(avaliacao.getIdMomentoAvaliacao(), avaliacao.getOrientadorId(), avaliacao.getPresidenteId(),
                avaliacao.getArguenteId(), avaliacao.getIdProjeto(), avaliacao.getConteudo(),
                avaliacao.getNameEstadoAvaliacao(), LocalDate.parse(avaliacao.getDateString(), avaliacao.formatter()));
    }

    @Override
    public void update(Avaliacao dto)
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withProcedureName("PROC_UPDATE_AVALIACAO")
                .declareParameters(
                        new SqlParameter("idIn", OracleTypes.NUMBER),
                        new SqlParameter("estado", OracleTypes.VARCHAR));

        jdbcCall.execute(dto.getId(), dto.getEstadoAvaliacao());
    }

    public Avaliacao findAllByProjetoId(Long projetoID) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withFunctionName("FUNC_FINDALL_AVALIACAO_PROJETOID")
                .declareParameters(new SqlParameter("projetoID", OracleTypes.NUMBER))
                .withReturnValue();
        return objectMapper.mapToObject(jdbcCall.execute(projetoID),Avaliacao.class);
    }

    public List <Avaliacao> findByPresidenteId(Long presidenteId) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withFunctionName("FUNC_FIND_AVALIACAO_PRESIDENTEID")
                .declareParameters(new SqlParameter("presidenteID", OracleTypes.NUMBER))
                .withReturnValue();
        return objectMapper.mapToObjectList(jdbcCall.execute(presidenteId),Avaliacao.class);
    }

    public List<Avaliacao> findAllEditableAvaliacao(Long idPresidente) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        SimpleJdbcCall jdbcCall = factory.create(jdbcTemplate)
                .withFunctionName("FUNC_FIND_AVALIACAO_EDITABLE")
                .declareParameters(new SqlParameter("idIn", OracleTypes.NUMBER))
                .withReturnValue();
        return objectMapper.mapToObjectList(jdbcCall.execute(idPresidente),Avaliacao.class);
    }
}

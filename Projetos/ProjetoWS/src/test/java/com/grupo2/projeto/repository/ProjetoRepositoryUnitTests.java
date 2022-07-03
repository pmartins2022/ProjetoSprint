package com.grupo2.projeto.repository;

/*import com.grupo2.projeto.dataModel.jpa.ProjetoJPA;
import com.grupo2.projeto.dataModel.jpa.mapper.ProjetoJPAMapper;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.jpa.ProjetoJPARepository;*/
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.PropostaDTO;
import com.grupo2.projeto.dto.PropostaEstado;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.model.factory.SimpleJDBCCallFactory;
import com.grupo2.projeto.repository.jdbc.reflection.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjetoRepositoryUnitTests
{
    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private SimpleJDBCCallFactory factory;

    @InjectMocks
    private ProjetoJDBCRepository repository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Projeto dto = mock(Projeto.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);

        when(objectMapper.mapToObjectList(call.execute(), Projeto.class)).thenReturn(List.of(dto,dto));

        List<Projeto> result = repository.findAll();

        assertEquals(2,result.size());
    }

    @Test
    public void shouldFindAllFilter() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        String query = "";
        Object[] params = new Object[1];

        Projeto dto = mock(Projeto.class);

        when(jdbcTemplate.queryForList(query, Projeto.class, params)).thenReturn(List.of(dto, dto));

        List<Projeto> result = repository.findAllFilter(query, params);

        assertEquals(2,result.size());
    }

    @Test
    public void shouldFindById() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Projeto dto = mock(Projeto.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObject(call.execute(1L),Projeto.class)).thenReturn(dto);

        Projeto result = repository.findById(1L);

        assertEquals(dto,result);
    }

    @Test
    public void shouldInsert() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Projeto dto = mock(Projeto.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withProcedureName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);

        assertDoesNotThrow(()->repository.insert(dto));
    }

    @Test
    public void shouldFindAllByPropostaID() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Projeto dto = mock(Projeto.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObject(call.execute(),Projeto.class)).thenReturn(dto);

        Projeto result = repository.findByPropostaId(1L);

        assertEquals(dto, result);
    }

    @Test
    public void shouldFindAllByOrientadorID() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Projeto dto = mock(Projeto.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObjectList(call.execute(),Projeto.class)).thenReturn(List.of(dto,dto));

        List<Projeto> result = repository.findAllByOrientadorId(1L);

        assertEquals(2, result.size());
    }
}
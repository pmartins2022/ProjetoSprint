package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.UtilizadorDTO;
import com.grupo2.projeto.model.TipoUtilizador;
import com.grupo2.projeto.model.factory.SimpleJDBCCallFactory;
import com.grupo2.projeto.repository.jdbc.reflection.ObjectMapper;
import oracle.jdbc.OracleTypes;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UtilizadorJDBCRepositoryUnitTests
{
    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private SimpleJDBCCallFactory factory;

    @InjectMocks
    private UtilizadorJDBCRepository repository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        UtilizadorDTO dto = mock(UtilizadorDTO.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);

        when(objectMapper.mapToObjectList(call.execute(),UtilizadorDTO.class)).thenReturn(List.of(dto,dto));

        List<UtilizadorDTO> result = repository.findAll();

        assertEquals(2,result.size());
    }

    @Test
    public void shouldFindById() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        UtilizadorDTO dto = mock(UtilizadorDTO.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObject(call.execute(1L),UtilizadorDTO.class)).thenReturn(dto);

        UtilizadorDTO result = repository.findById(1L);

        assertEquals(dto,result);
    }

    @Test
    public void shouldInsert() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        UtilizadorDTO dto = mock(UtilizadorDTO.class);

        when(dto.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withProcedureName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);

        assertDoesNotThrow(()->repository.insert(dto));
    }
}
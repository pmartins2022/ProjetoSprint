package com.grupo2.projeto.repository;

/*import com.grupo2.projeto.dataModel.jpa.AvaliacaoJPA;
import com.grupo2.projeto.dataModel.jpa.ConteudoJPA;
import com.grupo2.projeto.dataModel.jpa.factory.AvaliacaoJPAFactory;
import com.grupo2.projeto.dataModel.jpa.mapper.AvaliacaoJPAMapper;
import com.grupo2.projeto.dataModel.jpa.mapper.ConteudoJPAMapper;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.jpa.AvaliacaoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;*/
import com.grupo2.projeto.model.*;
import com.grupo2.projeto.model.factory.SimpleJDBCCallFactory;
import com.grupo2.projeto.repository.jdbc.reflection.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AvaliacaoRepositoryUnitTests
{
    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private SimpleJDBCCallFactory factory;

    @InjectMocks
    private AvaliacaoJDBCRepository repository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Avaliacao dto = mock(Avaliacao.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);

        when(objectMapper.mapToObjectList(call.execute(), Avaliacao.class)).thenReturn(List.of(dto,dto));

        List<Avaliacao> result = repository.findAll();

        assertEquals(2,result.size());
    }


    @Test
    public void shouldFindById() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Avaliacao dto = mock(Avaliacao.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObject(call.execute(1L),Avaliacao.class)).thenReturn(dto);

        Avaliacao result = repository.findById(1L);

        assertEquals(dto,result);
    }

    @Test
    public void shouldFindByPresidenteId() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Avaliacao dto = mock(Avaliacao.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObjectList(call.execute(1L),Avaliacao.class)).thenReturn(List.of(dto, dto));

        List<Avaliacao> result = repository.findByPresidenteId(1L);

        assertEquals(2,result.size());
    }

    @Test
    public void shouldFindByProjetoId() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Avaliacao dto = mock(Avaliacao.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObject(call.execute(1L),Avaliacao.class)).thenReturn(dto);

        Avaliacao result = repository.findAllByProjetoId(1L);

        assertEquals(dto, result);
    }

    @Test
    public void shouldInsert() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Avaliacao dto = mock(Avaliacao.class);
        when(dto.getEstadoAvaliacao()).thenReturn(EstadoAvaliacao.REVISAO);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withProcedureName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);

        assertDoesNotThrow(()->repository.insert(dto));
    }


    @Test
    public void shouldFindAllEditableAvaliacao() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Avaliacao dto = mock(Avaliacao.class);

        when(jdbcTemplate.queryForList(anyString(), eq(Avaliacao.class), eq(1L))).thenReturn(List.of(dto,dto));

        List<Avaliacao> result = repository.findAllEditableAvaliacao(1L);

        assertEquals(2,result.size());
    }
}
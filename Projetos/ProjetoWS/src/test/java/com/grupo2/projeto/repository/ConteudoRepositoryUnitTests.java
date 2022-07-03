package com.grupo2.projeto.repository;

/*import com.grupo2.projeto.dataModel.jpa.ConteudoJPA;
import com.grupo2.projeto.dataModel.jpa.mapper.ConteudoJPAMapper;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.jpa.ConteudoJPARepository;*/
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.EstadoConteudo;
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
import static org.mockito.Mockito.*;

@SpringBootTest
class ConteudoRepositoryUnitTests
{

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private SimpleJDBCCallFactory factory;

    @InjectMocks
    private ConteudoJDBCRepository repository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Conteudo dto = mock(Conteudo.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);

        when(objectMapper.mapToObjectList(call.execute(), Conteudo.class)).thenReturn(List.of(dto,dto));

        List<Conteudo> result = repository.findAll();

        assertEquals(2,result.size());
    }


    @Test
    public void shouldFindById() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Conteudo dto = mock(Conteudo.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObject(call.execute(1L),Conteudo.class)).thenReturn(dto);

        Conteudo result = repository.findById(1L);

        assertEquals(dto,result);
    }

    @Test
    public void shouldFindByProjetoId() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Conteudo dto = mock(Conteudo.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObjectList(call.execute(1L),Conteudo.class)).thenReturn(List.of(dto, dto));

        List<Conteudo> result = repository.findAllByIdProjeto(1L);

        assertEquals(2,result.size());
    }

    @Test
    public void shouldInsert() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Conteudo dto = mock(Conteudo.class);
        when(dto.getEstadoConteudo()).thenReturn(EstadoConteudo.APROVADO);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withProcedureName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);

        assertDoesNotThrow(()->repository.insert(dto));
    }

    @Test
    public void shouldUpdate() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Conteudo dto = mock(Conteudo.class);
        when(dto.getEstadoConteudo()).thenReturn(EstadoConteudo.APROVADO);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withProcedureName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);

        assertDoesNotThrow(()->repository.update(dto));
    }

}
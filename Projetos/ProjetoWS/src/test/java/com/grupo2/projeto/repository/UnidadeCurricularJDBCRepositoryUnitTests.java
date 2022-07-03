package com.grupo2.projeto.repository;

import com.grupo2.projeto.dto.EdicaoUCDTO;
import com.grupo2.projeto.dto.UnidadeCurricularDTO;
import com.grupo2.projeto.dto.UtilizadorDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UnidadeCurricularJDBCRepositoryUnitTests
{
    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private SimpleJDBCCallFactory factory;

    @InjectMocks
    private UnidadeCurricularJDBCRepository repository;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        UnidadeCurricularDTO dto = mock(UnidadeCurricularDTO.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);

        when(objectMapper.mapToObjectList(call.execute(), UnidadeCurricularDTO.class)).thenReturn(List.of(dto,dto));

        List<UnidadeCurricularDTO> result = repository.findAll();

        assertEquals(2,result.size());
    }

    @Test
    public void shouldFindById() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        UnidadeCurricularDTO dto = mock(UnidadeCurricularDTO.class);

        SimpleJdbcCall call = mock(SimpleJdbcCall.class);

        when(factory.create(any(JdbcTemplate.class))).thenReturn(call);

        when(call.withFunctionName(anyString())).thenReturn(call);
        when(call.declareParameters(any())).thenReturn(call);
        when(call.withReturnValue()).thenReturn(call);

        when(objectMapper.mapToObject(call.execute(1L),UnidadeCurricularDTO.class)).thenReturn(dto);

        UnidadeCurricularDTO result = repository.findById(1L);

        assertEquals(dto,result);
    }
}
package com.example.javafx.controller;

import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.service.UnidadeCurricularService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UnidadeCurricularControllerUnitTests
{
    @MockBean
    private UnidadeCurricularService service;

    @InjectMocks
    private UnidadeCurricularController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateUnidadeCurricular()
    {
        UnidadeCurricularDTO dto = mock(UnidadeCurricularDTO.class);

        when(service.createUnidadeCurricular(dto)).thenReturn(dto);

        UnidadeCurricularDTO curricular = controller.createUnidadeCurricular(dto);

        assertEquals(dto, curricular);
    }

    @Test
    public void shouldFindAllUnidadeCurricular()
    {
        when(service.findAll()).thenReturn(List.of(mock(UnidadeCurricularDTO.class)));

        List<String> list = controller.findAllUnidadeCurricular();

        assertEquals(1, list.size());
    }

    @Test
    public void shouldNotFindAllUnidadeCurricular()
    {
        when(service.findAll()).thenReturn(List.of());
        List<String> list = controller.findAllUnidadeCurricular();

        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldGetFromLista()
    {
        when(service.findAll()).thenReturn(List.of(mock(UnidadeCurricularDTO.class)));

        controller.findAllUnidadeCurricular();

        UnidadeCurricularDTO lista = controller.getFromLista(0);

        assertNotNull(lista);
    }

    @Test
    public void shouldNotGetFromLista()
    {
        assertThrows(IndexOutOfBoundsException.class,()->controller.getFromLista(50));
    }
}
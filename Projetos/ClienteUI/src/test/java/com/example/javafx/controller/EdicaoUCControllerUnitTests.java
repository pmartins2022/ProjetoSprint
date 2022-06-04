package com.example.javafx.controller;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.service.EdicaoUCService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class EdicaoUCControllerUnitTests
{
    @MockBean
    EdicaoUCService service;

    @InjectMocks
    EdicaoUCController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateEdicaoUC()
    {
        UnidadeCurricularDTO dto = mock(UnidadeCurricularDTO.class);
        AnoLetivoDTO letivoDTO = mock(AnoLetivoDTO.class);

        EdicaoUCDTO edicaoUCDTO = mock(EdicaoUCDTO.class);

        List<UnidadeCurricularDTO> listaUC = List.of(dto);
        List<AnoLetivoDTO> listaAL = List.of(letivoDTO);

        when(service.findAllUC()).thenReturn(listaUC);
        when(service.findAllAnoLetivo()).thenReturn(listaAL);

        controller.findAllAnoLetivo();
        controller.findAllUC();

        when(service.createAndSave(dto, letivoDTO)).thenReturn(edicaoUCDTO);

        EdicaoUCDTO uc = controller.createEdicaoUC(0, 0);

        assertEquals(uc, edicaoUCDTO);
    }

    @Test
    public void shouldNotCreateEdicaoUC_invalidID()
    {
        assertThrows(IndexOutOfBoundsException.class,()->controller.createEdicaoUC(99, 99));
    }

    @Test
    public void shouldFindAllUC()
    {
        List<UnidadeCurricularDTO> listaUC = List.of(mock(UnidadeCurricularDTO.class));
        when(service.findAllUC()).thenReturn(listaUC);
        List<String> uc = controller.findAllUC();
        assertEquals(1, uc.size());
    }

    @Test
    public void shouldNotFindAllUC()
    {
        when(service.findAllUC()).thenReturn(List.of());
        List<String> uc = controller.findAllUC();
        assertEquals(0, uc.size());
    }

    @Test
    public void shouldFindAllAL()
    {
        List<AnoLetivoDTO> listaUC = List.of(mock(AnoLetivoDTO.class));
        when(service.findAllAnoLetivo()).thenReturn(listaUC);
        List<String> uc = controller.findAllAnoLetivo();
        assertEquals(1, uc.size());
    }

    @Test
    public void shouldNotFindAllAL()
    {
        when(service.findAllUC()).thenReturn(List.of());
        List<String> uc = controller.findAllAnoLetivo();
        assertEquals(0, uc.size());
    }
}
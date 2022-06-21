package com.example.javafx.service;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.dto.factory.EdicaoUCDTOFactory;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.AnoLetivoRestRepo;
import com.example.javafx.repository.rest.EdicaoUCRestRepo;
import com.example.javafx.repository.rest.UnidadeCurricularRestRepo;
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
class EdicaoUCServiceUnitTests
{
    @MockBean
    AnoLetivoRestRepo restRepositoryAnoLetivo;

    @MockBean
    UnidadeCurricularRestRepo restRepositoryUC;
    @MockBean
    EdicaoUCRestRepo restRepositoryEdicaoUC;
    @MockBean
    EdicaoUCDTOFactory factory;

    @InjectMocks
    EdicaoUCService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAllAnoLetivo()
    {
        AnoLetivoDTO anoLetivoMOCK = mock(AnoLetivoDTO.class);
        List<AnoLetivoDTO> list = List.of(anoLetivoMOCK, anoLetivoMOCK);

        when(restRepositoryAnoLetivo.findAll()).thenReturn(list);

        List<AnoLetivoDTO> allAnoLetivo = service.findAllAnoLetivo();

        assertEquals(allAnoLetivo, list);
    }

    @Test
    public void shouldNotFindAllAnoLetivo()
    {
        when(restRepositoryAnoLetivo.findAll()).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, ()-> service.findAllAnoLetivo());
    }

    @Test
    public void shouldFindAlLUC()
    {
        UnidadeCurricularDTO ucMOCK = mock(UnidadeCurricularDTO.class);
        List<UnidadeCurricularDTO> list = List.of(ucMOCK, ucMOCK);

        when(restRepositoryUC.findAll()).thenReturn(list);

        List<UnidadeCurricularDTO> allUC = service.findAllUC();

        assertEquals(allUC, list);
    }

    @Test
    public void shouldNotFindAllUC()
    {
        when(restRepositoryUC.findAll()).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, ()-> service.findAllUC());
    }

    @Test
    public void shouldCreateEdicaoUC()
    {
        AnoLetivoDTO anoLetivoMOCK = mock(AnoLetivoDTO.class);
        UnidadeCurricularDTO ucMOCK = mock(UnidadeCurricularDTO.class);
        EdicaoUCDTO edicaoUCMOCK = mock(EdicaoUCDTO.class);

        when(factory.createEdicaoUCDTO(ucMOCK.getSigla(), anoLetivoMOCK.getSigla(),1L)).thenReturn(edicaoUCMOCK);
        when(restRepositoryEdicaoUC.createEdicaoUC(edicaoUCMOCK)).thenReturn(edicaoUCMOCK);

        EdicaoUCDTO saved = service.createAndSave(ucMOCK, anoLetivoMOCK,1L);

        assertEquals(saved, edicaoUCMOCK);
    }

    @Test
    public void shouldNotCreateEdicaoUC_Invalid()
    {
        AnoLetivoDTO anoLetivoMOCK = mock(AnoLetivoDTO.class);
        UnidadeCurricularDTO ucMOCK = mock(UnidadeCurricularDTO.class);
        EdicaoUCDTO edicaoUCMOCK = mock(EdicaoUCDTO.class);

        when(factory.createEdicaoUCDTO(ucMOCK.getSigla(), anoLetivoMOCK.getSigla(),1L)).thenReturn(edicaoUCMOCK);
        when(restRepositoryEdicaoUC.createEdicaoUC(edicaoUCMOCK)).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, () -> service.createAndSave(ucMOCK, anoLetivoMOCK,1L));
    }
}
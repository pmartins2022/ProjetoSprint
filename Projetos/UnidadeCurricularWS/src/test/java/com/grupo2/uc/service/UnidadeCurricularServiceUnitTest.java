package com.grupo2.uc.service;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.dto.mapper.UnidadeCurricularDTOMapper;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.model.factory.UnidadeCurricularFactory;
import com.grupo2.uc.repository.UnidadeCurricularRepository;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UnidadeCurricularServiceUnitTest {

    @MockBean
    UnidadeCurricularRepository repository;

    @MockBean
    UnidadeCurricularDTOMapper mapper;

    @MockBean
    UnidadeCurricularFactory factory;

    @InjectMocks
    UnidadeCurricularService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidUnidadeCurricular()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);
        UnidadeCurricular unidadeCurricularMOCK = mock(UnidadeCurricular.class);

        when(mapper.toModel(unidadeCurricularDTOMOCK)).thenReturn(unidadeCurricularMOCK);
        when(repository.saveUnidadeCurricular(unidadeCurricularMOCK)).thenReturn(unidadeCurricularMOCK);
        when(mapper.toDTO(unidadeCurricularMOCK)).thenReturn(unidadeCurricularDTOMOCK);

        when(unidadeCurricularDTOMOCK.getSigla()).thenReturn("MAT");
        when(unidadeCurricularDTOMOCK.getDenominacao()).thenReturn("Matematica");

        UnidadeCurricularDTO saved = service.createAndSaveUnidadeCurricular(unidadeCurricularDTOMOCK);

        assertEquals(unidadeCurricularDTOMOCK, saved);
    }
    @Test
    public void shouldFindAllUnidadeCurricularBySigla()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);
        UnidadeCurricular unidadeCurricularMOCK = mock(UnidadeCurricular.class);
        when(repository.findBySigla("MAT")).thenReturn(Optional.of(unidadeCurricularMOCK));
        when(mapper.toDTO(unidadeCurricularMOCK)).thenReturn(unidadeCurricularDTOMOCK);

        Optional<UnidadeCurricularDTO> saved = service.findBySigla("MAT");

        assertEquals(unidadeCurricularDTOMOCK, saved.get());
    }

    @Test
    public void shouldFindAllUnidadeCurricular()
    {
        UnidadeCurricular mockUnidadeCurricular = mock(UnidadeCurricular.class);
        UnidadeCurricularDTO mockUnidadeCurricularDTO = mock(UnidadeCurricularDTO.class);

        List<UnidadeCurricular> mockList = List.of(mockUnidadeCurricular,mockUnidadeCurricular,mockUnidadeCurricular);
        List<UnidadeCurricularDTO> mockListDTO = List.of(mockUnidadeCurricularDTO,mockUnidadeCurricularDTO,mockUnidadeCurricularDTO);

        when(repository.findAll()).thenReturn(mockList);

        when(mapper.toDTO(mockUnidadeCurricular)).thenReturn(mockUnidadeCurricularDTO);

        List<UnidadeCurricularDTO> all = service.findAll();

        assertEquals(all,mockListDTO);
    }
    @Test
    public void shouldUpdateUnidadeCurricular()
    {
        UnidadeCurricular mockUnidadeCurricular = mock(UnidadeCurricular.class);
        UnidadeCurricularDTO mockUnidadeCurricularDTO = mock(UnidadeCurricularDTO.class);

        when(repository.findBySigla("PTA")).thenReturn(Optional.of(mockUnidadeCurricular));
        when(factory.createUnidadeCurricular("PTA","Portugues Avancado")).thenReturn(mockUnidadeCurricular);

        when(repository.updateUnidadeCurricular(mockUnidadeCurricular)).thenReturn(mockUnidadeCurricular);

        when(mapper.toDTO(mockUnidadeCurricular)).thenReturn(mockUnidadeCurricularDTO);

        Optional<UnidadeCurricularDTO> optional = service.update("PTA", "Portugues Avancado");

        assertEquals(optional.get(),mockUnidadeCurricularDTO);
    }

}
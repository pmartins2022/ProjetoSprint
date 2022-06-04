package com.example.javafx.service;

import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.EdicaoUCRestRepo;
import com.example.javafx.repository.rest.OrganizacaoRestRepo;
import com.example.javafx.repository.rest.PropostaRestRepo;
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
class UnidadeCurricularServiceUnitTests
{
    @MockBean
    UnidadeCurricularRestRepo restRepo;

    @InjectMocks
    UnidadeCurricularService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldFindAll()
    {
        UnidadeCurricularDTO ucMOCK = mock(UnidadeCurricularDTO.class);
        List<UnidadeCurricularDTO> list = List.of(ucMOCK, ucMOCK);

        when(restRepo.findAll()).thenReturn(list);

        List<UnidadeCurricularDTO> dtoList = service.findAll();

        assertEquals(dtoList, list);
    }

    @Test
    public void shouldNotFindAll_Empty()
    {
        when(restRepo.findAll()).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, ()-> service.findAll());
    }

    @Test
    public void shouldCreateUnidadeCurricular()
    {
        UnidadeCurricularDTO ucMOCK = mock(UnidadeCurricularDTO.class);

        when(restRepo.createUnidadeCurricular(ucMOCK)).thenReturn(ucMOCK);

        UnidadeCurricularDTO unidadeCurricular = service.createUnidadeCurricular(ucMOCK);

        assertEquals(unidadeCurricular, ucMOCK);
    }

    @Test
    public void shouldNotCreateUnidadeCurricular_Invalid()
    {
        UnidadeCurricularDTO ucMOCK = mock(UnidadeCurricularDTO.class);

        when(restRepo.createUnidadeCurricular(ucMOCK)).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, () -> service.createUnidadeCurricular(ucMOCK));
    }

}
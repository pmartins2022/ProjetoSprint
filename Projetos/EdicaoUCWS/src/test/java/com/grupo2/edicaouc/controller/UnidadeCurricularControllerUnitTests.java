package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.service.UnidadeCurricularService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
@Transactional
class UnidadeCurricularControllerUnitTest
{
    @MockBean
    UnidadeCurricularService service;

    @InjectMocks
    UnidadeCurricularController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindSigla_Exists()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);

        when(service.findBySigla("sigla")).thenReturn(Optional.of(unidadeCurricularDTOMOCK));

        ResponseEntity<Object> responseEntity = controller.findByID("sigla");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindSigla()
    {
        when(service.findBySigla("sigla")).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class, () -> controller.findByID("sigla"));
    }

    @Test
    public void shouldCreateValidUnidadeCurricular()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);

        when(service.createAndSaveUnidadeCurricular(unidadeCurricularDTOMOCK)).thenReturn(unidadeCurricularDTOMOCK);

        ResponseEntity<UnidadeCurricularDTO> responseEntity = controller.createAndSaveUnidadeCurricular(unidadeCurricularDTOMOCK);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateValidUnidadeCurricular_ValidacaoInvalida()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);

        when(service.createAndSaveUnidadeCurricular(unidadeCurricularDTOMOCK)).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> controller.createAndSaveUnidadeCurricular(unidadeCurricularDTOMOCK));
    }

    @Test
    public void shouldNotCreateValidUnidadeCurricular_ErroGeral()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);

        when(service.createAndSaveUnidadeCurricular(unidadeCurricularDTOMOCK)).thenThrow(ErroGeralException.class);

        assertThrows(ErroGeralException.class, () -> controller.createAndSaveUnidadeCurricular(unidadeCurricularDTOMOCK));
    }

    @Test
    public void shouldUpdateDenominacao()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);

        when(service.update("sigla", "denominacao")).thenReturn(Optional.of(unidadeCurricularDTOMOCK));

        ResponseEntity<Object> responseEntity = controller.updateDenominacao("sigla", "denominacao");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotUpdateDenominacao()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);

        when(service.update("sigla", "denominacao")).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class, () -> controller.updateDenominacao("sigla", "denominacao"));
    }

    @Test
    public void shouldFindAll()
    {
        UnidadeCurricularDTO unidadeCurricularDTOMOCK = mock(UnidadeCurricularDTO.class);

        List<UnidadeCurricularDTO> list = List.of(unidadeCurricularDTOMOCK, unidadeCurricularDTOMOCK, unidadeCurricularDTOMOCK);

        when(service.findAll()).thenReturn(list);

        ResponseEntity<Object> responseEntity = controller.listAll();

        assertEquals(responseEntity.getBody(), list);
    }

    @Test
    public void shouldNotFindAll()
    {
        List<UnidadeCurricularDTO> list = List.of();

        when(service.findAll()).thenReturn(list);

        assertThrows(ListaVaziaException.class, () -> controller.listAll());
    }
}
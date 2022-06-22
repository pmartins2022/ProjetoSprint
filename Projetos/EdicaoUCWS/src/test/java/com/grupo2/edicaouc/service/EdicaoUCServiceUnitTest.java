package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCDTOMapper;
import com.grupo2.edicaouc.exception.ErrorDetail;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.repository.EdicaoUCRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class EdicaoUCServiceUnitTest {

    @MockBean
    EdicaoUCRepository repository;

    @MockBean
    EdicaoUCDTOMapper mapper;


    @MockBean
    private UnidadeCurricularService ucService;

    @MockBean
    private AnoLetivoService anoLetivoService;
    @InjectMocks
    EdicaoUCService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAllEdicaoByUCCode()
    {
        EdicaoUCDTO edicaoUCDTOMOCK = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUCMOCK = mock(EdicaoUC.class);

        List<EdicaoUC> list = List.of(edicaoUCMOCK,edicaoUCMOCK,edicaoUCMOCK);

        when(repository.findAllEdicaoByUCCode("2")).thenReturn(list);

        when(mapper.toDTO(edicaoUCMOCK)).thenReturn(edicaoUCDTOMOCK);

        List<EdicaoUCDTO> resultado =service.findAllEdicaoByUCCode("2");

        assertEquals(resultado.size(),list.size());
    }
    @Test
    public void shouldFindEdicaoUC_Exists()
    {
        EdicaoUCDTO edicaoUCDTOMOCK = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUCMOCK = mock(EdicaoUC.class);

        when(repository.findById(1L)).thenReturn(Optional.of(edicaoUCMOCK));
        when(mapper.toDTO(edicaoUCMOCK)).thenReturn(edicaoUCDTOMOCK);

        Optional<EdicaoUCDTO> saved = service.findById(1L);

        assertEquals(edicaoUCDTOMOCK, saved.get());
    }
    @Test
    public void shouldNotFindEdicaoUC_Empty()
    {
        EdicaoUCDTO edicaoUCDTO = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUC = mock(EdicaoUC.class);

        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(mapper.toDTO(edicaoUC)).thenReturn(edicaoUCDTO);

        Optional<EdicaoUCDTO> saved = service.findById(1L);

        assertTrue(saved.isEmpty());
    }
}
package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.controller.EdicaoUCController;
import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCDTOMapper;
import com.grupo2.edicaouc.exception.BaseDadosException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.repository.rest.UtilizadorRestRepository;
import com.grupo2.edicaouc.service.AnoLetivoService;
import com.grupo2.edicaouc.service.EdicaoUCService;
import com.grupo2.edicaouc.service.UnidadeCurricularService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
@Transactional
class EdicaoUCRepositoryUnitTests
{
    @MockBean
    private EdicaoUCRepository repository;

    @MockBean
    private EdicaoUCDTOMapper mapper;

    @MockBean
    private UnidadeCurricularService ucService;

    @MockBean
    private AnoLetivoService anoLetivoService;

    @MockBean
    private UtilizadorRestRepository utilizadorRestRepository;

    @InjectMocks
    EdicaoUCService service;


    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldCreateEdicaoUC()
    {
        //arrange
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUCMOCK = mock(EdicaoUC.class);

        when(mapper.toModel(dtoMOCK)).thenReturn(edicaoUCMOCK);
        when(ucService.findBySigla(edicaoUCMOCK.getUCCode())).thenReturn(Optional.of(new UnidadeCurricularDTO()));
        when(anoLetivoService.findByID(edicaoUCMOCK.getAnoLetivoCode())).thenReturn(Optional.of(new AnoLetivoDTO()));
        when(repository.saveEdicaoUC(edicaoUCMOCK)).thenReturn(edicaoUCMOCK);
        when(mapper.toDTO(edicaoUCMOCK)).thenReturn(dtoMOCK);

        when(dtoMOCK.getRucID()).thenReturn(1L);

        when(utilizadorRestRepository.isRole("ROLE_DOCENTE",dtoMOCK.getRucID())).thenReturn(true);

        //act
        EdicaoUCDTO saved = service.createEdicaoUC(dtoMOCK);
        //assert
        assertEquals(saved, dtoMOCK);
    }

    @Test
    public void shouldFindAllEdicaoUCByUCCode()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUCMOCK = mock(EdicaoUC.class);
        List<EdicaoUC> list = List.of(edicaoUCMOCK, edicaoUCMOCK);

        when(repository.findAllEdicaoByUCCode("PTA")).thenReturn(list);
        when(mapper.toDTO(edicaoUCMOCK)).thenReturn(dtoMOCK);

        List<EdicaoUCDTO> ucdtoList = service.findAllEdicaoByUCCode("PTA");

        assertEquals(ucdtoList.size(), list.size());
    }

    @Test
    public void shouldNotFindAllEdicaoUCByUCCode()
    {
        when(repository.findAllEdicaoByUCCode("PTA")).thenReturn(List.of());

        List<EdicaoUCDTO> ucdtoList = service.findAllEdicaoByUCCode("PTA");

        assertTrue(ucdtoList.isEmpty());
    }

    @Test
    public void shouldFindById()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUCMOCK = mock(EdicaoUC.class);

        when(repository.findById(1L)).thenReturn(Optional.of(edicaoUCMOCK));
        when(mapper.toDTO(edicaoUCMOCK)).thenReturn(dtoMOCK);

        Optional<EdicaoUCDTO> found = service.findById(1L);

        assertEquals(found.get(), dtoMOCK);
    }

    @Test
    public void shouldNotFindById_NotExists()
    {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<EdicaoUCDTO> found = service.findById(1L);

        assertEquals(found, Optional.empty());
    }

}
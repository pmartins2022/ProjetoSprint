package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.exception.BaseDadosException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCJPAMapper;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import com.grupo2.edicaouc.service.EdicaoUCService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
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
        //arrange
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.createEdicaoUC(dtoMOCK)).thenReturn(dtoMOCK);
        //
        ResponseEntity<Object> responseEntity = controller.createEdicao(dtoMOCK);
        //
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateEdicaoUC_Exists()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.createEdicaoUC(dtoMOCK)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, ()-> controller.createEdicao(dtoMOCK));
    }

    @Test
    public void shouldNotCreateEdicaoUC_InvalidAttributes()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.createEdicaoUC(dtoMOCK)).thenThrow(BaseDadosException.class);

        assertThrows(BaseDadosException.class, ()-> controller.createEdicao(dtoMOCK));
    }

    @Test
    public void shouldFindAllByUCCode()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);
        List<EdicaoUCDTO> list = List.of(dtoMOCK, dtoMOCK, dtoMOCK);

        when(service.findAllEdicaoByUCCode("PTA")).thenReturn(list);

        ResponseEntity<List<EdicaoUCDTO>> responseEntity = controller.listEdicaoByUCCode("PTA");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAllByUCCode_NotExists()
    {
        when(service.findAllEdicaoByUCCode("PTA")).thenReturn(List.of());

        assertThrows(ListaVaziaException.class, () -> controller.listEdicaoByUCCode("PTA"));
    }


    @Test
    public void shouldFindByIdEdicaoUC()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.findById(1L)).thenReturn(Optional.of(dtoMOCK));

        ResponseEntity<Object> responseEntity = controller.findById(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindByIdEdicaoUC_NotExists()
    {
        when(service.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class, () -> controller.findById(1L));
    }
}
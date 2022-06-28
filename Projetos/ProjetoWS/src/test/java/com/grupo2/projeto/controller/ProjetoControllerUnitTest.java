package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class ProjetoControllerUnitTest
{
    /*@MockBean
    ProjetoService service;

    @InjectMocks
    ProjetoController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidProjeto()
    {
        ProjetoDTO projetoDTOMOCK = mock(ProjetoDTO.class);

        when(service.createAndSave(projetoDTOMOCK)).thenReturn(projetoDTOMOCK);

        ResponseEntity<ProjetoDTO> responseEntity = controller.createProjeto(projetoDTOMOCK);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldFindProjeto_Exists()
    {
        ProjetoDTO projetoDTOMOCK = mock(ProjetoDTO.class);

        when(service.findById(1L)).thenReturn(Optional.of(projetoDTOMOCK));

        ResponseEntity<ProjetoDTO> responseEntity = controller.findById(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindProjeto_NotExists()
    {
        when(service.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ErroGeralException.class, ()-> controller.findById(2L));
    }*/
}
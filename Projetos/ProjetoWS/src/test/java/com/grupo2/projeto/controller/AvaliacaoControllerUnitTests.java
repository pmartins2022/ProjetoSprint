package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.exception.ListaVaziaException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.service.AvaliacaoService;
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
class AvaliacaoControllerUnitTests
{
    /*@MockBean
    AvaliacaoService service;

    @InjectMocks
    AvaliacaoController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAvaliacao()
    {
        AvaliacaoDTO avaliacaoMOCK = mock(AvaliacaoDTO.class);

        when(service.createAndSave(avaliacaoMOCK)).thenReturn(avaliacaoMOCK);

        ResponseEntity<AvaliacaoDTO> avaliacaoDTO = controller.createAvaliacao(avaliacaoMOCK);

        assertEquals(avaliacaoDTO.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldFindByID()
    {
        AvaliacaoDTO avaliacaoMOCK = mock(AvaliacaoDTO.class);

        when(service.findById(1L)).thenReturn(Optional.ofNullable(avaliacaoMOCK));

        ResponseEntity<AvaliacaoDTO> avaliacaoDTO = controller.findById(1L);

        assertEquals(avaliacaoDTO.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindByID_NotExists()
    {
        AvaliacaoDTO avaliacaoMOCK = mock(AvaliacaoDTO.class);

        when(service.findById(1L)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, ()-> controller.findById(1L));
    }

    @Test
    public void shouldFindAll()
    {
        AvaliacaoDTO avaliacaoMOCK = mock(AvaliacaoDTO.class);

        when(service.findAll()).thenReturn(List.of(avaliacaoMOCK, avaliacaoMOCK));

        ResponseEntity<Object> avaliacaoDTO = controller.listAllMomentoAvaliacao();

        assertEquals(avaliacaoDTO.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAll_Empty()
    {
        when(service.findAll()).thenThrow(ListaVaziaException.class);

        assertThrows(ListaVaziaException.class, ()->  controller.listAllMomentoAvaliacao());
    }
*/
}
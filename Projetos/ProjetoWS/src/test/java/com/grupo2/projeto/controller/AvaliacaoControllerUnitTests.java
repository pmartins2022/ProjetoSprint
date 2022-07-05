package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.exception.ListaVaziaException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.service.AvaliacaoNotaService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class AvaliacaoControllerUnitTests
{
    @MockBean
    AvaliacaoService service;
    @MockBean
    AvaliacaoNotaService notaService;

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

        //when(service.createAndSave(avaliacaoMOCK)).thenReturn(avaliacaoMOCK);

        doNothing().when(service).createAndSave(avaliacaoMOCK);

        ResponseEntity<AvaliacaoDTO> avaliacaoDTO = controller.createAvaliacao(avaliacaoMOCK);

        assertEquals(avaliacaoDTO.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldFindByID()
    {
        AvaliacaoDTO avaliacaoMOCK = mock(AvaliacaoDTO.class);

        when(service.findById(1L)).thenReturn(avaliacaoMOCK);

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

        ResponseEntity<Object> avaliacaoDTO = controller.listAllAvaliacao();

        assertEquals(avaliacaoDTO.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAll_Empty()
    {
        when(service.findAll()).thenThrow(ListaVaziaException.class);

        assertThrows(ListaVaziaException.class, () -> controller.listAllAvaliacao());
    }

    @Test
    public void shouldReviewAvalicao()
    {
        doNothing().when(notaService).reviewAvaliacaoNota(1L, "REVISAO");
        assertDoesNotThrow(() -> controller.reviewAvaliacaoNota(1L, "REVISAO"));
    }


    @Test
    public void shouldNotReviewAvalicao_EnumInvalido()
    {
        doThrow(ValidacaoInvalidaException.class).when(notaService).reviewAvaliacaoNota(1L, "ss");
        assertThrows(ValidacaoInvalidaException.class,() -> controller.reviewAvaliacaoNota(1L, "ss"));
    }

}
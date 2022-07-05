package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.service.ConteudoService;
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
class ConteudoControllerUnitTests
{
    @MockBean
    ConteudoService service;

    @InjectMocks
    ConteudoController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateConteudo()
    {
        ConteudoDTO conteudoMOCK = mock(ConteudoDTO.class);

        when(service.createAndSave(conteudoMOCK)).thenReturn(conteudoMOCK);

        ResponseEntity<ConteudoDTO> conteudoDTO = controller.createConteudo(conteudoMOCK);

        assertEquals(conteudoDTO.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldFindByID()
    {
        ConteudoDTO conteudoMOCK = mock(ConteudoDTO.class);

        when(service.findById(1L)).thenReturn(Optional.of(conteudoMOCK));

        ResponseEntity<ConteudoDTO> conteudoDTO = controller.findById(1L);

        assertEquals(conteudoDTO.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindByID_Empty()
    {
        when(service.findById(1L)).thenThrow(ErroGeralException.class);

        assertThrows(ErroGeralException.class, ()-> controller.findById(1L));
    }

    @Test
    public void shouldAcceptConteudo()
    {
        ConteudoDTO conteudoMOCK = mock(ConteudoDTO.class);

        when(service.acceptConteudo(1L)).thenReturn(conteudoMOCK);

        ResponseEntity<Object> conteudoDTO = controller.acceptConteudo(1L);

        assertEquals(conteudoDTO.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotAcceptConteudo_Empty()
    {
        when(service.acceptConteudo(1L)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, ()-> controller.acceptConteudo(1L));
    }

    @Test
    public void shouldNotAcceptConteudo_InvalidAtributtes()
    {
        when(service.acceptConteudo(1L)).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, ()-> controller.acceptConteudo(1L));
    }
    /*


    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/rejeitarConteudo/{id}")
    public ResponseEntity<ConteudoDTO> rejectConteudo(@PathVariable("idConteudo") Long idConteudo)
    {
        try
        {
            ConteudoDTO dto = service.rejeitarConteudo(idConteudo);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (AtualizacaoInvalidaException e)
        {
            throw e;
        }
        catch (IdInvalidoException e)
        {
            throw e;
        }
    }
     */

    @Test
    public void shouldRejectConteudo()
    {
        ConteudoDTO conteudoMOCK = mock(ConteudoDTO.class);

        when(service.rejeitarConteudo(1L)).thenReturn(conteudoMOCK);

        ResponseEntity<ConteudoDTO> conteudoDTO = controller.rejectConteudo(1L);

        assertEquals(conteudoDTO.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotRejectConteudo_Empty()
    {
        when(service.rejeitarConteudo(1L)).thenThrow(AtualizacaoInvalidaException.class);

        assertThrows(AtualizacaoInvalidaException.class, ()-> controller.rejectConteudo(1L));
    }

    @Test
    public void shouldNotRejectConteudo_InvalidAtributtes()
    {
        when(service.rejeitarConteudo(1L)).thenThrow(IdInvalidoException.class);

        assertThrows(IdInvalidoException.class, ()-> controller.rejectConteudo(1L));
    }
}
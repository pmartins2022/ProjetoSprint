package com.grupo2.proposta.controller;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.exception.ValidacaoInvalidaException;
import com.grupo2.proposta.service.PropostaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConviteControllerUnitTest
{
    @MockBean
    private PropostaService service;

    @MockBean
    private HttpServletRequest request;

    @InjectMocks
    private ConviteController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateConvite_valid() throws Exception
    {
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(service.createConvite(conviteDTO)).thenReturn(conviteDTO);

        ResponseEntity<Object> save = controller.criarConvite(conviteDTO);

        assertEquals(save.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateConvite_empty()
    {
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(service.createConvite(conviteDTO)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class,()->controller.criarConvite(conviteDTO));
    }

    @Test
    public void shouldNotCreateConvite_ivalid()
    {
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(service.createConvite(conviteDTO)).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class,()->controller.criarConvite(conviteDTO));
    }

    @Test
    public void shouldNotCreateConvite_invalidId()
    {
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(service.createConvite(conviteDTO)).thenThrow(IdInvalidoException.class);

        assertThrows(IdInvalidoException.class,()->controller.criarConvite(conviteDTO));
    }

    @Test
    public void shouldAceitarOrientacao() throws Exception
    {
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(service.acceptOrientacaoProposta(1L, 1L, request.getAuthType())).thenReturn(conviteDTO);

        ResponseEntity<Object> save = controller.aceitarOrientacao(1L, 1L, request);

        assertEquals(save.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldRejeitarOrientacao() throws Exception
    {
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(service.rejectOrientacaoProposta(1L, 1L, request.getAuthType())).thenReturn(conviteDTO);

        ResponseEntity<Object> save = controller.rejeitarOrientacao(1L, 1L, request);

        assertEquals(save.getStatusCode(), HttpStatus.CREATED);
    }
}
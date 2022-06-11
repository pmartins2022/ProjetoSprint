package com.grupo2.proposta.controller;

import com.grupo2.proposta.dto.ProjetoDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.ListaVaziaException;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaControllerUnitTests
{
    @MockBean
    private PropostaService service;

    @MockBean
    private HttpServletRequest request;

    @InjectMocks
    private PropostaController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateProposta_valid()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.createProposta(prop,"")).thenReturn(prop);

        ResponseEntity<PropostaDTO> proposta = controller.createProposta(prop,request);

        assertEquals(proposta.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateProposta_invalid()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.createProposta(prop,"")).thenThrow(BaseDadosException.class);

        assertThrows(BaseDadosException.class,()->controller.createProposta(prop,request));
    }

    @Test
    public void shouldAcceptProposta_valid()
    {
        ProjetoDTO prop = mock(ProjetoDTO.class);

        when(service.acceptCandidaturaProposta(1L, 1L, 1L)).thenReturn(prop);

        ResponseEntity<Object> objectResponseEntity = controller.aceitarProposta(1L, 1L, 1L);

        assertEquals(objectResponseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldNotAcceptProposta_invalidId()
    {
        ProjetoDTO prop = mock(ProjetoDTO.class);

        when(service.acceptCandidaturaProposta(1L, 1L, 1L)).thenThrow(IdInvalidoException.class);

        assertThrows(IdInvalidoException.class,()->controller.aceitarProposta(1L, 1L, 1L));
    }

    @Test
    public void shouldNotAcceptProposta_invalidAtualizacao()
    {
        ProjetoDTO prop = mock(ProjetoDTO.class);

        when(service.acceptCandidaturaProposta(1L, 1L, 1L)).thenThrow(AtualizacaoInvalidaException.class);

        assertThrows(AtualizacaoInvalidaException.class,()->controller.aceitarProposta(1L, 1L, 1L));
    }

    @Test
    public void shouldListById()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        List<PropostaDTO> list = List.of(prop,prop,prop);

        when(service.findByIdUtilizador(1L)).thenReturn(list);

        ResponseEntity<Object> response = controller.listbyIdUtilizador(1L);

        assertEquals(response.getBody(), list);
    }

    @Test
    public void shouldThrow_ListById_empty()
    {
        List<PropostaDTO> list = List.of();

        when(service.findByIdUtilizador(1L)).thenReturn(list);

        assertThrows(ListaVaziaException.class,()->controller.listbyIdUtilizador(1L));
    }

    @Test
    public void shouldRejectProposta()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.rejeitarProposta(1L)).thenReturn(Optional.of(prop));

        ResponseEntity<PropostaDTO> response = controller.rejeitarCandidaturaProposta(1L);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldNotRejectProposta_invalidId()
    {
        when(service.rejeitarProposta(1L)).thenReturn(Optional.empty());

        assertThrows(IdInvalidoException.class,()->controller.rejeitarCandidaturaProposta(1L));
    }

    @Test
    public void shouldNotRejectProposta_invalid()
    {
        when(service.rejeitarProposta(1L)).thenThrow(AtualizacaoInvalidaException.class);

        assertThrows(AtualizacaoInvalidaException.class,()->controller.rejeitarCandidaturaProposta(1L));
    }

    @Test
    public void shouldListByNif()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        List<PropostaDTO> list = List.of(prop,prop,prop);

        when(service.findByNif(1,"")).thenReturn(list);

        ResponseEntity<Object> entity = controller.listbyNif(1,request);

        assertEquals(entity.getBody(),list);
    }

    @Test
    public void shouldNotListByNif_Empty()
    {
        List<PropostaDTO> list = List.of();

        when(service.findByNif(1,"")).thenReturn(list);

        assertThrows(ListaVaziaException.class,()->controller.listbyNif(1,request));
    }

    @Test
    public void shouldListByTitulo()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        List<PropostaDTO> list = List.of(prop,prop,prop);

        when(service.findByTitulo("AAA")).thenReturn(list);

        ResponseEntity<Object> response = controller.listbyTitulo("AAA");

        assertEquals(response.getBody(),list);
    }

    @Test
    public void shouldNotListByTitulo_Empty()
    {
        List<PropostaDTO> list = List.of();

        when(service.findByTitulo("AAA")).thenReturn(list);

        assertThrows(ListaVaziaException.class,()->controller.listbyTitulo("AAA"));
    }
}
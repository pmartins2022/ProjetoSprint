
package com.grupo2.proposta.controller;

import com.grupo2.proposta.dto.ProjetoDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.ListaVaziaException;
import com.grupo2.proposta.model.PropostaEstado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
class PropostaControllerIntegrationTests
{
    @Autowired
    private PropostaController controller;

    @Test
    public void shouldCreateProposta_valid()
    {
        PropostaDTO prop = new PropostaDTO(1L, 1L, 1L, "titulo",
                "problema", "objetivo",  1L, PropostaEstado.CANDIDATURA);

        ResponseEntity<PropostaDTO> proposta = controller.createProposta(prop);

        assertEquals(proposta.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateProposta_invalid()
    {
        PropostaDTO prop = new PropostaDTO(1L, 1L, 1L, "titulo",
                "problema", "objetivo",  1L, PropostaEstado.CANDIDATURA);

        assertThrows(BaseDadosException.class,()->controller.createProposta(prop));
    }

    @Test
    public void shouldAcceptProposta_valid()
    {
        ResponseEntity<Object> objectResponseEntity = controller.aceitarProposta(1L, 1L, 1L);

        assertEquals(objectResponseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldNotAcceptProposta_invalidId()
    {
        assertThrows(IdInvalidoException.class,()->controller.aceitarProposta(1L, 1L, 1L));
    }

    @Test
    public void shouldNotAcceptProposta_invalidAtualizacao()
    {
        assertThrows(AtualizacaoInvalidaException.class,()->controller.aceitarProposta(1L, 1L, 1L));
    }
/*
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

        ResponseEntity<PropostaDTO> response = controller.rejeitarProposta(1L);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldNotRejectProposta_invalidId()
    {
        when(service.rejeitarProposta(1L)).thenReturn(Optional.empty());

        assertThrows(IdInvalidoException.class,()->controller.rejeitarProposta(1L));
    }

    @Test
    public void shouldNotRejectProposta_invalid()
    {
        when(service.rejeitarProposta(1L)).thenThrow(AtualizacaoInvalidaException.class);

        assertThrows(AtualizacaoInvalidaException.class,()->controller.rejeitarProposta(1L));
    }

    @Test
    public void shouldListByNif()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        List<PropostaDTO> list = List.of(prop,prop,prop);

        when(service.findByNif(1)).thenReturn(list);

        ResponseEntity<Object> entity = controller.listbyNif(1);

        assertEquals(entity.getBody(),list);
    }

    @Test
    public void shouldNotListByNif_Empty()
    {
        List<PropostaDTO> list = List.of();

        when(service.findByNif(1)).thenReturn(list);

        assertThrows(ListaVaziaException.class,()->controller.listbyNif(1));
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

 */
}

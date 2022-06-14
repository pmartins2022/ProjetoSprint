
package com.grupo2.proposta.controller;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.ListaVaziaException;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PropostaControllerIntegrationTests
{
    @Autowired
    PropostaController controller;
    @Autowired
    PropostaJPARepository jpaRepository;

    @Test
    public void shouldFindById()
    {
        PropostaJPA proposta = new PropostaJPA(1L, 1L, 1L,
                "AAAAAAAAAA", "AAAAAAAAAA", "AAAAAAAAAA",
                1L, PropostaEstado.APROVADO);

        PropostaJPA save = jpaRepository.save(proposta);

        ResponseEntity<Object> response = controller.listbyIdUtilizador(1L);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody() instanceof List);
        assertEquals(1, ((List) response.getBody()).size());
    }

    @Test
    public void shouldNotFindById()
    {
        assertThrows(ListaVaziaException.class,()->controller.listbyIdUtilizador(99L));
    }

    @Test
    public void shouldFindAllByTitulo()
    {
        PropostaJPA proposta = new PropostaJPA(1L, 20L, 1L,
                "AAAAAAAACC", "AAAAAAAAAA", "AAAAAAAAAA",
                1L, PropostaEstado.APROVADO);

        jpaRepository.save(proposta);

        ResponseEntity<Object> response = controller.listbyTitulo("AAAAAAAACC");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody() instanceof List);
        assertEquals(1, ((List) response.getBody()).size());
    }

    @Test
    public void shouldNotFindByTitulo()
    {
       assertThrows(ListaVaziaException.class,()->controller.listbyTitulo("AAAAAAAACC"));
    }

    @Test
    public void shouldRejectProposta()
    {
        PropostaJPA proposta = new PropostaJPA(1L, 20L, 1L,
                "AAAAAAAACC", "AAAAAAAAAA", "AAAAAAAAAA",
                1L, PropostaEstado.CANDIDATURA);

        PropostaJPA save = jpaRepository.save(proposta);

        ResponseEntity<PropostaDTO> response = controller.rejectCandidaturaProposta(save.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldNotRejectProposta_InvalidID()
    {
        assertThrows(IdInvalidoException.class, ()->controller.rejectCandidaturaProposta(99L));
    }

    @Test
    public void shouldNotRejectProposta_InvalidUpdate()
    {
        PropostaJPA proposta = new PropostaJPA(1L, 20L, 1L,
                "AAAAAAAACC", "AAAAAAAAAA", "AAAAAAAAAA",
                1L, PropostaEstado.APROVADO);

        PropostaJPA save = jpaRepository.save(proposta);

        assertThrows(AtualizacaoInvalidaException.class,()->controller.rejectCandidaturaProposta(save.getId()));
    }
}
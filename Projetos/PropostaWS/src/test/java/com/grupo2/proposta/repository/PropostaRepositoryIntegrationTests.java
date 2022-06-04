package com.grupo2.proposta.repository;

import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.model.factory.PropostaFactory;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PropostaRepositoryIntegrationTests
{
    @Autowired
    PropostaRepository repository;

    @Autowired
    PropostaFactory factory;

    @Autowired
    PropostaJPARepository jpaRepository;

    @Test
    public void shouldFindById()
    {
        PropostaJPA proposta = new PropostaJPA(1L, 1L, 1L,
                "AAAAAAAAAA", "AAAAAAAAAA", "AAAAAAAAAA",
                1L, PropostaEstado.APROVADO);

        PropostaJPA save = jpaRepository.save(proposta);

        Optional<Proposta> id = repository.findById(save.getId());

        assertTrue(id.isPresent());
        assertEquals(id.get().getProblema(),save.getProblema());
    }

    @Test
    public void shouldNotFindById()
    {
        Optional<Proposta> id = repository.findById(99L);
        assertTrue(id.isEmpty());
    }

    @Test
    public void shouldFindByIdUtilizador()
    {
        PropostaJPA proposta = new PropostaJPA(1L, 20L, 1L,
                "AAAAAAAAAA", "AAAAAAAAAA", "AAAAAAAAAA",
                1L, PropostaEstado.APROVADO);

        PropostaJPA save = jpaRepository.save(proposta);

        List<Proposta> utilizador = repository.findByIdUtilizador(20L);

        assertEquals(1, utilizador.size());
    }

    @Test
    public void shouldNotFindByIdUtilizador()
    {
        List<Proposta> utilizador = repository.findByIdUtilizador(99L);
        assertTrue(utilizador.isEmpty());
    }

    @Test
    public void shouldFindAllByTitulo()
    {
        PropostaJPA proposta = new PropostaJPA(1L, 20L, 1L,
                "AAAAAAAACC", "AAAAAAAAAA", "AAAAAAAAAA",
                1L, PropostaEstado.APROVADO);

        PropostaJPA save = jpaRepository.save(proposta);

        List<Proposta> titulo = repository.findAllByTitulo("AAAAAAAACC");

        assertEquals(1, titulo.size());
    }

    @Test
    public void shouldNotFindByTitulo()
    {
        List<Proposta> titulo = repository.findAllByTitulo("ASOJASFJASFOSAFIO");

        assertEquals(0,titulo.size());
    }

    @Test
    public void shouldUpdateProposta()
    {
        PropostaJPA proposta = new PropostaJPA(1L, 20L, 1L,
                "AAAAAAAACC", "AAAAAAAAAA", "AAAAAAAAAA",
                1L, PropostaEstado.CANDIDATURA);

        PropostaJPA jpa = jpaRepository.save(proposta);

        Proposta proposta2 = new Proposta(jpa.getId(), 20L, 1L,
                "QWERAAAACC", "ABCDEFGHIJKL", "ZXCVBNMLKJ",
                1L, PropostaEstado.CANDIDATURA);

        Optional<Proposta> propostaSaved = repository.atualizarProposta(proposta2);

        assertTrue(propostaSaved.isPresent());

        assertEquals(proposta2.getTitulo(),propostaSaved.get().getTitulo());
        assertEquals(proposta2.getProblema(),propostaSaved.get().getProblema());
        assertEquals(proposta2.getObjetivo(),propostaSaved.get().getObjetivo());
    }
}
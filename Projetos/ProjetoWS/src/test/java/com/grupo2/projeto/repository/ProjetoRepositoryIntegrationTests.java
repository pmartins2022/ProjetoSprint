package com.grupo2.projeto.repository;

import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.model.factory.ProjetoFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProjetoRepositoryIntegrationTests
{
    /*@Autowired
    private ProjetoRepository repository;

    @Autowired
    private ProjetoFactory factory;

    @Test
    public void shouldSaveProjeto()
    {
        Projeto projeto = factory.createProjeto(1L,1L,1L,1L);

        Projeto projetoSaved = repository.saveProjeto(projeto);

        assertNotNull(projetoSaved);
        assertEquals(projeto, projetoSaved);
    }

    @Test
    public void shouldFindById()
    {
        Projeto projeto = factory.createProjeto(1L,1L,1L,1L);

        Projeto projetoSaved = repository.saveProjeto(projeto);

        Optional<Projeto> projetoFound = repository.findById(projetoSaved.getId());

        assertTrue(projetoFound.isPresent());
        assertEquals(projetoSaved, projetoFound.get());
    }

    @Test
    public void shouldNotFindById()
    {
        Optional<Projeto> projetoFound = repository.findById(99L);

        assertTrue(projetoFound.isEmpty());
    }*/
}
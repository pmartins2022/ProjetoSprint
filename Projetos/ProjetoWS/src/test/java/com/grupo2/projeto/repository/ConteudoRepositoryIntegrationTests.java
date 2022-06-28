package com.grupo2.projeto.repository;

import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.EstadoConteudo;
import com.grupo2.projeto.model.factory.ConteudoFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ConteudoRepositoryIntegrationTests
{
    /*@Autowired
    private ConteudoRepository repository;

    @Autowired
    private ConteudoFactory factory;

    @Test
    public void shouldSaveConteudo()
    {
        Conteudo conteudo = factory.createConteudo(1L,1L,"Titulo","cccc","descricao","PT", EstadoConteudo.PENDENTE);
        Conteudo conteudo1 = repository.saveConteudo(conteudo);
        assertNotNull(conteudo1);
        assertEquals(conteudo,conteudo1);
    }

    @Test
    public void shouldFindById()
    {
        Conteudo conteudo = factory.createConteudo(1L,1L,"Titulo","cccc","descricao","PT", EstadoConteudo.PENDENTE);

        Conteudo conteudo1 = repository.saveConteudo(conteudo);

        Optional<Conteudo> id = repository.findById(conteudo1.getId());

        assertTrue(id.isPresent());
        assertEquals(conteudo1,id.get());
    }

    @Test
    public void shouldNotFindById()
    {
        Optional<Conteudo> id = repository.findById(99L);

        assertTrue(id.isEmpty());
    }

    @Test
    public void shouldAtualizarConteudo()
    {
        Conteudo c = factory.createConteudo(1L,1L,"Titulo","cccc","descricao","PT", EstadoConteudo.PENDENTE);

        Conteudo c1 = repository.saveConteudo(c);

        assertEquals(c,c1);

        c1.setTitulo("Novo titulo");

        Conteudo conteudo = repository.atualizarConteudo(c1);

        assertNotEquals(c,conteudo);

        assertEquals(c1,conteudo);
    }*/
}
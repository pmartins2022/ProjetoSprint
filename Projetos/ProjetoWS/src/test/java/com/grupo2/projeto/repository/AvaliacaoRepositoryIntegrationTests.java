package com.grupo2.projeto.repository;

import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.EstadoConteudo;
import com.grupo2.projeto.model.factory.AvaliacaoFactory;
import com.grupo2.projeto.model.factory.ConteudoFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AvaliacaoRepositoryIntegrationTests
{
    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private AvaliacaoFactory factory;

    @Autowired
    private ConteudoFactory factoryConteudo;

    @Test
    public void shouldSaveAvaliacao()
    {
        Conteudo conteudo = factoryConteudo.createConteudo(null,1L,"Titulo","cccc","descricao","PT", EstadoConteudo.PENDENTE);

        Conteudo savedCont = conteudoRepository.saveConteudo(conteudo);

        Avaliacao avaliacao = factory.createAvaliacao(1L,1L,1L,1L,2L,3L,savedCont.getId(),10);

        Avaliacao avaliacao1 = repository.saveAvaliacao(avaliacao,savedCont);

        assertNotNull(avaliacao1);
        assertEquals(avaliacao,avaliacao1);
    }

    @Test
    public void shouldFindById()
    {
        Conteudo conteudo = factoryConteudo.createConteudo(null,1L,"Titulo","cccc","descricao","PT", EstadoConteudo.PENDENTE);

        Conteudo savedCont = conteudoRepository.saveConteudo(conteudo);

        Avaliacao avaliacao = factory.createAvaliacao(1L,1L,1L,1L,2L,3L,savedCont.getId(),10);

        Avaliacao avaliacao1 = repository.saveAvaliacao(avaliacao,savedCont);

        Optional<Avaliacao> optionalAvaliacao = repository.findById(avaliacao1.getId());

        assertTrue(optionalAvaliacao.isPresent());
        assertEquals(avaliacao1,optionalAvaliacao.get());
    }

    @Test
    public void shouldNotFindById()
    {
        Optional<Avaliacao> optionalAvaliacao = repository.findById(99L);

        assertFalse(optionalAvaliacao.isPresent());
    }

    @Test
    public void shouldFindAll()
    {
        Conteudo conteudo = factoryConteudo.createConteudo(null,1L,"Titulo","cccc","descricao","PT", EstadoConteudo.PENDENTE);

        Conteudo savedCont = conteudoRepository.saveConteudo(conteudo);

        Avaliacao avaliacao = factory.createAvaliacao(1L,1L,1L,1L,2L,3L,savedCont.getId(),10);

        repository.saveAvaliacao(avaliacao,savedCont);

        List<Avaliacao> all = repository.findAll();

        assertEquals(1,all.size());
    }

}
package com.grupo2.organizacao.repository;

import com.grupo2.organizacao.model.Organizacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrganizacaoRepositoryIntegrationTest
{
    @Autowired
    OrganizacaoRepository repository;

    @Test
    public void shouldSaveValidOrganizacao()
    {
        Organizacao organizacao = new Organizacao("denominacao", 111222333);

        Organizacao saved = repository.save(organizacao);

        assertEquals(organizacao, saved);
    }

    @Test
    public void shouldFindById_Exists()
    {
        Organizacao organizacao = new Organizacao("denominacao", 111222333);

        Organizacao save = repository.save(organizacao);

        Optional<Organizacao> optional = repository.findByID(save.getId());

        assertEquals(optional.get(), save);
    }

    @Test
    public void shouldNotFindById_NotExists()
    {
        Optional<Organizacao> optional = repository.findByID(1L);

        assertTrue(optional.isEmpty());
    }

    @Test
    public void shouldFindAll_ExistsOrganizacao()
    {
        Organizacao organizacao = new Organizacao( "denominacao", 111222333);
        Organizacao organizacao1 = new Organizacao( "denominacao", 111522333);
        Organizacao organizacao2 = new Organizacao( "denominacao", 111222633);


        repository.save(organizacao);
        repository.save(organizacao1);
        repository.save(organizacao2);

        List<Organizacao> organizacoes = repository.findAll();

        assertEquals(3, organizacoes.size());
    }

    @Test
    public void shouldNotFindAll_Empty()
    {
        List<Organizacao> letivos = repository.findAll();

        assertTrue(letivos.isEmpty());
    }

    @Test
    public void shouldFindByNif_Exists()
    {
        Organizacao organizacao = new Organizacao( "denominacao", 111222333);

        repository.save(organizacao);

        Optional<Organizacao> optional = repository.findByNIF(111222333);

        assertTrue(optional.isPresent());
    }

    @Test
    public void shouldNotFindByNif_NotExists()
    {
        Optional<Organizacao> optional = repository.findByNIF(111222333);

        assertTrue(optional.isEmpty());
    }
}
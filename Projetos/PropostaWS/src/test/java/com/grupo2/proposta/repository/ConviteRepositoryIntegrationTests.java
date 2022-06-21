/*
package com.grupo2.proposta.repository;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.model.EstadoConvite;
import com.grupo2.proposta.repository.jpa.ConviteJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@ContextConfiguration(classes= {ConviteRepository.class, ConviteJPARepository.class})
class ConviteRepositoryIntegrationTests
{
    @Autowired
    private ConviteRepository repository;

    @Autowired
    private ConviteJPARepository jpaRepository;


    @Test
    public void shouldCreateConvite()
    {
        Convite convite = new Convite(1L, 1L, 1L, EstadoConvite.ACEITE);

        Convite saved = repository.createAndSaveConvite(convite);

        assertEquals(saved, convite);
    }

    @Test
    public void shouldFindByPropostaAndAluno()
    {
        Convite convite = new Convite(1L, 1L, 1L, EstadoConvite.ACEITE);
        ConviteID conviteID = new ConviteID(1L, 1L, 1L);

        ConviteJPA conviteJPA = new ConviteJPA(conviteID, EstadoConvite.ACEITE);
        jpaRepository.save(conviteJPA);

        Optional<Convite> saved = repository.findByPropostaAndAluno(conviteJPA.getId().getIdproposta(), conviteID.getIdaluno());

        assertEquals(saved, convite);
    }

    @Test
    public void shouldNotFindByPropostaAndAluno()
    {
        Optional<Convite> saved = repository.findByPropostaAndAluno(1L, 1L);

        assertTrue(saved.isPresent());
    }

    @Test
    public void shouldFindByDocenteAndProposta()
    {
        Convite convite = new Convite(1L, 1L, 1L, EstadoConvite.ACEITE);
        ConviteID conviteID = new ConviteID(1L, 1L, 1L);

        ConviteJPA conviteJPA = new ConviteJPA(conviteID, EstadoConvite.ACEITE);
        jpaRepository.save(conviteJPA);

        Optional<Convite> saved = repository.findByDocenteAndProposta(conviteJPA.getId().getIdproposta(), conviteID.getIdaluno());

        assertEquals(saved, convite);
    }

    @Test
    public void shouldNotFindByDocenteAndProposta()
    {
        Optional<Convite> saved = repository.findByDocenteAndProposta(1L, 1L);

        assertTrue(saved.isPresent());
    }

    @Test
    public void shouldFindByID()
    {
        Convite convite = new Convite(1L, 1L, 1L, EstadoConvite.ACEITE);
        ConviteID conviteID = new ConviteID(1L, 1L, 1L);

        ConviteJPA conviteJPA = new ConviteJPA(conviteID, EstadoConvite.ACEITE);
        jpaRepository.save(conviteJPA);

        Optional<Convite> saved = repository.findById(conviteID);

        assertEquals(saved, convite);
    }

    @Test
    public void shouldNotFindByID()
    {
        Optional<Convite> saved = repository.findByDocenteAndProposta(1L, 1L);

        assertTrue(saved.isPresent());
    }

}*/

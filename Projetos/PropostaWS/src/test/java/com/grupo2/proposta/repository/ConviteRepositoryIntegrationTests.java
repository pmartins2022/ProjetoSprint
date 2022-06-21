package com.grupo2.proposta.repository;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.model.EstadoConvite;
import com.grupo2.proposta.repository.jpa.ConviteJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
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

        Optional<Convite> saved = repository.findById(conviteJPA.getId().getIdproposta(), conviteID.getIdaluno());

        assertEquals(saved, convite);
    }

    @Test
    public void shouldNotFindByID()
    {
        Optional<Convite> saved = repository.findByDocenteAndProposta(1L, 1L);

        assertTrue(saved.isPresent());
    }
    /*
    public Optional<Convite> findById(ConviteID id)
    {
        Optional<ConviteJPA> id1 = jpaRepository.findById(id);

        if (id1.isPresent())
        {
            return Optional.of(mapper.toModel(id1.get()));
        }
        return Optional.empty();
    }

    public void invalidarConvites(Long propostaID, Long alunoId)
    {
        List<Convite> list = jpaRepository.findByIdIdproposta(propostaID).stream().map(mapper::toModel).toList();

        for (Convite convite : list)
        {
            if (!convite.getIdAluno().equals(alunoId))
            {
                convite.setEstado(EstadoConvite.RECUSADO);
                jpaRepository.save(mapper.toJPA(convite));
            }
        }
    }

    public void invalidarTodosConvites(Long propostaID)
    {
        List<Convite> list = jpaRepository.findByIdIdproposta(propostaID).stream().map(mapper::toModel).toList();

        for (Convite convite : list)
        {
            convite.setEstado(EstadoConvite.RECUSADO);
            jpaRepository.save(mapper.toJPA(convite));
        }
    }

    public void atualizar(Convite convite)
    {
        ConviteJPA conviteJPA = mapper.toJPA(convite);

        jpaRepository.deleteById(conviteJPA.getId());

        jpaRepository.save(conviteJPA);
    }
     */
}
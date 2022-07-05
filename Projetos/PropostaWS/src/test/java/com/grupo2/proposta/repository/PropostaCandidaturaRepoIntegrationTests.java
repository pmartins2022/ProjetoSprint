package com.grupo2.proposta.repository;

import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import com.grupo2.proposta.repository.jpa.PropostaCandidaturaJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class PropostaCandidaturaRepoIntegrationTests
{
    @Autowired
    private PropostaCandidaturaRepo repository;

    @Autowired
    private PropostaCandidaturaJPARepository candidaturaJPARepository;

    @Test
    public void shouldReturnTrue_AlunoInscrito()
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(1L, 1L);

        PropostaCandidaturaJPA candidaturaJPA = new PropostaCandidaturaJPA(id, EstadoCandidatura.PENDENTE);

        candidaturaJPARepository.save(candidaturaJPA);

        boolean isInscrito = repository.isIncrito(1L, 1L);

        assertTrue(isInscrito);
    }

    @Test
    public void shouldReturnFalse_AlunoNotInscrito()
    {
        boolean isInscrito = repository.isIncrito(1L, 1L);

        assertFalse(isInscrito);
    }

    @Test
    public void shouldReturnTrue_AlunoInscritoInAny()
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(1L, 1L);

        PropostaCandidaturaJPA candidaturaJPA = new PropostaCandidaturaJPA(id, EstadoCandidatura.PENDENTE);

        candidaturaJPARepository.save(candidaturaJPA);

        boolean isInscrito = repository.isAlunoInscrito( 1L);

        assertTrue(isInscrito);
    }

    @Test
    public void shouldReturnFalse_AlunoInscritoInAny()
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(1L, 2L);

        PropostaCandidaturaJPA candidaturaJPA = new PropostaCandidaturaJPA(id, EstadoCandidatura.PENDENTE);

        candidaturaJPARepository.save(candidaturaJPA);

        boolean isInscrito = repository.isAlunoInscrito(1L);

        assertFalse(isInscrito);
    }

    @Test
    public void shouldCreatePropostaCandidatura()
    {
        PropostaCandidatura propostaCandidatura = repository.createAndSave( 1L, 1L);

        assertTrue(propostaCandidatura.getEstado().equals(EstadoCandidatura.PENDENTE));
    }

    @Test
    public void shouldUpdateAndSave()
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(1L, 1L);
        PropostaCandidaturaJPA candidaturaJPA = new PropostaCandidaturaJPA(id, EstadoCandidatura.PENDENTE);
        candidaturaJPARepository.save(candidaturaJPA);

        PropostaCandidatura candidatura = new PropostaCandidatura(1L, 1L, EstadoCandidatura.PENDENTE);

        PropostaCandidatura propostaCandidatura = repository.updateAndSave(candidatura);

        assertTrue(propostaCandidatura.getEstado().equals(EstadoCandidatura.PENDENTE));
    }

    @Test
    public void shouldFindByID()
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(1L, 1L);
        PropostaCandidaturaJPA candidaturaJPA = new PropostaCandidaturaJPA(id, EstadoCandidatura.PENDENTE);
        candidaturaJPARepository.save(candidaturaJPA);

        Optional<PropostaCandidatura> propostaCandidatura = repository.findByID(id);

        assertTrue(propostaCandidatura.isPresent());
    }

    @Test
    public void shouldNotFindByID_Empty()
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(1L, 1L);

        Optional<PropostaCandidatura> propostaCandidatura = repository.findByID(id);

        assertFalse(propostaCandidatura.isPresent());
    }

    @Test
    public void shouldInvalidateCandidaturas()
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(1L, 1L);
        PropostaCandidaturaID id1 = new PropostaCandidaturaID(1L, 2L);
        PropostaCandidaturaJPA candidaturaJPA = new PropostaCandidaturaJPA(id, EstadoCandidatura.PENDENTE);
        PropostaCandidaturaJPA candidaturaJPA1 = new PropostaCandidaturaJPA(id1, EstadoCandidatura.PENDENTE);
        PropostaCandidaturaJPA save = candidaturaJPARepository.save(candidaturaJPA);
        candidaturaJPARepository.save(candidaturaJPA1);

        repository.invalidarCandidaturas(save.getId().getidproposta(), save.getId().getIdaluno());

        Optional<PropostaCandidaturaJPA> propostaCandidaturaJPA1 = candidaturaJPARepository.findById(id1);

        assertTrue(propostaCandidaturaJPA1.get().getEstado().equals(EstadoCandidatura.REJEITADO));
    }

    @Test
    public void shouldInvalidateAllCandidaturas()
    {
        PropostaCandidaturaID id = new PropostaCandidaturaID(1L, 1L);
        PropostaCandidaturaID id1 = new PropostaCandidaturaID(1L, 2L);
        PropostaCandidaturaJPA candidaturaJPA = new PropostaCandidaturaJPA(id, EstadoCandidatura.PENDENTE);
        PropostaCandidaturaJPA candidaturaJPA1 = new PropostaCandidaturaJPA(id1, EstadoCandidatura.PENDENTE);
        PropostaCandidaturaJPA save = candidaturaJPARepository.save(candidaturaJPA);
        candidaturaJPARepository.save(candidaturaJPA1);

        repository.invalidarTodasCandidaturas(save.getId().getidproposta());

        Optional<PropostaCandidaturaJPA> propostaCandidaturaJPA = candidaturaJPARepository.findById(id);
        Optional<PropostaCandidaturaJPA> propostaCandidaturaJPA1 = candidaturaJPARepository.findById(id1);

        assertTrue(propostaCandidaturaJPA.get().getEstado().equals(EstadoCandidatura.REJEITADO) &&
                propostaCandidaturaJPA1.get().getEstado().equals(EstadoCandidatura.REJEITADO));
    }
}
package com.grupo2.proposta.repository;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.jpa.mapper.ConviteJPAMapper;
import com.grupo2.proposta.jpa.mapper.PropostaCandidaturaJPAMapper;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import com.grupo2.proposta.repository.jpa.ConviteJPARepository;
import com.grupo2.proposta.repository.jpa.PropostaCandidaturaJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class PropostaCandidaturaRepoUnitTest
{
    @MockBean
    private PropostaCandidaturaJPARepository jpaRepository;

    @MockBean
    private PropostaCandidaturaJPAMapper mapper;

    @InjectMocks
    private PropostaCandidaturaRepo repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    public void shouldIsInscrito()
    {
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);
        PropostaCandidaturaJPA jpa = mock(PropostaCandidaturaJPA.class);

        when(jpaRepository.findById(propostaCandidaturaID)).thenReturn(Optional.of(jpa));
        when(mapper.toModel(jpa)).thenReturn(propostaCandidatura);

        Optional<PropostaCandidatura> save = repository.isIncrito(propostaCandidaturaID.getIdAluno(), propostaCandidaturaID.getIdProposta());

        assertTrue(id.isPresent());
        assertEquals(convite, id.get());
    }*/

    /*@Test
    public void shouldCreateAndSavePropostaCandidatura()
    {
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaJPA jpa = mock(PropostaCandidaturaJPA.class);

        when(jpaRepository.save(jpa)).thenReturn(jpa);

        when(mapper.toJPA(propostaCandidatura)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(propostaCandidatura);

        PropostaCandidatura save = repository.createAndSave(propostaCandidatura.getIdProposta(), propostaCandidatura.getIdProposta());

        assertEquals(propostaCandidatura, save);
    }*/

    @Test
    public void shouldFindById()
    {
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);
        PropostaCandidaturaJPA jpa = mock(PropostaCandidaturaJPA.class);

        when(jpaRepository.findById(propostaCandidaturaID)).thenReturn(Optional.of(jpa));
        when(mapper.toModel(jpa)).thenReturn(propostaCandidatura);

        Optional<PropostaCandidatura> id = repository.findByID(propostaCandidaturaID);

        assertTrue(id.isPresent());
        assertEquals(propostaCandidatura, id.get());
    }

    /*@Test
    public void shouldNotFindById()
    {
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);

        when(jpaRepository.findById(propostaCandidaturaID)).thenReturn(Optional.empty());

        Optional<PropostaCandidatura> id = repository.findByID(propostaCandidaturaID);

        assertTrue(id.isEmpty());
    }*/
}
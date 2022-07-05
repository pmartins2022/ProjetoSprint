package com.grupo2.proposta.repository;

import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.jpa.mapper.PropostaCandidaturaJPAMapper;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import com.grupo2.proposta.model.factory.PropostaCandidaturaIDFactory;
import com.grupo2.proposta.model.factory.PropostaCandidaturaJPAFactory;
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
    private PropostaCandidaturaIDFactory propostaCandidaturaIDFactory;

    @MockBean
    private PropostaCandidaturaJPAMapper mapper;

    @MockBean
    private PropostaCandidaturaJPAFactory propostaCandidaturaJPAFactory;

    @InjectMocks
    private PropostaCandidaturaRepo repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldIsInscrito()
    {
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);
        PropostaCandidaturaJPA jpa = mock(PropostaCandidaturaJPA.class);

        when(jpaRepository.findById(propostaCandidaturaID)).thenReturn(Optional.of(jpa));
        when(mapper.toModel(jpa)).thenReturn(propostaCandidatura);
        when(propostaCandidaturaIDFactory.create(propostaCandidaturaID.getidproposta(), propostaCandidaturaID.getIdaluno())).thenReturn(propostaCandidaturaID);

        boolean save = repository.isIncrito(propostaCandidaturaID.getidproposta(), propostaCandidaturaID.getIdaluno());

        assertTrue(save);
    }

    @Test
    public void shouldNotIsInscrito()
    {
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);
        PropostaCandidaturaJPA jpa = mock(PropostaCandidaturaJPA.class);

        when(jpaRepository.findById(propostaCandidaturaID)).thenReturn(Optional.of(jpa));
        when(mapper.toModel(jpa)).thenReturn(propostaCandidatura);

        boolean save = repository.isIncrito(propostaCandidaturaID.getidproposta(), propostaCandidaturaID.getIdaluno());

        assertFalse(save);
    }

    @Test
    public void shouldCreateAndSavePropostaCandidatura()
    {
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaJPA jpa = mock(PropostaCandidaturaJPA.class);

        when(propostaCandidatura.getIdProposta()).thenReturn(1L);
        when(propostaCandidatura.getIdAluno()).thenReturn(1L);

        when(propostaCandidaturaIDFactory.create(propostaCandidatura.getIdProposta(), propostaCandidatura.getIdAluno()))
                .thenReturn(propostaCandidaturaID);
        when(propostaCandidaturaJPAFactory.create(propostaCandidaturaID,EstadoCandidatura.PENDENTE)).thenReturn(jpa);

        when(jpaRepository.save(jpa)).thenReturn(jpa);

        when(mapper.toModel(jpa)).thenReturn(propostaCandidatura);


        PropostaCandidatura save = repository.createAndSave(propostaCandidatura.getIdProposta(), propostaCandidatura.getIdAluno());

        assertEquals(propostaCandidatura, save);
    }

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

    @Test
    public void shouldNotFindById()
    {
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);

        when(jpaRepository.findById(propostaCandidaturaID)).thenReturn(Optional.empty());

        Optional<PropostaCandidatura> id = repository.findByID(propostaCandidaturaID);

        assertTrue(id.isEmpty());
    }
}
package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.jpa.AnoLetivoJPA;
import com.grupo2.edicaouc.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.repository.jpa.AnoLetivoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AnoLetivoRepositoryUnitTests
{
    @MockBean
    private AnoLetivoJPARepository jpaRepository;

    @MockBean
    private AnoLetivoJPAMapper mapper;

    @InjectMocks
    private AnoLetivoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAndSaveAnoLetivo()
    {
        AnoLetivo al = mock(AnoLetivo.class);
        AnoLetivoJPA jpa = mock(AnoLetivoJPA.class);

        when(repository.findById("id")).thenReturn(Optional.of(al));

        when(mapper.toJpa(al)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(al);
        when(jpaRepository.save(jpa)).thenReturn(jpa);

        AnoLetivo saveAnoLetivo = repository.createAndSaveAnoLetivo(al);

        assertNotNull(saveAnoLetivo);
        assertEquals(al, saveAnoLetivo);
    }

    @Test
    public void shouldFindById()
    {
        AnoLetivo al = mock(AnoLetivo.class);
        AnoLetivoJPA jpa = mock(AnoLetivoJPA.class);

        when(mapper.toJpa(al)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(al);
        when(jpaRepository.findById("id")).thenReturn(Optional.of(jpa));

        Optional<AnoLetivo> anoLetivo = repository.findById("id");

        assertTrue(anoLetivo.isPresent());
        assertEquals(al, anoLetivo.get());
    }

    @Test
    public void shouldNotFindById()
    {
        when(jpaRepository.findById("id")).thenReturn(Optional.empty());

        Optional<AnoLetivo> anoLetivo = repository.findById("id");

        assertTrue(anoLetivo.isEmpty());
    }

    @Test
    public void shouldFindAll()
    {
        AnoLetivoJPA al = mock(AnoLetivoJPA.class);
        AnoLetivo al2 = mock(AnoLetivo.class);

        List<AnoLetivoJPA> list = List.of(al,al,al);

        when(jpaRepository.findAll()).thenReturn(list);
        when(mapper.toModel(al)).thenReturn(al2);

        List<AnoLetivo> all = repository.findAll();

        assertNotNull(all);
        assertEquals(3, all.size());
    }

    @Test
    public void shouldNotFindAll()
    {
        when(jpaRepository.findAll()).thenReturn(List.of());

        List<AnoLetivo> all = repository.findAll();

        assertNotNull(all);
        assertEquals(0, all.size());
    }
}
package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.jpa.UnidadeCurricularJPA;
import com.grupo2.edicaouc.jpa.mapper.UnidadeCurricularJPAMapper;
import com.grupo2.edicaouc.model.UnidadeCurricular;
import com.grupo2.edicaouc.repository.jpa.UnidadeCurricularJPARepository;
import com.sun.istack.Interned;
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
class UnidadeCurricularRepositoryUnitTests
{
    @MockBean
    private UnidadeCurricularJPARepository jpaRepository;

    @MockBean
    private UnidadeCurricularJPAMapper mapper;

    @InjectMocks
    private UnidadeCurricularRepository unidadeCurricularRepository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindBySigla()
    {
        UnidadeCurricularJPA jpa = mock(UnidadeCurricularJPA.class);

        UnidadeCurricular unidadeCurricular = mock(UnidadeCurricular.class);

        when(jpaRepository.findBySigla("UC1")).thenReturn(Optional.of(jpa));
        when(mapper.toModel(jpa)).thenReturn(unidadeCurricular);

        Optional<UnidadeCurricular> sigla = unidadeCurricularRepository.findBySigla("UC1");

        assertTrue(sigla.isPresent());
        assertEquals(unidadeCurricular, sigla.get());
    }

    @Test
    public void shouldNotFindBySigla()
    {
        when(jpaRepository.findBySigla("UC1")).thenReturn(Optional.empty());

        Optional<UnidadeCurricular> sigla = unidadeCurricularRepository.findBySigla("UC1");

        assertTrue(sigla.isEmpty());
    }

    @Test
    public void shouldSaveUnidadeCurricular()
    {
        UnidadeCurricular unidadeCurricular = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA jpa = mock(UnidadeCurricularJPA.class);

        when(unidadeCurricular.getSigla()).thenReturn("UC1");

        when(jpaRepository.findById(unidadeCurricular.getSigla())).thenReturn(Optional.empty());

        when(mapper.toJPA(unidadeCurricular)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(unidadeCurricular);
        when(jpaRepository.save(jpa)).thenReturn(jpa);

        UnidadeCurricular saved = unidadeCurricularRepository.saveUnidadeCurricular(unidadeCurricular);

        assertEquals(unidadeCurricular, saved);
    }

    @Test
    public void shouldNotSaveUnidadeCurricular()
    {
        UnidadeCurricular unidadeCurricular = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA jpa = mock(UnidadeCurricularJPA.class);

        when(unidadeCurricular.getSigla()).thenReturn("UC1");

        when(jpaRepository.findById(unidadeCurricular.getSigla())).thenReturn(Optional.of(jpa));

        assertThrows(ErroGeralException.class,()->unidadeCurricularRepository.saveUnidadeCurricular(unidadeCurricular));
    }

    @Test
    public void shouldUpdateUnidadeCurricular()
    {
        UnidadeCurricular unidadeCurricular = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA jpa = mock(UnidadeCurricularJPA.class);

        when(unidadeCurricular.getSigla()).thenReturn("UC1");
        when(jpaRepository.findById(unidadeCurricular.getSigla())).thenReturn(Optional.of(jpa));

        when(mapper.toJPA(unidadeCurricular)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(unidadeCurricular);

        when(jpaRepository.save(jpa)).thenReturn(jpa);

        UnidadeCurricular curricular = unidadeCurricularRepository.updateUnidadeCurricular(unidadeCurricular);
    }

    @Test
    public void shouldNotUpdateUnidadeCurricular()
    {
        UnidadeCurricular unidadeCurricular = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA jpa = mock(UnidadeCurricularJPA.class);

        when(unidadeCurricular.getSigla()).thenReturn("UC1");
        when(jpaRepository.findById(unidadeCurricular.getSigla())).thenReturn(Optional.empty());

        assertThrows(ErroGeralException.class,()->unidadeCurricularRepository.updateUnidadeCurricular(unidadeCurricular));
    }

    @Test
    public void shouldFindAll()
    {
        UnidadeCurricular unidadeCurricular = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA jpa = mock(UnidadeCurricularJPA.class);

        when(jpaRepository.findAll()).thenReturn(List.of(jpa));

        when(mapper.toModel(jpa)).thenReturn(unidadeCurricular);

        List<UnidadeCurricular> unidades = unidadeCurricularRepository.findAll();

        assertEquals(1, unidades.size());
    }
}
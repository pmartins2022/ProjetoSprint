package com.grupo2.uc.repository;

import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.exception.OptionalVazioException;
import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import com.grupo2.uc.jpa.mapper.UnidadeCurricularJPAMapper;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.repository.jpa.UnidadeCurricularJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
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
    UnidadeCurricularJPARepository jpaRepository;

    @MockBean
    UnidadeCurricularJPAMapper mapper;

    @InjectMocks
    UnidadeCurricularRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidUnidadeCurricular()
    {
        UnidadeCurricular ucMOCK = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA ucJPAMOCK = mock(UnidadeCurricularJPA.class);

        when(ucMOCK.getSigla()).thenReturn("Português");
        when(ucJPAMOCK.getSigla()).thenReturn("Português");

        when(jpaRepository.findById("Português")).thenReturn(Optional.empty());

        when(mapper.toModel(ucJPAMOCK)).thenReturn(ucMOCK);
        when(mapper.toJPA(ucMOCK)).thenReturn(ucJPAMOCK);

        when(jpaRepository.save(ucJPAMOCK)).thenReturn(ucJPAMOCK);

        UnidadeCurricular saveAnoLetivo = repository.saveUnidadeCurricular(ucMOCK);

        assertEquals(saveAnoLetivo, ucMOCK);
    }

    @Test
    public void shouldNotCreateUnidadeCurricular_Exists()
    {
        UnidadeCurricular ucMOCK = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA ucMOCKJPA = mock(UnidadeCurricularJPA.class);

        when(ucMOCK.getSigla()).thenReturn("Português");

        when(mapper.toModel(ucMOCKJPA)).thenReturn(ucMOCK);
        when(mapper.toJPA(ucMOCK)).thenReturn(ucMOCKJPA);

        when(jpaRepository.save(ucMOCKJPA)).thenReturn(ucMOCKJPA);

        when(jpaRepository.findById("Português")).thenReturn(Optional.of(ucMOCKJPA));

        assertThrows(ErroGeralException.class, () -> repository.saveUnidadeCurricular(ucMOCK));
    }

    @Test
    public void shouldFindAllUnidadeCurricular()
    {
        UnidadeCurricular ucMOCK = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA ucMOCKJPA = mock(UnidadeCurricularJPA.class);

        List<UnidadeCurricular> mockList = List.of(ucMOCK, ucMOCK, ucMOCK);
        List<UnidadeCurricularJPA> mockListJpa = List.of(ucMOCKJPA, ucMOCKJPA, ucMOCKJPA);

        when(jpaRepository.findAll()).thenReturn(mockListJpa);

        when(mapper.toModel(ucMOCKJPA)).thenReturn(ucMOCK);

        List<UnidadeCurricular> all = repository.findAll();

        assertEquals(all, mockList);
    }


    @Test
    public void shouldFindEmptyListUnidadeCurricular()
    {
        UnidadeCurricular ucMOCK = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA ucMOCKJPA = mock(UnidadeCurricularJPA.class);

        List<UnidadeCurricular> mockList = List.of();
        List<UnidadeCurricularJPA> mockListJpa = List.of();

        when(jpaRepository.findAll()).thenReturn(mockListJpa);

        when(mapper.toModel(ucMOCKJPA)).thenReturn(ucMOCK);

        List<UnidadeCurricular> all = repository.findAll();

        assertEquals(all, mockList);
    }

    @Test
    public void shouldFindUnidadeCurricular_Exists()
    {
        UnidadeCurricular ucMOCK = mock(UnidadeCurricular.class);
        UnidadeCurricularJPA ucMOCKJPA = mock(UnidadeCurricularJPA.class);

        when(jpaRepository.findBySigla("Português")).thenReturn(Optional.of(ucMOCKJPA));
        when(mapper.toModel(ucMOCKJPA)).thenReturn(ucMOCK);

        Optional<UnidadeCurricular> found = repository.findBySigla("Português");

        assertEquals(ucMOCK, found.get());
    }

    @Test
    public void shouldNotFindUnidadeCurricular_NotExists()
    {
        when(jpaRepository.findById("Português")).thenReturn(Optional.empty());

        Optional<UnidadeCurricular> found = repository.findBySigla("Português");

        assertEquals(Optional.empty(), found);
    }

    @Test
    public void shouldUpdateUnidadeCurricular()
    {
        UnidadeCurricularJPA ucJPAMOCK = mock(UnidadeCurricularJPA.class);
        when(ucJPAMOCK.getSigla()).thenReturn("PTA");
        when(ucJPAMOCK.getDenominacao()).thenReturn("Português Avançado");

        UnidadeCurricular ucMOCK = mock(UnidadeCurricular.class);
        when(ucMOCK.getSigla()).thenReturn("PTA");
        when(ucMOCK.getDenominacao()).thenReturn("Português SUPER Avançado");

        when(jpaRepository.save(ucJPAMOCK)).thenReturn(ucJPAMOCK);
        when(mapper.toModel(ucJPAMOCK)).thenReturn(ucMOCK);

        UnidadeCurricular ucUpdated = repository.updateUnidadeCurricular("PTA", "Português Avançado");

        assertEquals(ucMOCK.getDenominacao(), ucUpdated.getDenominacao());
    }

    /*
        public UnidadeCurricular updateUnidadeCurricular(String sigla, String denominacao)
    {
        UnidadeCurricularJPA jpa = new UnidadeCurricularJPA(sigla, denominacao);

        UnidadeCurricularJPA saved = jpaRepository.save(jpa);

        UnidadeCurricular ucSaved = mapper.toModel(saved);
        return ucSaved;
    }
     */
}
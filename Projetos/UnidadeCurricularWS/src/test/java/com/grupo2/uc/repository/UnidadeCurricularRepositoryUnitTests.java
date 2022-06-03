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
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
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
        UnidadeCurricular ucMock = mock(UnidadeCurricular.class);

        when(ucMock.getSigla()).thenReturn("PTXX");
        when(ucMock.getDenominacao()).thenReturn("PORTUGUES_AVANCADO");

        UnidadeCurricularJPA jpaMock = mock(UnidadeCurricularJPA.class);

        when(jpaRepository.findById(ucMock.getSigla())).thenReturn(Optional.of(jpaMock));
        when(jpaRepository.save(jpaMock)).thenReturn(jpaMock);
        when(mapper.toModel(jpaMock)).thenReturn(ucMock);
        when(mapper.toJPA(ucMock)).thenReturn(jpaMock);

        UnidadeCurricular unidadeCurricular = repository.updateUnidadeCurricular(ucMock);

        assertEquals(ucMock,unidadeCurricular);
    }

    @Test
    public void shouldNotUpdateUnidadeCurricular_DiffSiglaThatNotExists()
    {
        UnidadeCurricular ucMOCKToUpdate = mock(UnidadeCurricular.class);
        when(ucMOCKToUpdate.getSigla()).thenReturn("PTAB");

        when(jpaRepository.findById(ucMOCKToUpdate.getSigla())).thenReturn(Optional.empty());

        assertThrows(ErroGeralException.class,()-> repository.updateUnidadeCurricular(ucMOCKToUpdate));
    }
}
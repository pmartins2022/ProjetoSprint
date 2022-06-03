package com.grupo2.anoLetivoWS.repository;

import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.jpa.AnoLetivoJPA;
import com.grupo2.anoLetivoWS.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.repository.jpa.AnoLetivoJPARepository;
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
class AnoLetivoRepositoryUnitTests
{
    @MockBean
    AnoLetivoJPARepository jpaRepository;

    @MockBean
    AnoLetivoJPAMapper mapper;

    @InjectMocks
    AnoLetivoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidAnoLetivo()
    {
        AnoLetivo mockAno = mock(AnoLetivo.class);
        AnoLetivoJPA mockAnoJpa = mock(AnoLetivoJPA.class);

        when(mockAno.getSigla()).thenReturn("2000-2001");
        when(mockAnoJpa.getSigla()).thenReturn("2000-2001");

        when(jpaRepository.findById("2000-2001")).thenReturn(Optional.empty());

        when(mapper.toModel(mockAnoJpa)).thenReturn(mockAno);
        when(mapper.toJpa(mockAno)).thenReturn(mockAnoJpa);

        when(jpaRepository.save(mockAnoJpa)).thenReturn(mockAnoJpa);

        AnoLetivo saveAnoLetivo = repository.createAndSaveAnoLetivo(mockAno);

        assertEquals(saveAnoLetivo, mockAno);
    }

    @Test
    public void shouldNotCreateAnoLetivo_Exists()
    {
        AnoLetivo anoLetivoMock = mock(AnoLetivo.class);
        AnoLetivoJPA anoLetivoJpa = mock(AnoLetivoJPA.class);

        when(anoLetivoMock.getSigla()).thenReturn("2009-2010");

        //when(repository.findById())
        when(jpaRepository.findById("2009-2010")).thenReturn(Optional.of(anoLetivoJpa));
        when(jpaRepository.save(anoLetivoJpa)).thenReturn(anoLetivoJpa);
        when(mapper.toModel(anoLetivoJpa)).thenReturn(anoLetivoMock);
        when(mapper.toJpa(anoLetivoMock)).thenReturn(anoLetivoJpa);

        assertThrows(ErroGeralException.class,()->repository.createAndSaveAnoLetivo(anoLetivoMock));
    }

    @Test
    public void shouldFindAllAnoLetivo()
    {
        AnoLetivo anoLetivoMock = mock(AnoLetivo.class);
        AnoLetivoJPA anoLetivoMockJpa = mock(AnoLetivoJPA.class);

        List<AnoLetivo> mockList = List.of(anoLetivoMock,anoLetivoMock,anoLetivoMock);
        List<AnoLetivoJPA> mockListJpa = List.of(anoLetivoMockJpa,anoLetivoMockJpa,anoLetivoMockJpa);

        when(jpaRepository.findAll()).thenReturn(mockListJpa);

        when(mapper.toModel(anoLetivoMockJpa)).thenReturn(anoLetivoMock);

        List<AnoLetivo> all = repository.findAll();

        assertEquals(all,mockList);
    }


    @Test
    public void shouldFindEmptyListAnoLetivo()
    {
        AnoLetivo anoLetivoMock = mock(AnoLetivo.class);
        AnoLetivoJPA anoLetivoMockJpa = mock(AnoLetivoJPA.class);

        List<AnoLetivo> mockList = List.of();
        List<AnoLetivoJPA> mockListJpa = List.of();

        when(jpaRepository.findAll()).thenReturn(mockListJpa);

        when(mapper.toModel(anoLetivoMockJpa)).thenReturn(anoLetivoMock);

        List<AnoLetivo> all = repository.findAll();

        assertEquals(all,mockList);
    }

    @Test
    public void shouldFindAnoLetivo_Exists()
    {
        AnoLetivoJPA anoLetivoJPA = mock(AnoLetivoJPA.class);
        AnoLetivo anoLetivo = mock(AnoLetivo.class);

        when(jpaRepository.findById("2001-2002")).thenReturn(Optional.of(anoLetivoJPA));
        when(mapper.toModel(anoLetivoJPA)).thenReturn(anoLetivo);

        Optional<AnoLetivo> saved = repository.findById("2001-2002");

        assertEquals(anoLetivo, saved.get());
    }

    @Test
    public void shouldNotFindAnoLetivo_NotExists()
    {
        when(jpaRepository.findById("2001-2002")).thenReturn(Optional.empty());

        Optional<AnoLetivo> saved = repository.findById("2001-2002");

        assertEquals(Optional.empty(), saved);
    }
}
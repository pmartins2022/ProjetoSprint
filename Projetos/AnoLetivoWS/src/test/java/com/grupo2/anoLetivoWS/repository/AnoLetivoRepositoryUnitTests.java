package com.grupo2.anoLetivoWS.repository;

import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.jpa.AnoLetivoJpa;
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
        AnoLetivoJpa mockAnoJpa = mock(AnoLetivoJpa.class);

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
        AnoLetivoJpa anoLetivoJpa = mock(AnoLetivoJpa.class);

        when(anoLetivoMock.getSigla()).thenReturn("2000-2001");

        when(mapper.toModel(anoLetivoJpa)).thenReturn(anoLetivoMock);
        when(mapper.toJpa(anoLetivoMock)).thenReturn(anoLetivoJpa);

        when(jpaRepository.save(anoLetivoJpa)).thenReturn(anoLetivoJpa);

        when(jpaRepository.findById("2000-2001")).thenReturn(Optional.of(anoLetivoJpa));

        assertThrows(ErroGeralException.class,()->repository.createAndSaveAnoLetivo(anoLetivoMock));
    }

    @Test
    public void shouldFindAllAnoLetivo()
    {
        AnoLetivo anoLetivoMock = mock(AnoLetivo.class);
        AnoLetivoJpa anoLetivoMockJpa = mock(AnoLetivoJpa.class);

        List<AnoLetivo> mockList = List.of(anoLetivoMock,anoLetivoMock,anoLetivoMock);
        List<AnoLetivoJpa> mockListJpa = List.of(anoLetivoMockJpa,anoLetivoMockJpa,anoLetivoMockJpa);

        when(jpaRepository.findAll()).thenReturn(mockListJpa);

        when(mapper.toModel(anoLetivoMockJpa)).thenReturn(anoLetivoMock);

        List<AnoLetivo> all = repository.findAll();

        assertEquals(all,mockList);
    }


    @Test
    public void shouldFindEmptyListAnoLetivo()
    {
        AnoLetivo anoLetivoMock = mock(AnoLetivo.class);
        AnoLetivoJpa anoLetivoMockJpa = mock(AnoLetivoJpa.class);

        List<AnoLetivo> mockList = List.of();
        List<AnoLetivoJpa> mockListJpa = List.of();

        when(jpaRepository.findAll()).thenReturn(mockListJpa);

        when(mapper.toModel(anoLetivoMockJpa)).thenReturn(anoLetivoMock);

        List<AnoLetivo> all = repository.findAll();

        assertEquals(all,mockList);
    }
}
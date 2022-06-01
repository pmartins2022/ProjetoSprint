package com.grupo2.anoLetivoWS.service;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.dto.mapper.AnoLetivoDTOMapper;
import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.repository.AnoLetivoRepository;
import com.grupo2.anoLetivoWS.repository.jpa.AnoLetivoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnoLetivoServiceUnitTests
{
    @MockBean
    AnoLetivoRepository repository;

    @MockBean
    AnoLetivoDTOMapper mapper;

    @InjectMocks
    AnoLetivoService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidAnoLetivo()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);
        AnoLetivo anoLetivoMOCK = mock(AnoLetivo.class);

        when(mapper.toModel(anoLetivoDTOMOCK)).thenReturn(anoLetivoMOCK);
        when(repository.createAndSaveAnoLetivo(anoLetivoMOCK)).thenReturn(anoLetivoMOCK);
        when(mapper.toDTO(anoLetivoMOCK)).thenReturn(anoLetivoDTOMOCK);

        AnoLetivoDTO saved = service.createAndSaveAnoLetivo(anoLetivoDTOMOCK);

        assertEquals(anoLetivoDTOMOCK, saved);
    }

    @Test
    public void shouldNotCreateAnoLetivo_Exist()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);
        AnoLetivo anoLetivoMOCK = mock(AnoLetivo.class);

        when(mapper.toModel(anoLetivoDTOMOCK)).thenReturn(anoLetivoMOCK);

        when(repository.createAndSaveAnoLetivo(anoLetivoMOCK)).thenThrow(ErroGeralException.class);

        assertThrows(ErroGeralException.class,()->service.createAndSaveAnoLetivo(anoLetivoDTOMOCK));
    }

    @Test
    public void shouldFindAllAnoLetivo()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);
        AnoLetivo anoLetivoMOCK = mock(AnoLetivo.class);

        List<AnoLetivoDTO> anoLetivoListDTO = List.of(anoLetivoDTOMOCK,anoLetivoDTOMOCK,anoLetivoDTOMOCK);

        List<AnoLetivo> anoLetivoList = List.of(anoLetivoMOCK,anoLetivoMOCK,anoLetivoMOCK);

        when(repository.findAll()).thenReturn(anoLetivoList);
        when(mapper.toDTO(anoLetivoMOCK)).thenReturn(anoLetivoDTOMOCK);

        List<AnoLetivoDTO> all = service.findAll();

        assertEquals(all,anoLetivoListDTO);
    }

    @Test
    public void shouldNotFindAnoLetivo()
    {
        List<AnoLetivo> anoLetivoList = List.of();

        when(repository.findAll()).thenReturn(anoLetivoList);

        List<AnoLetivoDTO> all = service.findAll();

        assertEquals(0,all.size());
    }


}
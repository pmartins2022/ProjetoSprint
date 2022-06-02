package com.grupo2.anoLetivoWS.dto.mapper;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.jpa.AnoLetivoJPA;
import com.grupo2.anoLetivoWS.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.model.factory.AnoLetivoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AnoLetivoDTOMapperUnitTests
{

    @MockBean
    AnoLetivoFactory factory;

    @InjectMocks
    AnoLetivoDTOMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldConvertValidAnoLetivo_ValidAtributtes()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);
        AnoLetivo anoLetivoMOCK = mock(AnoLetivo.class);
        when(anoLetivoDTOMOCK.getSigla()).thenReturn("2001-2002");
        when(anoLetivoMOCK.getSigla()).thenReturn("2001-2002");

        when(factory.createAnoLetivo(anoLetivoDTOMOCK.getSigla())).thenReturn(anoLetivoMOCK);

        AnoLetivo anoLetivo = mapper.toModel(anoLetivoDTOMOCK);

        assertEquals(anoLetivo.getSigla(), anoLetivoMOCK.getSigla());
    }

    @Test
    public void shouldNotConvertValidAnoLetivo_InvalidAtributtes()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);
        when(factory.createAnoLetivo(anoLetivoDTOMOCK.getSigla())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(anoLetivoDTOMOCK));
    }

    @Test
    public void shouldConvertValidAnoLetivoDTO_ValidAtributtes()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);
        AnoLetivo anoLetivoMOCK = mock(AnoLetivo.class);
        when(anoLetivoDTOMOCK.getSigla()).thenReturn("2001-2002");
        when(anoLetivoMOCK.getSigla()).thenReturn("2001-2002");

        AnoLetivoDTO anoLetivoDTO = mapper.toDTO(anoLetivoMOCK);

        assertEquals(anoLetivoDTO.getSigla(), anoLetivoMOCK.getSigla());
    }

    @Test
    public void shouldNotConvertValidAnoLetivoDTO_InvalidAtributtes()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);

        when(factory.createAnoLetivo(anoLetivoDTOMOCK.getSigla())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(anoLetivoDTOMOCK));
    }
}
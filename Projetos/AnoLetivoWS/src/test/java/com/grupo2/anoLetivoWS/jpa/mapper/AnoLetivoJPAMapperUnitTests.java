package com.grupo2.anoLetivoWS.jpa.mapper;


import com.grupo2.anoLetivoWS.controller.AnoLetivoController;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.jpa.AnoLetivoJPA;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.model.factory.AnoLetivoFactory;
import com.grupo2.anoLetivoWS.service.AnoLetivoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AnoLetivoJPAMapperUnitTests
{
    @MockBean
    AnoLetivoFactory factory;

    @InjectMocks
    AnoLetivoJPAMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldConvertValidAnoLetivo_ValidAtributtes()
    {
        AnoLetivoJPA anoLetivoJPAMOCK = mock(AnoLetivoJPA.class);
        AnoLetivo anoLetivoMOCK = mock(AnoLetivo.class);
        when(anoLetivoJPAMOCK.getSigla()).thenReturn("2001-2002");
        when(anoLetivoMOCK.getSigla()).thenReturn("2001-2002");

        when(factory.createAnoLetivo(anoLetivoJPAMOCK.getSigla())).thenReturn(anoLetivoMOCK);

        AnoLetivo anoLetivo = mapper.toModel(anoLetivoJPAMOCK);

        assertEquals(anoLetivo.getSigla(), anoLetivoMOCK.getSigla());
    }

    @Test
    public void shouldNotConvertValidAnoLetivo_InvalidAtributtes()
    {
        AnoLetivoJPA anoLetivoJPAMOCK = mock(AnoLetivoJPA.class);
        when(factory.createAnoLetivo(anoLetivoJPAMOCK.getSigla())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(anoLetivoJPAMOCK));
    }

    @Test
    public void shouldConvertValidAnoLetivoDTO_ValidAtributtes()
    {
        AnoLetivoJPA anoLetivoJPAMOCK = mock(AnoLetivoJPA.class);
        AnoLetivo anoLetivoMOCK = mock(AnoLetivo.class);
        when(anoLetivoJPAMOCK.getSigla()).thenReturn("2001-2002");
        when(anoLetivoMOCK.getSigla()).thenReturn("2001-2002");

        AnoLetivoJPA anoLetivoJPA = mapper.toJpa(anoLetivoMOCK);

        assertEquals(anoLetivoJPA.getSigla(), anoLetivoMOCK.getSigla());
    }

    @Test
    public void shouldNotConvertValidAnoLetivoDTO_InvalidAtributtes()
    {
        AnoLetivoJPA anoLetivoJPAMOCK = mock(AnoLetivoJPA.class);

        when(factory.createAnoLetivo(anoLetivoJPAMOCK.getSigla())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(anoLetivoJPAMOCK));
    }
}
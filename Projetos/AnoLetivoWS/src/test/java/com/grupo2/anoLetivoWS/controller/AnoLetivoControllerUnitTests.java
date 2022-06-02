package com.grupo2.anoLetivoWS.controller;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.exception.ListaVaziaException;
import com.grupo2.anoLetivoWS.exception.OptionalVazioException;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.service.AnoLetivoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AnoLetivoControllerUnitTests
{
    @MockBean
    AnoLetivoService service;

    @InjectMocks
    AnoLetivoController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldCreateValidAnoLetivo()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);

        when(service.createAndSaveAnoLetivo(anoLetivoDTOMOCK)).thenReturn(anoLetivoDTOMOCK);

        ResponseEntity<AnoLetivoDTO> responseEntity = controller.createAndSaveAnoLetivo(anoLetivoDTOMOCK);

        assertEquals( 200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void shouldNotCreateValidAnoLetivo_Invalid()
    {

        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);

        when(service.createAndSaveAnoLetivo(anoLetivoDTOMOCK)).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class,()->controller.createAndSaveAnoLetivo(anoLetivoDTOMOCK));
    }

    @Test
    public void shouldNotCreateValidAnoLetivo_Exists()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);

        when(service.createAndSaveAnoLetivo(anoLetivoDTOMOCK)).thenThrow(ErroGeralException.class);

        assertThrows(ErroGeralException.class,()->controller.createAndSaveAnoLetivo(anoLetivoDTOMOCK));
    }

    @Test
    public void shouldFindAllAnoLetivo()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);

        List<AnoLetivoDTO> anoLetivoListDTO = List.of(anoLetivoDTOMOCK,anoLetivoDTOMOCK,anoLetivoDTOMOCK);

        when(service.findAll()).thenReturn(anoLetivoListDTO);

        ResponseEntity<Object> responseEntity = controller.listAllAnoLetivo();

        assertEquals(responseEntity.getStatusCodeValue(),200);
    }

    @Test
    public void shouldNotFindListAnoLetivo_Empty()
    {
        when(service.findAll()).thenThrow(ListaVaziaException.class);

        assertThrows(ListaVaziaException.class,()->controller.listAllAnoLetivo());
    }

    @Test
    public void shouldFindAnoLetivo_Exists()
    {
        AnoLetivoDTO anoLetivoDTO = mock(AnoLetivoDTO.class);

        when(service.findByID("2001-2002")).thenReturn(Optional.of(anoLetivoDTO));

        ResponseEntity<Object> responseEntity = controller.findBySigla("2001-2002");

        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void shouldNotFindAnoLetivo_NotExists()
    {
        when(service.findByID("2001-2002")).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, ()-> controller.findBySigla("2001-2002"));
    }

}
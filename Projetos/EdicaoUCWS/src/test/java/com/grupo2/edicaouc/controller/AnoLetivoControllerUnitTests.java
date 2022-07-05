package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.service.AnoLetivoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void shouldCreateAnoLetivo()
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");
    }

    @Test
    public void shouldCreateValidAnoLetivo()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);

        when(service.createAndSaveAnoLetivo(anoLetivoDTOMOCK)).thenReturn(anoLetivoDTOMOCK);

        ResponseEntity<AnoLetivoDTO> responseEntity = controller.createAndSaveAnoLetivo(anoLetivoDTOMOCK);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
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

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
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

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAnoLetivo_NotExists()
    {
        when(service.findByID("2001-2002")).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, ()-> controller.findBySigla("2001-2002"));
    }

}
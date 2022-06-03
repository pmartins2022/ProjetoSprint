package com.grupo2.anoLetivoWS.controller;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.exception.ListaVaziaException;
import com.grupo2.anoLetivoWS.exception.OptionalVazioException;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.service.AnoLetivoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AnoLetivoControllerIntegrationTests
{
    @Autowired
    private AnoLetivoController controller;

    @Test
    public void shouldCreateValidAnoLetivo()
    {
        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO("2001-2002");

        ResponseEntity<AnoLetivoDTO> responseEntity = controller.createAndSaveAnoLetivo(anoLetivoDTO);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateValidAnoLetivo_Exists()
    {
        AnoLetivoDTO anoLetivoDTOSaved = new AnoLetivoDTO("2001-2002");

        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO("2001-2002");

        controller.createAndSaveAnoLetivo(anoLetivoDTOSaved);

        assertThrows(ErroGeralException.class, () -> controller.createAndSaveAnoLetivo(anoLetivoDTO));
    }

    @Test
    public void shouldFindAllAnoLetivo()
    {
        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO("2001-2002");
        AnoLetivoDTO anoLetivoDTO1 = new AnoLetivoDTO("2002-2003");

        controller.createAndSaveAnoLetivo(anoLetivoDTO1);
        controller.createAndSaveAnoLetivo(anoLetivoDTO);

        ResponseEntity<Object> responseEntity = controller.listAllAnoLetivo();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindListAnoLetivo_Empty()
    {
        assertThrows(ListaVaziaException.class, () -> controller.listAllAnoLetivo());
    }

    @Test
    public void shouldFindAnoLetivo_Exists()
    {
        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO("2001-2002");

        controller.createAndSaveAnoLetivo(anoLetivoDTO);

        ResponseEntity<Object> responseEntity = controller.findBySigla("2001-2002");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAnoLetivo_NotExists()
    {
        assertThrows(OptionalVazioException.class, () -> controller.findBySigla("2001-2002"));
    }
}
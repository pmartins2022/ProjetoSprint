package com.grupo2.anoLetivoWS.service;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class AnoLetivoServiceIntegrationTest
{
    @Autowired
    private AnoLetivoService service;

    @Test
    public void shouldCreateAnoLetivo()
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");
        AnoLetivoDTO anoLetivo = service.createAndSaveAnoLetivo(dto);

        assertEquals(dto, anoLetivo);
    }

    @Test
    public void shouldNotCreateAnoLetivo_Exists()
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");
        service.createAndSaveAnoLetivo(dto);

        assertThrows(ErroGeralException.class, () -> service.createAndSaveAnoLetivo(dto));
    }

    @Test
    public void shouldFindAll()
    {
        AnoLetivoDTO ano = new AnoLetivoDTO("2000-2001");
        AnoLetivoDTO ano1 = new AnoLetivoDTO("2002-2003");
        AnoLetivoDTO ano2 = new AnoLetivoDTO("2003-2004");

        service.createAndSaveAnoLetivo(ano);
        service.createAndSaveAnoLetivo(ano1);
        service.createAndSaveAnoLetivo(ano2);

        List<AnoLetivoDTO> letivos = service.findAll();

        assertEquals(3, letivos.size());
    }

    @Test
    public void shouldNotFindAll()
    {
        List<AnoLetivoDTO> letivos = service.findAll();

        assertEquals(0, letivos.size());
    }
}
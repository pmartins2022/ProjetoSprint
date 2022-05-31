package com.grupo2.anoLetivoWS.controller;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.anoLetivoWS.repository.AnoLetivoRepository;
import com.grupo2.anoLetivoWS.repository.jpa.AnoLetivoJPARepository;
import com.grupo2.anoLetivoWS.service.AnoLetivoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AnoLetivoControllerUnitTest
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
    public void shoulddCreateValidAnoLetivo()
    {
        AnoLetivoDTO anoLetivoDTOMOCK = mock(AnoLetivoDTO.class);

        when(service.createAndSaveAnoLetivo(anoLetivoDTOMOCK)).thenReturn(anoLetivoDTOMOCK);

        ResponseEntity<AnoLetivoDTO> responseEntity = controller.createAndSaveAnoLetivo(anoLetivoDTOMOCK);

        assertEquals( 200, responseEntity.getStatusCodeValue());
    }
}
package com.example.javafx.controller;

import com.example.javafx.controller.admin.AnoLetivoController;
import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.service.AnoLetivoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnoLetivoControllerUnitTests
{
    @MockBean
    private AnoLetivoService service;

    @InjectMocks
    private AnoLetivoController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAnoLetivo()
    {
        AnoLetivoDTO dtoMOCK = mock(AnoLetivoDTO.class);

        when(service.createAnoLetivo(dtoMOCK)).thenReturn(dtoMOCK);

        AnoLetivoDTO anoLetivo = controller.createAnoLetivo(dtoMOCK);

        assertEquals(anoLetivo, dtoMOCK);
    }

    @Test
    public void shouldNotCreateAnoLetivo()
    {
        AnoLetivoDTO dtoMOCK = mock(AnoLetivoDTO.class);

        when(service.createAnoLetivo(dtoMOCK)).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, ()-> controller.createAnoLetivo(dtoMOCK));
    }
}
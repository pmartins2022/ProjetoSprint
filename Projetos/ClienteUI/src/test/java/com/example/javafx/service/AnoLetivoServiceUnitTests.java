package com.example.javafx.service;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.AnoLetivoRestRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.transaction.Transactional;

@SpringBootTest
class AnoLetivoServiceUnitTests
{
    @MockBean
    AnoLetivoRestRepo restRepository;

    @InjectMocks
    AnoLetivoService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAnoLetivo()
    {
        AnoLetivoDTO dtoMOCK = mock(AnoLetivoDTO.class);

        when(restRepository.createAnoLetivo(dtoMOCK)).thenReturn(dtoMOCK);

        AnoLetivoDTO anoLetivo = service.createAnoLetivo(dtoMOCK);

        assertEquals(anoLetivo, dtoMOCK);
    }

    @Test
    public void shouldNotCreateAnoLetivo_Invalid()
    {
        AnoLetivoDTO dtoMOCK = mock(AnoLetivoDTO.class);

        when(restRepository.createAnoLetivo(dtoMOCK)).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, () -> service.createAnoLetivo(dtoMOCK));
    }

}
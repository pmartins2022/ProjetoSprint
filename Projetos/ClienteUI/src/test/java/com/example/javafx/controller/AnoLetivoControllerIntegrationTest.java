package com.example.javafx.controller;

import com.example.javafx.dto.AnoLetivoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnoLetivoControllerIntegrationTest
{
    @Autowired
    AnoLetivoController controller;

    @Test
    public void shouldCreateAnoLetivo_valid()
    {
        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO("2000-2001");

        AnoLetivoDTO save = controller.createAnoLetivo(anoLetivoDTO);

        AnoLetivoDTO expected = new AnoLetivoDTO("2000-2001");

        assertEquals(expected,save);
    }

}
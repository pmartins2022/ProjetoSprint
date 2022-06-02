package com.example.javafx.controller;

import com.example.javafx.service.AnoLetivoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnoLetivoControllerTest
{
    @MockBean
    private AnoLetivoService service;

    @InjectMocks
    private AnoLetivoController controller;

    @BeforeEach
    void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testService()
    {
        assertNotNull(service);
    }

    @Test
    public void testController()
    {
        assertNotNull(controller);
    }

}
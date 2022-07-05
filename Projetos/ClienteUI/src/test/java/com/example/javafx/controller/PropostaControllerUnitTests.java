package com.example.javafx.controller;

import com.example.javafx.dto.OrganizacaoDTO;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.factory.PropostaDTOFactory;
import com.example.javafx.service.PropostaService;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaControllerUnitTests
{
    @MockBean
    private PropostaService service;

    @MockBean
    private PropostaDTOFactory factory;

    @InjectMocks
    private PropostaController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateProposta()
    {
        PropostaDTO dto = mock(PropostaDTO.class);

        when(factory.create(0L, 0L ,"AAA","AAA","AAA",0L)).thenReturn(dto);

        when(service.saveProposta(dto)).thenReturn(dto);

        PropostaDTO proposta = controller.createProposta(0L, 0L, 0L, "AAA","AAA","AAA");

        assertEquals(dto, proposta);
    }



}
package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.EdicaoMomentoAvaliacaoDTO;
import com.grupo2.edicaouc.dto.MomentoAvaliacaoDTO;
import com.grupo2.edicaouc.service.MomentoAvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class MomentoAvaliacaoControllerUnitTests
{

    @MockBean
    MomentoAvaliacaoService service;

    @InjectMocks
    MomentoAvaliacaoController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateMomento()
    {
        MomentoAvaliacaoDTO momentoMOCK = mock(MomentoAvaliacaoDTO.class);

        when(service.createAndSaveMomentoAvaliacao(momentoMOCK)).thenReturn(momentoMOCK);

        HttpServletRequest req = mock(HttpServletRequest.class);

        ResponseEntity<Object> momentoAvaliacaoDTO = controller.criarMomento(momentoMOCK,req);

        assertEquals(momentoAvaliacaoDTO.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldCreateEdicaoMomentoAvaliacao()
    {
        EdicaoMomentoAvaliacaoDTO edicaMOCK = mock(EdicaoMomentoAvaliacaoDTO.class);

        when(service.createAndSaveEdicaoMomentoAvaliacao(edicaMOCK)).thenReturn(edicaMOCK);

        ResponseEntity<Object> momentoAvaliacaoDTO = controller.criarEdicaoMomento(edicaMOCK);

        assertEquals(momentoAvaliacaoDTO.getStatusCode(), HttpStatus.CREATED);
    }
}
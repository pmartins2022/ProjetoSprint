package com.grupo2.utilizadores.controller;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.OptionalVazioException;
import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;
import com.grupo2.utilizadores.service.UtilizadorService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UtilizadorControllerUnitTest {

    @MockBean
    UtilizadorService service;

    @InjectMocks
    UtilizadorController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindUtilizador_Exists() {
        UtilizadorDTO utilizadorDTOMOCK = mock(UtilizadorDTO.class);

        when(service.findByID(1L)).thenReturn(Optional.of(utilizadorDTOMOCK));

        ResponseEntity<Object> responseEntity = controller.findByID(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindUtilizador_Empty()
    {
        when(service.findByID(1L)).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class,()->controller.findByID(1L));
    }


}
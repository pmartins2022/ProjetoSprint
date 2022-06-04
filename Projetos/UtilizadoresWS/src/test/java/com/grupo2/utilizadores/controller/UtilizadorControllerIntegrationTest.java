package com.grupo2.utilizadores.controller;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.OptionalVazioException;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.repository.jpa.UtilizadorJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UtilizadorControllerIntegrationTest {

    @Autowired
    private UtilizadorController controller;

    @Autowired
    UtilizadorJPARepository repository;



    @Test
    public void shouldNotFindUtilizador_NotExists()
    {
        assertThrows(OptionalVazioException.class, ()-> controller.findByID(2L));
    }
}
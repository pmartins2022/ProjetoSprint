package com.grupo2.uc.controller;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.exception.ListaVaziaException;
import com.grupo2.uc.exception.OptionalVazioException;
import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.model.UnidadeCurricular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UnidadeCurricularControllerIntegrationTests
{

    @Autowired
    private UnidadeCurricularController controller;

    @Test
    public void shouldCreateValidUnidadeCurricular()
    {
        UnidadeCurricularDTO ucDTO = new UnidadeCurricularDTO("PTA", "Português Avançado");

        ResponseEntity<UnidadeCurricularDTO> responseEntity = controller.createAndSaveUnidadeCurricular(ucDTO);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateUnidadeCurricular_InvalidAtributes()
    {
        UnidadeCurricularDTO ucDTO = new UnidadeCurricularDTO("PTA1", "Português Avançado");

        assertThrows(ValidacaoInvalidaException.class, () -> controller.createAndSaveUnidadeCurricular(ucDTO));
    }

    @Test
    public void shouldNotCreateUnidadeCurricular_Exists()
    {
        UnidadeCurricularDTO ucDTO = new UnidadeCurricularDTO("PTA", "Português Avançado");

        controller.createAndSaveUnidadeCurricular(ucDTO);

        assertThrows(ErroGeralException.class, () -> controller.createAndSaveUnidadeCurricular(ucDTO));
    }

    @Test
    public void shouldFindAllUnidadeCurricular()
    {
        UnidadeCurricularDTO uc =  new UnidadeCurricularDTO("PTA", "Português Avançado");
        UnidadeCurricularDTO uc1 = new UnidadeCurricularDTO("PTAB", "Português Avançado B");

        controller.createAndSaveUnidadeCurricular(uc);
        controller.createAndSaveUnidadeCurricular(uc1);

        ResponseEntity<Object> responseEntity = controller.listAll();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldFindEmptyListUnidadeCurricular()
    {
        assertThrows(ListaVaziaException.class, ()-> controller.listAll());
    }

    @Test
    public void shouldFindUnidadeCurricular_Exists()
    {
        UnidadeCurricularDTO uc =  new UnidadeCurricularDTO("PTA", "Português Avançado");

        controller.createAndSaveUnidadeCurricular(uc);

        ResponseEntity<Object> responseEntity = controller.findByID("PTA");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindUnidadeCurricular_NotExists()
    {
        assertThrows(OptionalVazioException.class, ()-> controller.findByID("PTA"));
    }

    @Test
    public void shouldUpdateUnidadeCurricular()
    {
        UnidadeCurricularDTO ucOld = new UnidadeCurricularDTO("PTA", "Português Deprecated");

        controller.createAndSaveUnidadeCurricular(ucOld);

        ResponseEntity<Object> responseEntity = controller.updateDenominacao("PTA", "Português Avançado");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotUpdateUnidadeCurricular_DiffSigla()
    {
        UnidadeCurricularDTO ucOld = new UnidadeCurricularDTO("PTA", "Português Deprecated");

        controller.createAndSaveUnidadeCurricular(ucOld);

        assertThrows(OptionalVazioException.class, ()-> controller.updateDenominacao("PTAB", "Português Avançado"));
    }
}
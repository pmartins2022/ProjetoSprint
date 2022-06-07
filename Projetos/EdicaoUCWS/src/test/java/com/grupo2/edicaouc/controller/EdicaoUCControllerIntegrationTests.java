package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import com.grupo2.edicaouc.service.AnoLetivoService;
import com.grupo2.edicaouc.service.UnidadeCurricularService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class EdicaoUCControllerIntegrationTests
{
    @Autowired
    EdicaoUCController controller;
    @Autowired
    EdicaoUCJpaRepository jpaRepository;

    @Autowired
    AnoLetivoService anoLetivoService;

    @Autowired
    UnidadeCurricularService unidadeCurricularService;

    @Test
    public void shouldFindByIdEdicaoUC()
    {
        //arrange
        EdicaoUCJPA edicaoUCJPA = new EdicaoUCJPA("PTA", "2001-2002");

        EdicaoUCJPA save = jpaRepository.save(edicaoUCJPA);
        //act
        ResponseEntity<Object> responseEntity = controller.findById(save.getId());
        //assert
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindByIdEdicaoUC_NotExists()
    {
        assertThrows(OptionalVazioException.class, () -> controller.findById(99L));
    }

    @Test
    public void create()
    {
        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO("2001-2002");
        UnidadeCurricularDTO unidadeCurricularDTO = new UnidadeCurricularDTO("PTA","PAWIFJIAOS OASIJFAOIFAOASF");

        AnoLetivoDTO andSaveAnoLetivo = anoLetivoService.createAndSaveAnoLetivo(anoLetivoDTO);
        System.out.println(anoLetivoService.findByID(andSaveAnoLetivo.getSigla()).isPresent());
        UnidadeCurricularDTO andSaveUnidadeCurricular = unidadeCurricularService.createAndSaveUnidadeCurricular(unidadeCurricularDTO);
        System.out.println(unidadeCurricularService.findBySigla(andSaveUnidadeCurricular.getSigla()).isPresent());
        EdicaoUCDTO edicaoUCDTO = new EdicaoUCDTO("PTA", "2001-2002");

        ResponseEntity<Object> edicao = controller.createEdicao(edicaoUCDTO);

        assertEquals(edicao.getStatusCode(), HttpStatus.CREATED);
    }
}
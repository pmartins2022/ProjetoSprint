package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.exception.BaseDadosException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class EdicaoUCControllerIntegrationTests
{
    @Autowired
    EdicaoUCController controller;
    @Autowired
    EdicaoUCJpaRepository jpaRepository;

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
        assertThrows(OptionalVazioException.class, () -> controller.findById(1L));
    }
}
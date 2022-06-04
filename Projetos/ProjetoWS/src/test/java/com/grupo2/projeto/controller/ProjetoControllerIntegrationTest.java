package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.exception.ErroGeralException;
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
class ProjetoControllerIntegrationTest
{
    @Autowired
    private ProjetoController controller;

    @Test
    public void shouldCreateProject()
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(1L,2L,2L);

        ResponseEntity<ProjetoDTO> responseEntity = controller.createProjeto(projetoDTO);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }


    @Test
    public void shouldFindProject_Exists()
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(1L,2L,2L);

        controller.createProjeto(projetoDTO);

        ResponseEntity<ProjetoDTO> responseEntity = controller.findById(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void shouldNotFindProject_NotExists()
    {
        assertThrows(ErroGeralException.class, ()-> controller.findById(2L));
    }

}
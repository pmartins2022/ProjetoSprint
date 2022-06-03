package com.grupo2.utilizadores.service;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.model.TipoUtilizador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UtilizadorServiceIntegrationTests
{
    @Autowired
    private UtilizadorService utilizadorService;

    @Test
    public void shouldCreateAndSave_Valid()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"testName","testSName","testEmail@email.com", TipoUtilizador.ALUNO);
        UtilizadorDTO save = utilizadorService.createAndSave(utilizadorDTO);

        assertEquals(utilizadorDTO,save);
    }

    @Test
    public void shouldFindById_Exists()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L, "testName","testSName","testEmail@email.com", TipoUtilizador.ALUNO);
        UtilizadorDTO save = utilizadorService.createAndSave(utilizadorDTO);

        Optional<UtilizadorDTO> dto = utilizadorService.findByID(save.getId());

        assertTrue(dto.isPresent());

        assertEquals(save,dto.get());
    }

    @Test
    public void shouldNotFindById_DoesNotExist()
    {
        Optional<UtilizadorDTO> dto = utilizadorService.findByID(99L);

        assertTrue(dto.isEmpty());
    }

}
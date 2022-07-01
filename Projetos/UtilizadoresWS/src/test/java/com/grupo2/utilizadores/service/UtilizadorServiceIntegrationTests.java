package com.grupo2.utilizadores.service;

import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.OptionalVazioException;
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
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"testName","testSName","testEmail@email.com","aabb","aabb", TipoUtilizador.ALUNO);
        UtilizadorDTO save = utilizadorService.registar(utilizadorDTO);

        assertEquals(utilizadorDTO,save);
    }

    @Test
    public void shouldFindById_Exists()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"testName","testSName","testEmail@email.com","aabb","aabb", TipoUtilizador.ALUNO);
        UtilizadorDTO save = utilizadorService.registar(utilizadorDTO);

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

    @Test
    public void shouldRegistar()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"testName","testSName","email@mail.com","aabb","aabb", TipoUtilizador.ALUNO);

        UtilizadorDTO save = utilizadorService.registar(utilizadorDTO);

        assertEquals(utilizadorDTO,save);
    }

    @Test
    public void shouldNotRegister_duplicateUserName()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"testName","testSName","email@mail.com","aabb","aabb", TipoUtilizador.ALUNO);

        utilizadorService.registar(utilizadorDTO);

        assertThrows(IllegalArgumentException.class, () -> utilizadorService.registar(utilizadorDTO));
    }

    @Test
    public void shouldFindByUsername()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"testName","testSName","email@mail.com","aabb","aabb", TipoUtilizador.ALUNO);

        utilizadorService.registar(utilizadorDTO);

        Optional<UtilizadorAuthDTO> dto = utilizadorService.findByUsername(utilizadorDTO.getUsername());

        assertTrue(dto.isPresent());
    }

    @Test
    public void shouldNotFindByUsername_DoesNotExist()
    {
        Optional<UtilizadorAuthDTO> dto = utilizadorService.findByUsername("test");

        assertTrue(dto.isEmpty());
    }

    @Test
    public void shouldReturnTrue_correctRole()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"testName","testSName","email@mail.com","aabb","aabb", TipoUtilizador.ALUNO);

        UtilizadorDTO registar = utilizadorService.registar(utilizadorDTO);

        assertTrue(utilizadorService.isRole(TipoUtilizador.ALUNO.toString(), registar.getId()));
    }

    @Test
    public void shouldThrow_invalidUser()
    {
        assertThrows(OptionalVazioException.class,()->utilizadorService.isRole(TipoUtilizador.ALUNO.toString(), 99L));
    }

    @Test
    public void shouldReturnFalse_incorrectRole()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"testName","testSName","email@mail.com","aabb","aabb", TipoUtilizador.ALUNO);

        UtilizadorDTO registar = utilizadorService.registar(utilizadorDTO);

        assertFalse(utilizadorService.isRole(TipoUtilizador.ADMINISTRADOR.toString(), registar.getId()));
    }


}
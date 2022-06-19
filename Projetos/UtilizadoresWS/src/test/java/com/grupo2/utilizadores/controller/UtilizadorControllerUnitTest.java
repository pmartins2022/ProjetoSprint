package com.grupo2.utilizadores.controller;

import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.ListaVaziaException;
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

    @Test
    public void shouldRegistarUtilizador() {
        UtilizadorDTO utilizadorDTOMOCK = mock(UtilizadorDTO.class);

        when(service.registar(utilizadorDTOMOCK)).thenReturn(utilizadorDTOMOCK);

        ResponseEntity<?> responseEntity = controller.registar(utilizadorDTOMOCK);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

   /* @Test
    public void shouldNotRegistarUtilizador() {
        UtilizadorDTO utilizadorDTOMOCK = mock(UtilizadorDTO.class);

        when(service.registar(utilizadorDTOMOCK)).thenReturn(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,()->controller.registar(utilizadorDTOMOCK));
    }*/

    @Test
    public void shouldFindByUsername() {
        UtilizadorAuthDTO utilizadorDTOMOCK = mock(UtilizadorAuthDTO.class);

        when(service.findByUsername("nome")).thenReturn(Optional.of(utilizadorDTOMOCK));

        ResponseEntity<UtilizadorAuthDTO> responseEntity = controller.findByUsername("nome");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindByUsername() {

        when(service.findByUsername("nome")).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class,()->controller.findByUsername("nome"));
    }

    @Test
    public void shouldFindAll()
    {
        UtilizadorDTO utilizadorDTOMOCK = mock(UtilizadorDTO.class);
        List<UtilizadorDTO> list = List.of(utilizadorDTOMOCK, utilizadorDTOMOCK, utilizadorDTOMOCK);

        when(service.findAll()).thenReturn(list);

        ResponseEntity<Object> responseEntity = controller.listAll();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAllByUCCode_NotExists()
    {
        when(service.findAll()).thenReturn(List.of());

        assertThrows(ListaVaziaException.class, () -> controller.listAll());
    }

   /* @Test
    public void shouldIsRole()
    {
        UtilizadorDTO utilizadorDTOMOCK = mock(UtilizadorDTO.class);

        when(service.isRole("papel", 1L)).thenReturn();

        ResponseEntity<Boolean> responseEntity = controller.isRole("papel", 1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }*/

   /* @Test
    public void shouldNotIsRole()
    {
        UtilizadorDTO utilizadorDTOMOCK = mock(UtilizadorDTO.class);

        when(service.isRole("papel", 1L)).thenReturn(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class,()->controller.isRole(utilizadorDTOMOCK));
    }*/
}
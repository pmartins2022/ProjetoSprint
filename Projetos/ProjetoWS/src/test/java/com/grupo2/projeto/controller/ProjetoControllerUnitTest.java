package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.filter.ProjetoFilterBody;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.service.ProjetoFilterService;
import com.grupo2.projeto.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class ProjetoControllerUnitTest
{
    @MockBean
    ProjetoService service;

    @MockBean
    ProjetoFilterService filterService;

    @InjectMocks
    ProjetoController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidProjeto()
    {
        ProjetoDTO projetoDTOMOCK = mock(ProjetoDTO.class);

        doNothing().when(service).createAndSave(projetoDTOMOCK);

        ResponseEntity<Object> responseEntity = controller.createProjeto(projetoDTOMOCK);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldFindProjeto_Exists()
    {
        ProjetoDTO projetoDTOMOCK = mock(ProjetoDTO.class);

        when(service.findById(1L)).thenReturn(Optional.of(projetoDTOMOCK));

        ResponseEntity<ProjetoDTO> responseEntity = controller.findById(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindProjeto_NotExists()
    {
        when(service.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ErroGeralException.class, ()-> controller.findById(2L));
    }

    @Test
    public void shouldFindAllByOrientadorID() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        ProjetoDTO dto = mock(ProjetoDTO.class);

        List<ProjetoDTO> lst = List.of(dto,dto,dto);

        when(service.findAllByOrientadorId(1L)).thenReturn(lst);

        ResponseEntity<List<ProjetoDTO>> rest = controller.findAllByOrientadorID(1L);

        assertEquals(rest.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldProjetoFilter()
    {
        ProjetoDTO dto = mock(ProjetoDTO.class);

        List<ProjetoDTO> lst = List.of(dto,dto,dto);

        ProjetoFilterBody body = mock(ProjetoFilterBody.class);

        when(filterService.filtrarProjetos(body)).thenReturn(lst);

        ResponseEntity<Object> entity = controller.projetoFilter(body);

        assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotProjetoFilter()
    {
        ProjetoDTO dto = mock(ProjetoDTO.class);

        List<ProjetoDTO> lst = List.of();

        ProjetoFilterBody body = mock(ProjetoFilterBody.class);

        when(filterService.filtrarProjetos(body)).thenReturn(lst);

       assertThrows(OptionalVazioException.class,()->controller.projetoFilter(body));
    }
}
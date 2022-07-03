package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.EdicaoUCAlunoDTO;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.UtilizadorDTO;
import com.grupo2.edicaouc.exception.*;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import com.grupo2.edicaouc.security.LoginContext;
import com.grupo2.edicaouc.service.EdicaoUCService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class EdicaoUCControllerUnitTests
{
    @MockBean
    EdicaoUCService service;

    @InjectMocks
    EdicaoUCController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateEdicaoUC()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        HttpServletRequest req = mock(HttpServletRequest.class);

        when(service.createEdicaoUC(dtoMOCK)).thenReturn(dtoMOCK);

        ResponseEntity<Object> responseEntity = controller.createEdicao(dtoMOCK,req);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateEdicaoUC_Exists()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        HttpServletRequest req = mock(HttpServletRequest.class);

        when(service.createEdicaoUC(dtoMOCK)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, ()-> controller.createEdicao(dtoMOCK,req));
    }

    @Test
    public void shouldNotCreateEdicaoUC_InvalidAttributes()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        HttpServletRequest req = mock(HttpServletRequest.class);

        when(service.createEdicaoUC(dtoMOCK)).thenThrow(BaseDadosException.class);

        assertThrows(BaseDadosException.class, ()-> controller.createEdicao(dtoMOCK,req));
    }

    @Test
    public void shouldFindAllByUCCode()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);
        List<EdicaoUCDTO> list = List.of(dtoMOCK, dtoMOCK, dtoMOCK);

        when(service.findAllEdicaoByUCCode("PTA")).thenReturn(list);

        ResponseEntity<List<EdicaoUCDTO>> responseEntity = controller.listEdicaoByUCCode("PTA");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAllByUCCode_NotExists()
    {
        when(service.findAllEdicaoByUCCode("PTA")).thenReturn(List.of());

        assertThrows(ListaVaziaException.class, () -> controller.listEdicaoByUCCode("PTA"));
    }


    @Test
    public void shouldFindByIdEdicaoUC()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.findById(1L)).thenReturn(Optional.of(dtoMOCK));

        ResponseEntity<Object> responseEntity = controller.findById(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindByIdEdicaoUC_NotExists()
    {
        when(service.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class, () -> controller.findById(1L));
    }

    @Test
    public void shouldFindAll()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.findAllEdicaoUC()).thenReturn(List.of(dtoMOCK, dtoMOCK));

        ResponseEntity<List<EdicaoUCDTO>> responseEntity = controller.listAll();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAll_Empty()
    {
        when(service.findAllEdicaoUC()).thenThrow(ListaVaziaException.class);

        assertThrows(ListaVaziaException.class, () -> controller.listAll());
    }

    @Test
    public void shouldFindAllByRucID()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.findByRucID(1L)).thenReturn(List.of(dtoMOCK, dtoMOCK));

        ResponseEntity<List<EdicaoUCDTO>> responseEntity = controller.findByRucID(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAllByRucID_Empty()
    {
        when(service.findByRucID(1L)).thenThrow(ListaVaziaException.class);

        assertThrows(ListaVaziaException.class, () -> controller.findByRucID(1L));
    }

    @Test
    public void shouldFindAllByRucIDActive()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.findByRucIDAndEstadoEdicaoUC(1L,  EstadoEdicaoUC.ATIVA)).thenReturn(Optional.ofNullable(dtoMOCK));

        ResponseEntity<EdicaoUCDTO> responseEntity = controller.findByRucIDAndEstadoEdicaoUC(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAllByRucIDActive_Empty()
    {
        when(service.findByRucIDAndEstadoEdicaoUC(1L,  EstadoEdicaoUC.ATIVA)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, () -> controller.findByRucIDAndEstadoEdicaoUC(1L));
    }

    @Test
    public void shouldReturnEdicaoUCAluno()
    {
        EdicaoUCAlunoDTO dtoMOCK = mock(EdicaoUCAlunoDTO.class);
        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);

        when(service.addAlunoEdicaoUC(utilizadorDTO, 1L, 1L)).thenReturn(dtoMOCK);

        ResponseEntity<?> responseEntity = controller.addAlunoEdicaoUC(1L, 1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldDeactivateEdicaoUC() throws ValidationException
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.desativarEdicao(1L)).thenReturn(dtoMOCK);

        ResponseEntity<?> responseEntity = controller.desativarEdicao(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotDeactivateEdicaoUC_Invalid()
    {
        when(service.desativarEdicao(1L)).thenThrow(ErroGeralException.class);

        assertThrows(ErroGeralException.class, ()-> controller.desativarEdicao(1L));
    }

    @Test
    public void shouldNotDeactivateEdicaoUC_InvalidIDs()
    {
        when(service.desativarEdicao(1L)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, ()-> controller.desativarEdicao(1L));
    }
}
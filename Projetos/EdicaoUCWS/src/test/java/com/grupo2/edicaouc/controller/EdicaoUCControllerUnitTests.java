package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.exception.BaseDadosException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.service.EdicaoUCService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        when(service.createEdicaoUC(dtoMOCK)).thenReturn(dtoMOCK);

        ResponseEntity<Object> responseEntity = controller.createEdicao(dtoMOCK);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateEdicaoUC_Exists()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.createEdicaoUC(dtoMOCK)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, ()-> controller.createEdicao(dtoMOCK));
    }

    @Test
    public void shouldNotCreateEdicaoUC_InvalidAttributes()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);

        when(service.createEdicaoUC(dtoMOCK)).thenThrow(BaseDadosException.class);

        assertThrows(BaseDadosException.class, ()-> controller.createEdicao(dtoMOCK));
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

        when(service.findByRucIDAndEstadoEdicaoUC(1L, 1L)).thenReturn(Optional.ofNullable(dtoMOCK));

        ResponseEntity<EdicaoUCDTO> responseEntity = controller.findByRucIDAndEstadoEdicaoUC(1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldNotFindAllByRucIDActive_Empty()
    {
        when(service.findByRucIDAndEstadoEdicaoUC(1L, 1L)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class, () -> controller.findByRucIDAndEstadoEdicaoUC(1L));
    }

    /*
    @Test
    public void shouldReturnEdicaoUCAluno()
    {
        EdicaoUCDTO dtoMOCK = mock(EdicaoUCDTO.class);
        //PRECISO DO LOGIN CONTEXT
        when(service.addAlunoEdicaoUC(1L, 1L)).thenReturn(Optional.ofNullable(dtoMOCK));

        ResponseEntity<?> responseEntity = controller.addAlunoEdicaoUC(1L, 1L);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    _____________________________________________________________________________________________________________________
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/inscrever/{edicaoUCID}")
    public ResponseEntity<?> addAlunoEdicaoUC(@PathVariable("edicaoUCID") Long edicaoUCID, @RequestParam("alunoID") Long alunoID)
    {
        UtilizadorDTO dto = LoginContext.getCurrent();
        try
        {
            EdicaoUCAlunoDTO edicaoUCAlunoDTO = service.addAlunoEdicaoUC(dto, edicaoUCID, alunoID);
            return new ResponseEntity<>(edicaoUCAlunoDTO, HttpStatus.OK);
        } catch (ErrorDetail e)
        {
            throw e;
        } catch (ErroGeralException e)
        {
            throw e;
        }
    }


    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/ativarEdicao/{edicaoUCID}")
    public ResponseEntity<Object> ativarEdicao(@PathVariable("edicaoUCID") Long edicaoUCID)
    {
        UtilizadorDTO utilizador = LoginContext.getCurrent();

        try
        {
            EdicaoUCDTO dto = service.activarEdicao(utilizador.getId(), edicaoUCID);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        }
        catch (ErroGeralException e)
        {
            throw new ErroGeralException(e.getMessage());
        } catch (ValidationException e)
        {
            throw new RuntimeException(e);
        }

    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/desativarEdicao/{edicaoUCID}")
    public ResponseEntity<Object> desativarEdicao(@PathVariable("edicaoUCID") Long edicaoUCID)
    {
        try
        {
            EdicaoUCDTO dto = service.desativarEdicao(edicaoUCID);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        } catch (ErroGeralException e)
        {
            throw e;
        }
    }
     */
}
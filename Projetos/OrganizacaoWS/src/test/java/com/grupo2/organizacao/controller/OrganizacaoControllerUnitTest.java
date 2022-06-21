package com.grupo2.organizacao.controller;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.exception.ListaVaziaException;
import com.grupo2.organizacao.exception.OptionalVazioException;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.service.OrganizacaoService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class OrganizacaoControllerUnitTest {
    @MockBean
    OrganizacaoService service;

    @InjectMocks
    OrganizacaoController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAllOrganizacao()
    {
        OrganizacaoDTO organizacaoDTOMOCK = mock(OrganizacaoDTO.class);

        List<OrganizacaoDTO> organizacaoListDTO = List.of(organizacaoDTOMOCK,organizacaoDTOMOCK,organizacaoDTOMOCK);

        when(service.findAll()).thenReturn(organizacaoListDTO);


        ResponseEntity<List<OrganizacaoDTO>> responseEntity = controller.findAll();

        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }
    @Test
    public void shouldGetOrganizacaoByID_Exists()
    {
        OrganizacaoDTO organizacaoDTOMOCK = mock(OrganizacaoDTO.class);

        when(service.findByID(2L)).thenReturn(Optional.of(organizacaoDTOMOCK));

        ResponseEntity<Object> responseEntity =controller.findByID(2L);

        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldGetOrganizacaoByNIF_Exists()
    {
        OrganizacaoDTO organizacaoDTOMOCK = mock(OrganizacaoDTO.class);

        when(service.findByNIF(123456789)).thenReturn(Optional.of(organizacaoDTOMOCK));

        ResponseEntity<Object> responseEntity =controller.findByNIF(123456789);

        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }
    @Test
    public void shouldNotFindListOrganizacao_Empty()
    {
        when(service.findAll()).thenReturn(List.of());

        assertThrows(ListaVaziaException.class,()->controller.findAll());
    }

    @Test
    public void shouldNotGetOrganizacaoByID_NotExists()
    {
        when(service.findByID(2L)).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class,()-> controller.findByID(2L));
    }

    @Test
    public void shouldNotGetOrganizacaoByNIF_NotExists()
    {
        when(service.findByNIF(123456789)).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class,()-> controller.findByNIF(123456789));
    }



}
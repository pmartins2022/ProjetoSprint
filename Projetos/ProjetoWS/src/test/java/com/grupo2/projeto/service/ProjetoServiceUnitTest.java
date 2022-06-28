package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.mapper.ProjetoDTOMapper;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.model.Projeto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class ProjetoServiceUnitTest
{
    @MockBean
    ProjetoRepository repository;

    @MockBean
    ProjetoDTOMapper mapper;

    @InjectMocks
    ProjetoService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidProjeto()
    {
        ProjetoDTO projetoDTOMock = mock(ProjetoDTO.class);
        Projeto projetoMock = mock(Projeto.class);

        when(mapper.toModel(projetoDTOMock)).thenReturn(projetoMock);
        when(repository.saveProjeto(projetoMock)).thenReturn(projetoMock);
        when(mapper.toDTO(projetoMock)).thenReturn(projetoDTOMock);

        ProjetoDTO saved = service.createAndSave(projetoDTOMock);

        assertEquals(projetoDTOMock, saved);
    }

    @Test
    public void shouldNotCreateProjeto_Exist()
    {
        ProjetoDTO projetoDTOMock = mock(ProjetoDTO.class);
        Projeto projetoMock = mock(Projeto.class);

        when(mapper.toModel(projetoDTOMock)).thenReturn(projetoMock);

        when(repository.saveProjeto(projetoMock)).thenThrow(ErroGeralException.class);

        assertThrows(ErroGeralException.class,()->service.createAndSave(projetoDTOMock));
    }

    @Test
    public void shouldFindProjeto_Exists()
    {
        ProjetoDTO projetoDTOMock = mock(ProjetoDTO.class);
        Projeto projetoMock = mock(Projeto.class);

        when(repository.findById(1L)).thenReturn(Optional.of(projetoMock));
        when(mapper.toDTO(projetoMock)).thenReturn(projetoDTOMock);

        Optional<ProjetoDTO> saved = service.findById(1L);

        assertEquals(projetoDTOMock, saved.get());
    }

    @Test
    public void shouldNotFindAnoLetivo_NotExists()
    {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<ProjetoDTO> saved = service.findById(1L);

        assertEquals(Optional.empty(), saved);
    }

}
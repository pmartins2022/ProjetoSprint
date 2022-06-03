package com.grupo2.utilizadores.service;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.dto.mapper.UtilizadorDTOMapper;
import com.grupo2.utilizadores.exception.ErroGeralException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.jpa.mapper.UtilizadorJPAMapper;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.UtilizadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class UtilizadorServiceUnitTests
{
    @MockBean
    UtilizadorRepository repository;

    @MockBean
    UtilizadorDTOMapper mapper;

    @InjectMocks
    UtilizadorService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldCreateUtilizador()
    {
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);
        Utilizador utilizadorMOCK = mock(Utilizador.class);

        when(mapper.toModel(dtoMOCK)).thenReturn(utilizadorMOCK);
        when(repository.save(utilizadorMOCK)).thenReturn(utilizadorMOCK);
        when(mapper.toDTO(utilizadorMOCK)).thenReturn(dtoMOCK);

        UtilizadorDTO saved = service.createAndSave(dtoMOCK);

        assertEquals(saved, dtoMOCK);
    }

    @Test
    public void shouldNotCreateUtilizador_Exists()
    {
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);
        Utilizador utilizadorMOCK = mock(Utilizador.class);

        when(mapper.toModel(dtoMOCK)).thenReturn(utilizadorMOCK);
        when(repository.save(utilizadorMOCK)).thenThrow(ErroGeralException.class);

        assertThrows(ErroGeralException.class, () -> service.createAndSave(dtoMOCK));
    }

    @Test
    public void shouldFindUtilizador_Exists()
    {
        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);
        Utilizador utilizador = mock(Utilizador.class);

        when(repository.findByID(1L)).thenReturn(Optional.of(utilizador));
        when(mapper.toDTO(utilizador)).thenReturn(utilizadorDTO);

        Optional<UtilizadorDTO> saved = service.findByID(1L);

        assertEquals(saved.get(), utilizadorDTO);
    }

    @Test
    public void shouldNotFindAnoLetivo_NotExists()
    {
        when(repository.findByID(1L)).thenReturn(Optional.empty());

        Optional<UtilizadorDTO> saved = service.findByID(1L);

        assertEquals(Optional.empty(), saved);
    }
}
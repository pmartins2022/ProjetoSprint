package com.grupo2.utilizadores.service;

import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.dto.mapper.UtilizadorDTOMapper;
import com.grupo2.utilizadores.exception.ErroGeralException;
import com.grupo2.utilizadores.exception.OptionalVazioException;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.UtilizadorRepository;
import com.grupo2.utilizadores.security.UtilizadorUserDetailsService;
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

    @MockBean
    UtilizadorUserDetailsService userDetailsService;

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

    @Test
    public void shouldRegistarUtilizador()
    {
        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);
        Utilizador utilizador = mock(Utilizador.class);
        when(utilizador.getPassword()).thenReturn("pass");

        when(repository.findByUsername(utilizadorDTO.getUsername())).thenReturn(Optional.empty());
        when(mapper.toModel(utilizadorDTO)).thenReturn(utilizador);
        when(repository.save(utilizador)).thenReturn(utilizador);
        when(mapper.toDTO(utilizador)).thenReturn(utilizadorDTO);

        UtilizadorDTO dto = service.registar(utilizadorDTO);

        assertEquals(utilizadorDTO, dto);
    }

    @Test
    public void shouldNotRegistarUtilizador()
    {
        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);
        when(utilizadorDTO.getUsername()).thenReturn("user");

        when(repository.findByUsername(utilizadorDTO.getUsername())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> service.registar(utilizadorDTO));
    }

    @Test
    public void shouldFindByUsernameInMemory()
    {
        UtilizadorAuthDTO authDTOMOCK = mock(UtilizadorAuthDTO.class);

        when(userDetailsService.findInMemory("user")).thenReturn(Optional.ofNullable(authDTOMOCK));

        Optional<UtilizadorAuthDTO> authDTO = service.findByUsername("user");

        assertTrue(authDTO.isPresent());
    }

    @Test
    public void shouldFindByUsernameInLocalRepository()
    {
        UtilizadorAuthDTO authDTOMOCK = mock(UtilizadorAuthDTO.class);
        Utilizador utilizadorMOCK = mock(Utilizador.class);

        when(userDetailsService.findInMemory("user")).thenReturn(Optional.empty());
        when(repository.findByUsername("user")).thenReturn(Optional.of(utilizadorMOCK));
        when(mapper.toAuthDTO(utilizadorMOCK)).thenReturn(authDTOMOCK);

        Optional<UtilizadorAuthDTO> authDTO = service.findByUsername("user");

        assertTrue(authDTO.isPresent());
    }

    /*@Test
    public void shouldReturnTrue_isOfRole()
    {
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);

        when(service.findByID(1L)).thenReturn(Optional.of(dtoMOCK));
        when(dtoMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.DOCENTE);

        Boolean isRole = service.isRole("ROLE_DOCENTE", 1L);

        assertTrue(isRole);
    }

    @Test
    public void shouldReturnFalse_isNotOfRole()
    {
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);
        when(dtoMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.DOCENTE);

        when(service.findByID(1L)).thenReturn(Optional.of(dtoMOCK));

        Boolean isRole = service.isRole("ROLE_DOCENTE", 1L);

        assertTrue(isRole);
    }*/

    @Test
    public void shouldReturnFalse_IDNotFound()
    {
        when(service.findByID(1L)).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class, ()-> service.isRole("ROLE_DOCENTE", 1L));
    }


    @Test
    public void shouldFindAll()
    {
        Utilizador utilizadorMOCK = mock(Utilizador.class);
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);

        when(repository.findAll()).thenReturn(List.of(utilizadorMOCK, utilizadorMOCK));
        when(mapper.toDTO(utilizadorMOCK)).thenReturn(dtoMOCK);

        List<UtilizadorDTO> list = service.findAll();

        assertEquals(2, list.size());
    }


    @Test
    public void shouldNotFindAll_Empty()
    {
        when(repository.findAll()).thenReturn(List.of());

        List<UtilizadorDTO> list = service.findAll();

        assertEquals(0, list.size());
    }


    @Test
    public void shouldNotFindAllDocentes_Empty()
    {
        when(repository.findAll()).thenReturn(List.of());

        List<UtilizadorDTO> list = service.findAllDocentes();

        assertEquals(0, list.size());
    }
}
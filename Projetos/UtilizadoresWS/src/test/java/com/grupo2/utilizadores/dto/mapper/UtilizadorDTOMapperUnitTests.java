package com.grupo2.utilizadores.dto.mapper;

import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.jpa.mapper.UtilizadorJPAMapper;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.model.factory.UtilizadorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class UtilizadorDTOMapperUnitTests
{
    @MockBean
    UtilizadorFactory factory;

    @InjectMocks
    UtilizadorDTOMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldConvertValidUtilizador()
    {
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);
        when(dtoMOCK.getId()).thenReturn(1L);
        when(dtoMOCK.getNome()).thenReturn("nome");
        when(dtoMOCK.getSobrenome()).thenReturn("sobrenome");
        when(dtoMOCK.getEmail()).thenReturn("email");
        when(dtoMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        Utilizador utilizadorMOCK = mock(Utilizador.class);
        when(utilizadorMOCK.getId()).thenReturn(1L);
        when(utilizadorMOCK.getNome()).thenReturn("nome");
        when(utilizadorMOCK.getSobrenome()).thenReturn("sobrenome");
        when(utilizadorMOCK.getEmail()).thenReturn("email");
        when(utilizadorMOCK.getUsername()).thenReturn("username");
        when(utilizadorMOCK.getPassword()).thenReturn("password");
        when(utilizadorMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        when(factory.createUtilizador(1L, dtoMOCK.getNome(), dtoMOCK.getSobrenome(), dtoMOCK.getEmail(),
                dtoMOCK.getUsername(),dtoMOCK.getPassword(),dtoMOCK.getTipoUtilizador())).thenReturn(utilizadorMOCK);

        Utilizador utilizador = mapper.toModel(dtoMOCK);

        assertEquals(utilizador.getId(), utilizadorMOCK.getId());
    }

    @Test
    public void shouldNotConvertValidUtilizador_InvalidAtributtes()
    {
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);

        when(factory.createUtilizador(dtoMOCK.getId(), dtoMOCK.getNome(), dtoMOCK.getSobrenome(), dtoMOCK.getEmail(),
                dtoMOCK.getUsername(), dtoMOCK.getPassword(),dtoMOCK.getTipoUtilizador())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, ()-> mapper.toModel(dtoMOCK));
    }

    @Test
    public void shouldConvertValidUtilizadorDTO_ValidAtributtes()
    {
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);
        when(dtoMOCK.getId()).thenReturn(1L);
        when(dtoMOCK.getNome()).thenReturn("nome");
        when(dtoMOCK.getSobrenome()).thenReturn("sobrenome");
        when(dtoMOCK.getEmail()).thenReturn("email");
        when(dtoMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        Utilizador utilizadorMOCK = mock(Utilizador.class);
        when(utilizadorMOCK.getId()).thenReturn(1L);
        when(utilizadorMOCK.getNome()).thenReturn("nome");
        when(utilizadorMOCK.getSobrenome()).thenReturn("sobrenome");
        when(utilizadorMOCK.getEmail()).thenReturn("email");
        when(utilizadorMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        UtilizadorDTO utilizadorDTO = mapper.toDTO(utilizadorMOCK);

        assertEquals(utilizadorDTO.getId(), utilizadorMOCK.getId());
    }

    @Test
    public void shouldConvertValidUtilizadorAuthDTO_ValidAtributtes()
    {
        UtilizadorAuthDTO dtoMOCK = mock(UtilizadorAuthDTO.class);
        when(dtoMOCK.getId()).thenReturn(1L);
        when(dtoMOCK.getUsername()).thenReturn("user");
        when(dtoMOCK.getPassword()).thenReturn("pass");
        when(dtoMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO.toString());

        Utilizador utilizadorMOCK = mock(Utilizador.class);
        when(utilizadorMOCK.getId()).thenReturn(1L);
        when(utilizadorMOCK.getUsername()).thenReturn("user");
        when(utilizadorMOCK.getPassword()).thenReturn("pass");
        when(utilizadorMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        UtilizadorAuthDTO authDTO = mapper.toAuthDTO(utilizadorMOCK);

        assertEquals(authDTO.getId(), utilizadorMOCK.getId());
    }

    /*@Test
    public void shouldConvertValidUtilizadorAuthDTOFromUserDetails_ValidAtributtes()
    {
        UtilizadorAuthDTO dtoMOCK = mock(UtilizadorAuthDTO.class);
        when(dtoMOCK.getId()).thenReturn(1L);
        when(dtoMOCK.getUsername()).thenReturn("user");
        when(dtoMOCK.getPassword()).thenReturn("pass");
        when(dtoMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO.toString());

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");
        when(userDetails.getPassword()).thenReturn("pass");
        //when(userDetails.getAuthorities()).thenReturn(List.of());

        UtilizadorAuthDTO authDTO = mapper.toAuthDTO(userDetails);

        assertEquals(authDTO.getUsername(), userDetails.getUsername());
    }*/

}
package com.grupo2.utilizadores.jpa.mapper;

import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.model.factory.UtilizadorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class UtilizadorJPAMapperUnitTests
{
    @MockBean
    UtilizadorFactory factory;

    @InjectMocks
    UtilizadorJPAMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldConvertValidUtilizador()
    {
        UtilizadorJPA jpaMOCK = mock(UtilizadorJPA.class);
        when(jpaMOCK.getId()).thenReturn(1L);
        when(jpaMOCK.getNome()).thenReturn("nome");
        when(jpaMOCK.getSobrenome()).thenReturn("sobrenome");
        when(jpaMOCK.getEmail()).thenReturn("email");
        when(jpaMOCK.getUsername()).thenReturn("username");
        when(jpaMOCK.getPassword()).thenReturn("password");
        when(jpaMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        Utilizador utilizadorMOCK = mock(Utilizador.class);
        when(utilizadorMOCK.getId()).thenReturn(1L);
        when(utilizadorMOCK.getNome()).thenReturn("nome");
        when(utilizadorMOCK.getSobrenome()).thenReturn("sobrenome");
        when(utilizadorMOCK.getEmail()).thenReturn("email");
        when(utilizadorMOCK.getUsername()).thenReturn("username");
        when(utilizadorMOCK.getPassword()).thenReturn("password");
        when(utilizadorMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        when(factory.createUtilizador(1L, jpaMOCK.getNome(), jpaMOCK.getSobrenome(), jpaMOCK.getEmail(),
                jpaMOCK.getUsername(),jpaMOCK.getPassword(),jpaMOCK.getTipoUtilizador())).thenReturn(utilizadorMOCK);

        Utilizador utilizador = mapper.toModel(jpaMOCK);

        assertEquals(utilizador.getId(), utilizadorMOCK.getId());
    }

    @Test
    public void shouldNotConvertValidUtilizador_InvalidAtributtes()
    {
        UtilizadorJPA jpaMOCK = mock(UtilizadorJPA.class);

        when(factory.createUtilizador(jpaMOCK.getId(), jpaMOCK.getNome(), jpaMOCK.getSobrenome(), jpaMOCK.getEmail(),
                jpaMOCK.getUsername(),jpaMOCK.getPassword(),jpaMOCK.getTipoUtilizador())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, ()-> mapper.toModel(jpaMOCK));
    }

    @Test
    public void shouldConvertValidUtilizadorJPA_ValidAtributtes()
    {
        UtilizadorJPA jpaMOCK = mock(UtilizadorJPA.class);
        when(jpaMOCK.getId()).thenReturn(1L);
        when(jpaMOCK.getNome()).thenReturn("nome");
        when(jpaMOCK.getSobrenome()).thenReturn("sobrenome");
        when(jpaMOCK.getEmail()).thenReturn("email");
        when(jpaMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        Utilizador utilizadorMOCK = mock(Utilizador.class);
        when(utilizadorMOCK.getId()).thenReturn(1L);
        when(utilizadorMOCK.getNome()).thenReturn("nome");
        when(utilizadorMOCK.getSobrenome()).thenReturn("sobrenome");
        when(utilizadorMOCK.getEmail()).thenReturn("email");
        when(utilizadorMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        UtilizadorJPA anoLetivoJPA = mapper.toJPA(utilizadorMOCK);

        assertEquals(anoLetivoJPA.getId(), utilizadorMOCK.getId());
    }

}
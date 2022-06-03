package com.grupo2.utilizadores.dto.mapper;

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

import javax.transaction.Transactional;

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
        when(utilizadorMOCK.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        when(factory.createUtilizador(1L, dtoMOCK.getNome(), dtoMOCK.getSobrenome(), dtoMOCK.getEmail(),
                dtoMOCK.getTipoUtilizador())).thenReturn(utilizadorMOCK);

        Utilizador utilizador = mapper.toModel(dtoMOCK);

        assertEquals(utilizador.getId(), utilizadorMOCK.getId());
    }

    @Test
    public void shouldNotConvertValidUtilizador_InvalidAtributtes()
    {
        UtilizadorDTO dtoMOCK = mock(UtilizadorDTO.class);

        when(factory.createUtilizador(dtoMOCK.getId(), dtoMOCK.getNome(), dtoMOCK.getSobrenome(), dtoMOCK.getEmail(),
                dtoMOCK.getTipoUtilizador())).thenThrow(ValidacaoInvalidaException.class);

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

        UtilizadorDTO anoLetivoDTO = mapper.toDTO(utilizadorMOCK);

        assertEquals(anoLetivoDTO.getId(), utilizadorMOCK.getId());
    }

}
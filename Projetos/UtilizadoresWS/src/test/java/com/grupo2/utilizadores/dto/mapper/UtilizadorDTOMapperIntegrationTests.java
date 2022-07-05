package com.grupo2.utilizadores.dto.mapper;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.jpa.mapper.UtilizadorJPAMapper;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UtilizadorDTOMapperIntegrationTests
{
    @Autowired
    private UtilizadorDTOMapper mapper;

    @Test
    public void shouldConvertValidUtilizador()
    {
        UtilizadorDTO dto = new UtilizadorDTO(1L, "nome", "sobrenome", "email@gmail.com", "usermame", "password", TipoUtilizador.ALUNO);

        Utilizador utilizador = mapper.toModel(dto);

        assertEquals(utilizador.getId(), dto.getId());
    }

    @Test
    public void shouldNotConvertValidUtilizador_InvalidAtributtes()
    {
        UtilizadorDTO dto = new UtilizadorDTO(1L, "nome", "sobrenome", "emailgmail.com", "usermame", "password",TipoUtilizador.ALUNO);

        assertThrows(ValidacaoInvalidaException.class, ()-> mapper.toModel(dto));
    }

    @Test
    public void shouldConvertValidUtilizadorDTO_ValidAtributtes()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com", "usermame", "password", TipoUtilizador.ALUNO);

        UtilizadorDTO dtoConverted = mapper.toDTO(utilizador);

        assertEquals(dtoConverted.getId(), utilizador.getId());
    }

}
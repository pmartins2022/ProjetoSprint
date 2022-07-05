package com.grupo2.utilizadores.jpa.mapper;

import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class UtilizadorJPAMapperIntegrationTests
{
    @Autowired
    private UtilizadorJPAMapper mapper;

    @Test
    public void shouldConvertValidUtilizador()
    {
       UtilizadorJPA jpa = new UtilizadorJPA(1L, "nome", "sobrenome", "email@gmail.com","aabb","aabb", TipoUtilizador.ALUNO);

        Utilizador utilizador = mapper.toModel(jpa);

        assertEquals(utilizador.getId(), jpa.getId());
    }

    @Test
    public void shouldNotConvertValidUtilizador_InvalidAtributtes()
    {
        UtilizadorJPA jpa = new UtilizadorJPA(1L, "nome", "sobrenome", "email.com","aabb","aabb", TipoUtilizador.ALUNO);

        assertThrows(ValidacaoInvalidaException.class, ()-> mapper.toModel(jpa));
    }

    @Test
    public void shouldConvertValidUtilizadorJPA_ValidAtributtes()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com","aabb","aabb", TipoUtilizador.ALUNO);

        UtilizadorJPA anoLetivoJPA = mapper.toJPA(utilizador);

        assertEquals(anoLetivoJPA.getId(), utilizador.getId());
    }

}
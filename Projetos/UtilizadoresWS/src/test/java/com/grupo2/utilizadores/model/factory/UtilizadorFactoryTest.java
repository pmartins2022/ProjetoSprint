package com.grupo2.utilizadores.model.factory;

import com.grupo2.utilizadores.exception.ErroGeralException;
import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;
import com.grupo2.utilizadores.model.TipoUtilizador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UtilizadorFactoryTest
{
    @Autowired
    private UtilizadorFactory factory;

    @Test
    public void shouldCreateValidUtilizador()
    {
        assertDoesNotThrow(()-> factory.createUtilizador("nome", "sobrenome", "email@gmail.com","aabb","aabb", TipoUtilizador.ALUNO));
    }

    @Test
    public void shouldNotCreateValidUtilizador()
    {
        assertThrows(ValidacaoInvalidaException.class, ()-> factory.createUtilizador("nome", "sobrenome", "email","aabb","aabb", TipoUtilizador.ALUNO));
    }

    @Test
    public void shouldCreateValidUtilizadorWithAllAtributtes()
    {
        assertDoesNotThrow(()-> factory.createUtilizador(1L, "nome", "sobrenome", "email@gmail.com","aabb","aabb", TipoUtilizador.ALUNO));
    }

    @Test
    public void shouldNotCreateValidUtilizadorWithAllAtributtes()
    {
        assertThrows(ValidacaoInvalidaException.class, ()-> factory.createUtilizador("nome", "sobrenome", "email","aabb","aabb", TipoUtilizador.ALUNO));
    }
}
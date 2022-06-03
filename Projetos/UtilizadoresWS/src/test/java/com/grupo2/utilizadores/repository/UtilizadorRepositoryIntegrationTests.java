package com.grupo2.utilizadores.repository;

import com.grupo2.utilizadores.exception.ErroGeralException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class UtilizadorRepositoryIntegrationTests
{
    @Autowired
    private UtilizadorRepository repository;

    @Test
    public void shouldCreateValidUtilizador()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com",
                TipoUtilizador.DOCENTE);

        Utilizador saved = repository.save(utilizador);

        assertEquals(saved, utilizador);
    }


    @Test
    public void shouldFindByIDUtilizador_Exists()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com",
                TipoUtilizador.DOCENTE);

        Utilizador save = repository.save(utilizador);

        Optional<Utilizador> found = repository.findByID(save.getId());

        assertEquals(found.get(), utilizador);
    }

    @Test
    public void shouldNotFindByIDUtilizador_NotExists()
    {
        Optional<Utilizador> found = repository.findByID(1L);

        assertTrue(found.isEmpty());
    }
}
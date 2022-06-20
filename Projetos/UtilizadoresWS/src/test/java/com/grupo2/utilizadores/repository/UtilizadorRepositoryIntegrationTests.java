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
    public void shouldSaveValidUtilizador()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com","username","pass",
                TipoUtilizador.DOCENTE);

        Utilizador saved = repository.save(utilizador);

        Optional<Utilizador> utilizadorSaved = repository.findByID(saved.getId());

        assertEquals(utilizador, utilizadorSaved.get());
    }
//id null, username e pass únicos
    @Test
    public void shouldNotSaveValidUtilizador_IDExists()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com","username","pass",
                TipoUtilizador.DOCENTE);

        Utilizador utilizador1 = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com","username","pass",
                TipoUtilizador.DOCENTE);

        repository.save(utilizador);

        assertThrows(ErroGeralException.class, ()-> repository.save(utilizador1));
    }

    @Test
    public void shouldNotSaveValidUtilizador_EmailExists()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com","username","pass",
                TipoUtilizador.DOCENTE);

        Utilizador utilizador1 = new Utilizador(2L, "nome", "sobrenome", "email@gmail.com","username","pass",
                TipoUtilizador.DOCENTE);

        repository.save(utilizador);

        assertThrows(ErroGeralException.class, ()-> repository.save(utilizador1));
    }


    @Test
    public void shouldFindByIDUtilizador_Exists()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com","user", "pass",
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
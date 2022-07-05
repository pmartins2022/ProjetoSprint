package com.grupo2.utilizadores.repository;

import com.grupo2.utilizadores.exception.ErroGeralException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.jpa.UtilizadorJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UtilizadorRepositoryIntegrationTests
{
    @Autowired
    private UtilizadorRepository repository;


    @Test
    public void shouldSaveValidUtilizador()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com", "username", "pass",
                TipoUtilizador.DOCENTE);

        Utilizador saved = repository.save(utilizador);

        Optional<Utilizador> utilizadorSaved = repository.findByID(saved.getId());

        assertEquals(utilizador, utilizadorSaved.get());
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

    @Test
    public void shouldFindAllUtilizador()
    {
        Utilizador utilizador = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com", "user", "pass",
                TipoUtilizador.DOCENTE);

        Utilizador utilizador1 = new Utilizador(2L, "nomee", "sobrenomee", "emailasd@gmail.com", "userr", "pass",
                TipoUtilizador.DOCENTE);

        repository.save(utilizador);
        repository.save(utilizador1);

        List<Utilizador> list = repository.findAll();

        assertEquals(2, list.size());
    }

    @Test
    public void shouldNotFindAllUtilizador_Empty()
    {
        List<Utilizador> list = repository.findAll();

        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldFindByUsername()
    {
        Utilizador utilizador1 = new Utilizador(1L, "nome", "sobrenome", "email@gmail.com", "user", "pass",
                TipoUtilizador.DOCENTE);

        repository.save(utilizador1);

        Optional<Utilizador> optionalUtilizador = repository.findByUsername(utilizador1.getUsername());

        assertEquals(utilizador1.getUsername(), optionalUtilizador.get().getUsername());
    }

    @Test
    public void shouldNotFindByUsername_NotExists()
    {

        Optional<Utilizador> optionalUtilizador = repository.findByUsername("username");

        assertTrue(optionalUtilizador.isEmpty());
    }
}
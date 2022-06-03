package com.grupo2.utilizadores.repository;

import com.grupo2.utilizadores.exception.ErroGeralException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.jpa.mapper.UtilizadorJPAMapper;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.jpa.UtilizadorJPARepository;
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
class UtilizadorRepositoryUnitTests
{
    @MockBean
    UtilizadorJPARepository jpaRepository;

    @MockBean
    UtilizadorJPAMapper mapper;

    @InjectMocks
    UtilizadorRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldCreateValidUtilizador()
    {
        Utilizador utilizadorMOCK = mock(Utilizador.class);
        UtilizadorJPA utilizadorJPAMOCK = mock(UtilizadorJPA.class);

        when(mapper.toJPA(utilizadorMOCK)).thenReturn(utilizadorJPAMOCK);
        when(jpaRepository.save(utilizadorJPAMOCK)).thenReturn(utilizadorJPAMOCK);
        when(mapper.toModel(utilizadorJPAMOCK)).thenReturn(utilizadorMOCK);

        Utilizador saved = repository.save(utilizadorMOCK);

        assertEquals(saved, utilizadorMOCK);
    }

    @Test
    public void shouldNotCreateValidUtilizador_Exists()
    {

        Utilizador utilizadorMOCK = mock(Utilizador.class);
        when(utilizadorMOCK.getId()).thenReturn(1L);

        UtilizadorJPA utilizadorJPAMOCK = mock(UtilizadorJPA.class);

        when(jpaRepository.findById(utilizadorMOCK.getId())).thenReturn(Optional.of(utilizadorJPAMOCK));

        assertThrows(ErroGeralException.class, () -> repository.save(utilizadorMOCK));
    }

    @Test
    public void shouldFindByIDUtilizador_Exists()
    {
        UtilizadorJPA jpaMOCK = mock(UtilizadorJPA.class);
        Utilizador utilizadorMOCK = mock(Utilizador.class);

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(jpaMOCK));
        when(mapper.toModel(jpaMOCK)).thenReturn(utilizadorMOCK);

        Optional<Utilizador> utilizador = repository.findByID(1L);

        assertEquals(utilizador.get(), utilizadorMOCK);
    }

    @Test
    public void shouldNotFindByIDUtilizador_NotExists()
    {
        Utilizador utilizadorMOCK = mock(Utilizador.class);

        when(jpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Utilizador> utilizador = repository.findByID(1L);

        assertEquals(utilizador, Optional.empty());
    }

}
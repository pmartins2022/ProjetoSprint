package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.ConteudoJPA;
import com.grupo2.projeto.jpa.mapper.ConteudoJPAMapper;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.jpa.ConteudoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConteudoRepositoryUnitTests
{

    @MockBean
    private ConteudoJPARepository jpaRepository;
    @MockBean
    private ConteudoJPAMapper mapper;

    @InjectMocks
    private ConteudoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateConteudo()
    {
        ConteudoJPA jpaMOCK = mock(ConteudoJPA.class);
        Conteudo conteudoMOCK = mock(Conteudo.class);

        when(mapper.toJpa(conteudoMOCK)).thenReturn(jpaMOCK);
        when(jpaRepository.save(jpaMOCK)).thenReturn(jpaMOCK);
        when(mapper.toModel(jpaMOCK)).thenReturn(conteudoMOCK);

        Conteudo conteudo = repository.saveConteudo(conteudoMOCK);

        assertEquals(conteudoMOCK, conteudo);
    }


    @Test
    public void shouldFindByID()
    {
        ConteudoJPA jpaMOCK = mock(ConteudoJPA.class);
        Conteudo conteudoMOCK = mock(Conteudo.class);

        when(jpaRepository.findById(1L)).thenReturn(Optional.ofNullable(jpaMOCK));
        when(mapper.toModel(jpaMOCK)).thenReturn(conteudoMOCK);

        Optional<Conteudo> conteudo = repository.findById(1L);

        assertTrue(conteudo.isPresent());
    }

    @Test
    public void shouldNotFindByID_Empty()
    {
        when(jpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Conteudo> conteudo = repository.findById(1L);

        assertTrue(conteudo.isEmpty());
    }

    @Test
    public void shouldReturnConteudoUpdated()
    {
        ConteudoJPA jpaMOCK = mock(ConteudoJPA.class);
        Conteudo conteudoMOCK = mock(Conteudo.class);

        jpaRepository.deleteById(1L);
        when(mapper.toJpa(conteudoMOCK)).thenReturn(jpaMOCK);
        when(jpaRepository.save(jpaMOCK)).thenReturn(jpaMOCK);
        when(mapper.toModel(jpaMOCK)).thenReturn(conteudoMOCK);

        Conteudo conteudo = repository.atualizarConteudo(conteudoMOCK);

        assertEquals(conteudoMOCK, conteudo);
    }
}
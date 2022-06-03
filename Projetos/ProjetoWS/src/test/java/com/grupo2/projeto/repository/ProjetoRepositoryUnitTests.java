package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.ProjetoJPA;
import com.grupo2.projeto.jpa.mapper.ProjetoJPAMapper;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.jpa.ProjetoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjetoRepositoryUnitTests
{
    @MockBean
    private ProjetoJPARepository jpaRepository;
    @MockBean
    private ProjetoJPAMapper mapper;

    @InjectMocks
    private ProjetoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveProjeto()
    {
        Projeto proj = mock(Projeto.class);
        ProjetoJPA jpa = mock(ProjetoJPA.class);

        when(mapper.toJpa(proj)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(proj);

        when(repository.saveProjeto(proj)).thenReturn(proj);

        repository.saveProjeto(proj);
    }

    @Test
    public void shouldFindById()
    {
        Projeto proj = mock(Projeto.class);
        ProjetoJPA jpa = mock(ProjetoJPA.class);

        when(mapper.toModel(jpa)).thenReturn(proj);

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(jpa));

        Optional<Projeto> id = repository.findById(1L);
        assertTrue(id.isPresent());
    }

    @Test
    public void shouldNotFindById()
    {
        Projeto proj = mock(Projeto.class);
        ProjetoJPA jpa = mock(ProjetoJPA.class);

        when(mapper.toModel(jpa)).thenReturn(proj);

        when(jpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Projeto> id = repository.findById(1L);
        assertTrue(id.isEmpty());
    }

}
package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.MomentoAvaliacaoJPA;
import com.grupo2.edicaouc.jpa.mapper.MomentoAvaliacaoJPAMapper;
import com.grupo2.edicaouc.model.MomentoAvaliacao;
import com.grupo2.edicaouc.repository.jpa.MomentoAvaliacaoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class MomentoAvaliacaoRepositoryUnitTests
{
    @MockBean
    private MomentoAvaliacaoJPARepository jpaRepository;

    @MockBean
    private MomentoAvaliacaoJPAMapper mapper;

    @InjectMocks
    private MomentoAvaliacaoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreate()
    {
        MomentoAvaliacao momento = mock(MomentoAvaliacao.class);
        MomentoAvaliacaoJPA jpa = mock(MomentoAvaliacaoJPA.class);

        when(mapper.toJPA(momento) ).thenReturn(jpa);
        when(mapper.toModel(jpa) ).thenReturn(momento);
        when(jpaRepository.save(jpa) ).thenReturn(jpa);

        MomentoAvaliacao avaliacao = repository.createAndSaveMomentoAvaliacao(momento);

        assertEquals(momento, avaliacao);
    }

    @Test
    public void shouldFindById()
    {
        MomentoAvaliacaoJPA jpa = mock(MomentoAvaliacaoJPA.class);
        MomentoAvaliacao momento = mock(MomentoAvaliacao.class);

        when(mapper.toJPA(momento) ).thenReturn(jpa);
        when(mapper.toModel(jpa) ).thenReturn(momento);
        when(jpaRepository.findById(1L) ).thenReturn(Optional.of(jpa));

        Optional<MomentoAvaliacao> id = repository.findById(1L);

        assertTrue(id.isPresent());
        assertEquals(momento, id.get());
    }

    @Test
    public void shouldNotFindById()
    {
        when(jpaRepository.findById(99L) ).thenReturn(Optional.empty());

        Optional<MomentoAvaliacao> id = repository.findById(99L);

        assertTrue(id.isEmpty());
    }
}
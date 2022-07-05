package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.AvaliacaoJPA;
import com.grupo2.projeto.jpa.ConteudoJPA;
import com.grupo2.projeto.jpa.factory.AvaliacaoJPAFactory;
import com.grupo2.projeto.jpa.mapper.AvaliacaoJPAMapper;
import com.grupo2.projeto.jpa.mapper.ConteudoJPAMapper;
import com.grupo2.projeto.jpa.mapper.ProjetoJPAMapper;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.jpa.AvaliacaoJPARepository;
import com.grupo2.projeto.repository.jpa.ProjetoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AvaliacaoRepositoryUnitTests
{

    @MockBean
    private AvaliacaoJPARepository jpaRepository;
    @MockBean
    private AvaliacaoJPAMapper mapper;
    @MockBean
    private ConteudoJPAMapper conteudoJPAMapper;
    @MockBean
    private AvaliacaoJPAFactory factory;

    @InjectMocks
    private AvaliacaoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveAvaliacao()
    {
        Avaliacao avaliacaoMOCK = mock(Avaliacao.class);
        when(avaliacaoMOCK.getId()).thenReturn(1L);
        when(avaliacaoMOCK.getIdMomentoAvaliacao()).thenReturn(1L);
        when(avaliacaoMOCK.getPresidenteId()).thenReturn(1L);
        when(avaliacaoMOCK.getArguenteId()).thenReturn(2L);
        when(avaliacaoMOCK.getOrientadorId()).thenReturn(3L);
        when(avaliacaoMOCK.getIdProjeto()).thenReturn(1L);
        when(avaliacaoMOCK.getConteudo()).thenReturn(1L);
        when(avaliacaoMOCK.getNota()).thenReturn(32);

        AvaliacaoJPA jpaMOCK = mock(AvaliacaoJPA.class);
        Conteudo conteudoMOCK = mock(Conteudo.class);
        ConteudoJPA conteudoJPAMOCK = mock(ConteudoJPA.class);

        when(conteudoJPAMapper.toJpa(conteudoMOCK)).thenReturn(conteudoJPAMOCK);
        when(factory.create(avaliacaoMOCK.getId(), avaliacaoMOCK.getIdMomentoAvaliacao(), avaliacaoMOCK.getPresidenteId(), avaliacaoMOCK.getOrientadorId(),
                avaliacaoMOCK.getArguenteId(), avaliacaoMOCK.getIdProjeto(),conteudoJPAMOCK, avaliacaoMOCK.getNota())).thenReturn(jpaMOCK);
        when(jpaRepository.save(jpaMOCK)).thenReturn(jpaMOCK);
        when(mapper.toModel(jpaMOCK)).thenReturn(avaliacaoMOCK);

        Avaliacao avaliacao = repository.saveAvaliacao(avaliacaoMOCK, conteudoMOCK);

        assertEquals(avaliacaoMOCK, avaliacao);
    }

    @Test
    public void shouldFindByID()
    {
        Avaliacao avaliacaoMOCK = mock(Avaliacao.class);
        AvaliacaoJPA jpaMOCK = mock(AvaliacaoJPA.class);

        when(jpaRepository.findById(1L)).thenReturn(Optional.ofNullable(jpaMOCK));
        when(mapper.toModel(jpaMOCK)).thenReturn(avaliacaoMOCK);

        Optional<Avaliacao> avaliacao = repository.findById(1L);

        assertTrue(avaliacao.isPresent());
    }

    @Test
    public void shouldNotFindByID_Empty()
    {
        when(jpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Avaliacao> avaliacao = repository.findById(1L);

        assertTrue(avaliacao.isEmpty());
    }

    @Test
    public void shouldFindAll()
    {
        Avaliacao avaliacaoMOCK = mock(Avaliacao.class);
        AvaliacaoJPA jpaMOCK = mock(AvaliacaoJPA.class);

        when(jpaRepository.findAll()).thenReturn(List.of(jpaMOCK, jpaMOCK));
        when(mapper.toModel(jpaMOCK)).thenReturn(avaliacaoMOCK);

        List<Avaliacao> avaliacao = repository.findAll();

        assertEquals(2, avaliacao.size());
    }

    @Test
    public void shouldNotFindAll_Empty()
    {
        when(jpaRepository.findAll()).thenReturn(List.of());

        List<Avaliacao> avaliacao = repository.findAll();

        assertEquals(0, avaliacao.size());
    }
}
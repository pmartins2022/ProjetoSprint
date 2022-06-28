package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.*;
import com.grupo2.projeto.dto.mapper.AvaliacaoDTOMapper;
import com.grupo2.projeto.dto.mapper.ConteudoDTOMapper;
import com.grupo2.projeto.model.*;
import com.grupo2.projeto.repository.rest.UtilizadorRestRepository;
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
class AvaliacaoServiceUnitTest {

    /*@MockBean
    AvaliacaoRepository repository;
    @MockBean
    UtilizadorRestRepository utilizadorRestRepository;
    @MockBean
    ConteudoRepository conteudoRepository;
    @MockBean
    ProjetoRepository projetoRepository;
    @MockBean
    AvaliacaoDTOMapper avaliacaoDTOMapper;
    @MockBean
    ConteudoDTOMapper conteudoDTOMapper;
    @InjectMocks
    AvaliacaoService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAvaliacao()
    {
        AvaliacaoDTO avaliacaoDTOMock = mock(AvaliacaoDTO.class);
        when(avaliacaoDTOMock.getConteudo()).thenReturn(1L);
        Avaliacao avaliacaoMock = mock(Avaliacao.class);
        Projeto projetoMock = mock(Projeto.class);
        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);
        Conteudo conteudoMock = mock(Conteudo.class);
        when(conteudoMock.getEstadoConteudo()).thenReturn(EstadoConteudo.APROVADO);
        when(conteudoRepository.findById(avaliacaoDTOMock.getConteudo())).thenReturn(Optional.of(conteudoMock));
        when(avaliacaoDTOMock.getPresidenteId()).thenReturn(2L);
        when(avaliacaoDTOMock.getArguenteId()).thenReturn(3L);
        when(avaliacaoDTOMock.getOrientadorId()).thenReturn(5L);
        when(utilizadorRestRepository.findById(avaliacaoDTOMock.getPresidenteId())).thenReturn(Optional.of(utilizadorDTO));
        when(utilizadorRestRepository.findById(avaliacaoDTOMock.getArguenteId())).thenReturn(Optional.of(utilizadorDTO));
        when(utilizadorRestRepository.findById(avaliacaoDTOMock.getOrientadorId())).thenReturn(Optional.of(utilizadorDTO));
        when(utilizadorDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.DOCENTE);
        when(conteudoRepository.findById(avaliacaoDTOMock.getConteudo())).thenReturn(Optional.of(conteudoMock));
        when(projetoRepository.findById(avaliacaoDTOMock.getIdProjeto())).thenReturn(Optional.of(projetoMock));
        when(avaliacaoDTOMapper.toModel(avaliacaoDTOMock)).thenReturn(avaliacaoMock);
        when(repository.saveAvaliacao(avaliacaoMock,conteudoMock)).thenReturn(avaliacaoMock);
        when(avaliacaoDTOMapper.toDTO(avaliacaoMock)).thenReturn(avaliacaoDTOMock);
        AvaliacaoDTO saved = service.createAndSave(avaliacaoDTOMock);
        assertEquals(avaliacaoDTOMock,saved);
    }

    @Test
    public void shouldFindAvaliacao_Exists()
    {
        AvaliacaoDTO avaliacaoDTOMock = mock(AvaliacaoDTO.class);
        Avaliacao avaliacaoMock = mock(Avaliacao.class);
        when(repository.findById(1L)).thenReturn(Optional.of(avaliacaoMock));
        when(avaliacaoDTOMapper.toDTO(avaliacaoMock)).thenReturn(avaliacaoDTOMock);
        Optional<AvaliacaoDTO> saved = service.findById(1L);
        assertEquals(avaliacaoDTOMock,saved.get());
    }

    @Test
    public void shouldFindAllAvaliacao()
    {

        Avaliacao avaliacaoMock = mock(Avaliacao.class);
        when(repository.findAll()).thenReturn(List.of(avaliacaoMock,avaliacaoMock,avaliacaoMock));
        List<AvaliacaoDTO> saved = service.findAll();
        assertEquals(3,saved.size());
    }*/
}
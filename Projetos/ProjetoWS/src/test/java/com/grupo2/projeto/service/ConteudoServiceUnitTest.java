package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.dto.mapper.ConteudoDTOMapper;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.Projeto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class ConteudoServiceUnitTest {

    /*@MockBean
    ConteudoRepository conteudoRepository;
    @MockBean
    ConteudoDTOMapper conteudoDTOMapper;

    @MockBean
    Projeto projeto;
    @MockBean
    ProjetoRepository projetoRepository;

    @InjectMocks
    ConteudoService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        ConteudoDTO conteudoDTOMock = mock(ConteudoDTO.class);
        Conteudo conteudoMock = mock(Conteudo.class);
        when(conteudoRepository.findById(1L)).thenReturn(Optional.of(conteudoMock));
        when(conteudoDTOMapper.toDTO(conteudoMock)).thenReturn(conteudoDTOMock);
        Optional<ConteudoDTO> saved = service.findById(1L);
        assertEquals(conteudoDTOMock, saved.get());
    }

    @Test
    void findAllByIdProjeto() {
        Conteudo conteudoMock = mock(Conteudo.class);
        when(conteudoRepository.findAllByIdProjeto(1L)).thenReturn(List.of(conteudoMock,conteudoMock,conteudoMock));
        List<ConteudoDTO> lista = service.findAllByIdProjeto(1L);
        assertEquals(3,lista.size());
    }
*/

}
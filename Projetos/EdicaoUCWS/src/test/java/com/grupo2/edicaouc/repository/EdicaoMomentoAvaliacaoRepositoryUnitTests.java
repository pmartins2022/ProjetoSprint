package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoMomentoAvaliacaoJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoMomentoAvaliacaoJPAMapper;
import com.grupo2.edicaouc.model.EdicaoMomentoAvaliacao;
import com.grupo2.edicaouc.repository.jpa.EdicaoMomentoAvaliacaoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class EdicaoMomentoAvaliacaoRepositoryUnitTests
{
    @MockBean
    private EdicaoMomentoAvaliacaoJPARepository jpaRepo;

    @MockBean
    private EdicaoMomentoAvaliacaoJPAMapper mapper;

    @InjectMocks
    private EdicaoMomentoAvaliacaoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAndSave()
    {
        EdicaoMomentoAvaliacao model = mock(EdicaoMomentoAvaliacao.class);
        EdicaoMomentoAvaliacaoJPA jpa = mock(EdicaoMomentoAvaliacaoJPA.class);

        when(mapper.toJPA(model)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(model);
        when(jpaRepo.save(jpa)).thenReturn(jpa);

        EdicaoMomentoAvaliacao avaliacao = repository.createAndSaveEdicaoMomentoAvaliacao(model);

        assertNotNull(avaliacao);
        assertEquals(model, avaliacao);
    }
}
package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoID;
import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCAlunoJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUCAluno;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCAlunoIDFactory;
import com.grupo2.edicaouc.model.factory.EdicaoUCAlunoJPAFactory;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCAlunoJPARepository;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class EdicaoUCAlunoRepositoryUnitTests
{
    @MockBean
    private EdicaoUCAlunoJPAMapper mapper;

    @MockBean
    private EdicaoUCAlunoJPARepository jpaRepository;

    @MockBean
    private EdicaoUCJpaRepository jpaEdicaoUCRepository;

    @MockBean
    private EdicaoUCAlunoIDFactory idFactory;

    @MockBean
    private EdicaoUCAlunoJPAFactory jpaFactory;

    @InjectMocks
    private EdicaoUCAlunoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveEdicaoUCAluno()
    {
        EdicaoUCAlunoID id = mock(EdicaoUCAlunoID.class);
        EdicaoUCAlunoJPA jpa = mock(EdicaoUCAlunoJPA.class);
        EdicaoUCAluno aluno = mock(EdicaoUCAluno.class);


        when(idFactory.create(1L,1L)).thenReturn(id);
        when(jpaFactory.create(id)).thenReturn(jpa);
        when(jpaRepository.save(jpa)).thenReturn(jpa);

        when(mapper.toModel(jpa)).thenReturn(aluno);

        EdicaoUCAluno ucAluno = repository.saveEdicaoUCAluno(1L, 1L);

        assertNotNull(ucAluno);
        assertEquals(aluno, ucAluno);
    }

    @Test
    public void shouldStudentAvailable()
    {
        EdicaoUCAlunoJPA jpa = mock(EdicaoUCAlunoJPA.class);
        EdicaoUCJPA edicaoUC = mock(EdicaoUCJPA.class);

        when(jpaRepository.findAll()).thenReturn(List.of(jpa));
        when(jpaEdicaoUCRepository.findAll()).thenReturn(List.of(edicaoUC));

        assertTrue(repository.isStudentAvailable(1L));
    }

    @Test
    public void shouldNotStudentAvailable()
    {
        EdicaoUCAlunoJPA jpa = mock(EdicaoUCAlunoJPA.class);
        EdicaoUCJPA edicaoUC = mock(EdicaoUCJPA.class);
        EdicaoUCAlunoID id = mock(EdicaoUCAlunoID.class);

        when(edicaoUC.getEstadoEdicaoUC()).thenReturn(EstadoEdicaoUC.ATIVA);

        when(jpa.getId()).thenReturn(id);
        when(jpa.getId().getAlunoID()).thenReturn(1L);

        when(jpaRepository.findAll()).thenReturn(List.of(jpa));
        when(jpaEdicaoUCRepository.findAll()).thenReturn(List.of(edicaoUC));

        assertFalse(repository.isStudentAvailable(1L));
    }

}
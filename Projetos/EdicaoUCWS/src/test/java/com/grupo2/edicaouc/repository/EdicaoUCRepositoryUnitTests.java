package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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
class EdicaoUCRepositoryUnitTests
{
    @MockBean
    private EdicaoUCJpaRepository jpaRepository;

    @MockBean
    private EdicaoUCJPAMapper mapper;

    @InjectMocks
    private EdicaoUCRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveEdicaoUC()
    {
        EdicaoUC edicaoUC = mock(EdicaoUC.class);
        EdicaoUCJPA jpa = mock(EdicaoUCJPA.class);

        when(jpaRepository.save(jpa)).thenReturn(jpa);

        when(mapper.toJPA(edicaoUC)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(edicaoUC);

        EdicaoUC uc = repository.saveEdicaoUC(edicaoUC);

        assertEquals(edicaoUC, uc);
    }

    @Test
    public void shouldFindAllEdicaoByUCCode()
    {
        EdicaoUCJPA jpa = mock(EdicaoUCJPA.class);
        EdicaoUC edicaoUC = mock(EdicaoUC.class);

        when(jpaRepository.findAllByucCode("UCCode")).thenReturn(List.of(jpa));

        when(mapper.toModel(jpa)).thenReturn(edicaoUC);

        List<EdicaoUC> code = repository.findAllEdicaoByUCCode("UCCode");

        assertEquals(1, code.size());
    }

    @Test
    public void shouldFindById()
    {
        EdicaoUC edicaoUC = mock(EdicaoUC.class);
        EdicaoUCJPA mock = mock(EdicaoUCJPA.class);

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(mock));
        when(mapper.toModel(mock)).thenReturn(edicaoUC);

        Optional<EdicaoUC> id = repository.findById(1L);

        assertTrue(id.isPresent());
        assertEquals(edicaoUC, id.get());
    }

    @Test
    public void shouldNotFindById()
    {
        when(jpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<EdicaoUC> id = repository.findById(1L);

        assertTrue(id.isEmpty());
    }

    @Test
    public void shouldFindAll()
    {
        EdicaoUC edicaoUC = mock(EdicaoUC.class);
        EdicaoUCJPA mock = mock(EdicaoUCJPA.class);

        when(jpaRepository.findAll()).thenReturn(List.of(mock));
        when(mapper.toModel(mock)).thenReturn(edicaoUC);

        List<EdicaoUC> all = repository.findAll();

        assertEquals(1, all.size());
    }

    @Test
    public void shouldAtivarEdicao()
    {
        EdicaoUC edicaoUC = mock(EdicaoUC.class);
        EdicaoUCJPA jpa = mock(EdicaoUCJPA.class);


        when(edicaoUC.getId()).thenReturn(1L);
        when(mapper.toJPA(edicaoUC)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(edicaoUC);
        when(jpaRepository.save(jpa)).thenReturn(jpa);


        EdicaoUC uc = repository.ativarEdicao(edicaoUC);
    }

    @Test
    public void shouldDesativarEdicao()
    {
        EdicaoUC edicaoUC = mock(EdicaoUC.class);
        EdicaoUCJPA jpa = mock(EdicaoUCJPA.class);


        when(edicaoUC.getId()).thenReturn(1L);
        when(mapper.toJPA(edicaoUC)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(edicaoUC);
        when(jpaRepository.save(jpa)).thenReturn(jpa);


        EdicaoUC uc = repository.desativarEdicao(edicaoUC);
    }

    @Test
    public void shouldFindByRucID()
    {
        EdicaoUC edicaoUC = mock(EdicaoUC.class);
        EdicaoUCJPA jpa = mock(EdicaoUCJPA.class);

        when(jpaRepository.findByRucID(1L)).thenReturn(List.of(jpa));

        when(mapper.toModel(jpa)).thenReturn(edicaoUC);

        List<EdicaoUC> list = repository.findByRucID(1L);

        assertEquals(1, list.size());
    }

    @Test
    public void shouldFindByRucIDAndEstadoEdicaoUC()
    {
        EdicaoUCJPA mock = mock(EdicaoUCJPA.class);
        EdicaoUC edicaoUC = mock(EdicaoUC.class);

        when(jpaRepository.findByRucIDAndEstadoEdicaoUC(1L,  EstadoEdicaoUC.ATIVA)).thenReturn(Optional.of(mock));

        when(mapper.toModel(mock)).thenReturn(edicaoUC);

        Optional<EdicaoUC> optional = repository.findByRucIDAndEstadoEdicaoUC(1L,  EstadoEdicaoUC.ATIVA);

        assertTrue(optional.isPresent());
        assertEquals(edicaoUC, optional.get());
    }

    @Test
    public void shouldNotFindByRucIDAndEstadoEdicaoUC()
    {
        when(jpaRepository.findByRucIDAndEstadoEdicaoUC(1L,  EstadoEdicaoUC.ATIVA)).thenReturn(Optional.empty());

        Optional<EdicaoUC> optional = repository.findByRucIDAndEstadoEdicaoUC(1L,  EstadoEdicaoUC.ATIVA);

        assertTrue(optional.isEmpty());
    }

    @Test
    public void shouldFindByEstadoEdicaoUC()
    {
        EdicaoUCJPA edicaoUC = mock(EdicaoUCJPA.class);
        EdicaoUC mock = mock(EdicaoUC.class);

        when(jpaRepository.findByEstadoEdicaoUC(EstadoEdicaoUC.ATIVA)).thenReturn(Optional.of(edicaoUC));
        when(mapper.toModel(edicaoUC)).thenReturn(mock);

        Optional<EdicaoUC> uc = repository.findByEstadoEdicaoUC(EstadoEdicaoUC.ATIVA);

        assertTrue(uc.isPresent());
        assertEquals(mock, uc.get());
    }

    @Test
    public void shouldNotFindByEstadoEdicaoUC()
    {
        when(jpaRepository.findByEstadoEdicaoUC(EstadoEdicaoUC.ATIVA)).thenReturn(Optional.empty());

        Optional<EdicaoUC> uc = repository.findByEstadoEdicaoUC(EstadoEdicaoUC.ATIVA);

        assertTrue(uc.isEmpty());
    }
}
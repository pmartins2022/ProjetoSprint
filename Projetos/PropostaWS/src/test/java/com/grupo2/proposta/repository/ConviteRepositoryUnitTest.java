package com.grupo2.proposta.repository;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.jpa.mapper.ConviteJPAMapper;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.repository.jpa.ConviteJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class ConviteRepositoryUnitTest
{
    @MockBean
    private ConviteJPARepository jpaRepository;

    @MockBean
    private ConviteJPAMapper mapper;

    @InjectMocks
    private ConviteRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAndSaveConvite()
    {
        //Arrange
        Convite convite = mock(Convite.class);
        ConviteJPA jpa = mock(ConviteJPA.class);

        when(jpaRepository.save(jpa)).thenReturn(jpa);

        when(mapper.toJPA(convite)).thenReturn(jpa);
        when(mapper.toModel(jpa)).thenReturn(convite);

        //Act
        Convite save = repository.createAndSaveConvite(convite);

        //Assert
        assertEquals(convite, save);
    }

    @Test
    public void shouldFindByPropostaAndAluno()
    {
        //Arrange
        Convite convite = mock(Convite.class);
        ConviteJPA jpa = mock(ConviteJPA.class);

        when(jpaRepository.findByIdIdpropostaAndIdIdaluno(1L, 1L)).thenReturn(Optional.of(jpa));

        when(mapper.toModel(jpa)).thenReturn(convite);

        //Act
        Optional<Convite> optional = repository.findByPropostaAndAluno(1L, 1L);

        assertTrue(optional.isPresent());
        assertEquals(convite, optional.get());
    }

    @Test
    public void shouldFindByDocenteAndProposta()
    {
        //Arrange
        Convite convite = mock(Convite.class);
        ConviteJPA jpa = mock(ConviteJPA.class);

        when(jpaRepository.findByIdIdpropostaAndIdIdaluno(1L, 1L)).thenReturn(Optional.of(jpa));

        when(mapper.toModel(jpa)).thenReturn(convite);

        //Act
        Optional<Convite> optional = repository.findByDocenteAndProposta(1L, 1L);

        //Assert
        assertTrue(optional.isPresent());
        assertEquals(convite, optional.get());
    }

    @Test
    public void shouldFindById()
    {
        //Arrange
        Convite convite = mock(Convite.class);
        ConviteID conviteID = mock(ConviteID.class);
        ConviteJPA jpa = mock(ConviteJPA.class);

        when(jpaRepository.findById(conviteID)).thenReturn(Optional.of(jpa));
        when(mapper.toModel(jpa)).thenReturn(convite);
        //Act
        Optional<Convite> id = repository.findById(conviteID);

        //Assert
        assertTrue(id.isPresent());
        assertEquals(convite, id.get());
    }

    @Test
    public void shouldNotFindById()
    {
        //Arrange
        ConviteID conviteID = mock(ConviteID.class);

        when(jpaRepository.findById(conviteID)).thenReturn(Optional.empty());

        //Act
        Optional<Convite> id = repository.findById(conviteID);

        //Assert
        assertTrue(id.isEmpty());
    }

}
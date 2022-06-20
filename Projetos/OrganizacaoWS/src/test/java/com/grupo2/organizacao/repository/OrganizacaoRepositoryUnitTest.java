package com.grupo2.organizacao.repository;

import com.grupo2.organizacao.dto.NifDTO;
import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import com.grupo2.organizacao.jpa.mapper.OrganizacaoJPAMapper;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.repository.jpa.OrganizacaoJPARepository;
import com.grupo2.organizacao.repository.rest.NifRestRepository;
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
class OrganizacaoRepositoryUnitTest
{
    @MockBean
    OrganizacaoJPARepository jpaRepository;

    @MockBean
    OrganizacaoJPAMapper mapper;

    @MockBean
    NifRestRepository nifRestRepository;

    @InjectMocks
    OrganizacaoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    

    @Test
    public void shouldCreateValidOrganizacao()
    {
        //arrange
        Organizacao mockOrganizacao = mock(Organizacao.class);
        OrganizacaoJPA mockOrganizacaoJpa = mock(OrganizacaoJPA.class);

        when(mapper.toModel(mockOrganizacaoJpa)).thenReturn(mockOrganizacao);
        when(mapper.toJpa(mockOrganizacao)).thenReturn(mockOrganizacaoJpa);

        when(jpaRepository.save(mockOrganizacaoJpa)).thenReturn(mockOrganizacaoJpa);
        //act
        Organizacao saveOrganizacao = repository.save(mockOrganizacao);
        //assert
        assertEquals(saveOrganizacao, mockOrganizacao);
    }

    @Test
    public void shouldFindAllOrganizacao()
    {
        Organizacao mockOrganizacao = mock(Organizacao.class);
        OrganizacaoJPA mockOrganizacaoJpa = mock(OrganizacaoJPA.class);

        List<Organizacao> mockList = List.of(mockOrganizacao,mockOrganizacao,mockOrganizacao);
        List<OrganizacaoJPA> mockListJpa = List.of(mockOrganizacaoJpa,mockOrganizacaoJpa,mockOrganizacaoJpa);

        when(jpaRepository.findAll()).thenReturn(mockListJpa);

        when(mapper.toModel(mockOrganizacaoJpa)).thenReturn(mockOrganizacao);

        List<Organizacao> all = repository.findAll();

        assertEquals(all,mockList);
    }

    @Test
    public void shouldFindByNifOrganizacao()
    {
        Organizacao mockOrganizacao = mock(Organizacao.class);
        OrganizacaoJPA mockOrganizacaoJpa = mock(OrganizacaoJPA.class);

        when(mockOrganizacao.getNif()).thenReturn(111222333);
        when(mockOrganizacaoJpa.getNif()).thenReturn(111222333);

        when(jpaRepository.findBynif(111222333)).thenReturn(Optional.of(mockOrganizacaoJpa));

        when(mapper.toModel(mockOrganizacaoJpa)).thenReturn(mockOrganizacao);

        Optional<Organizacao> saveOrganizacao = repository.findByNIF(111222333);

        assertEquals(saveOrganizacao.get(),mockOrganizacao);
    }

    @Test
    public void shouldNotFindByNifOrganizacao_Empty()
    {
        when(jpaRepository.findBynif(111222333)).thenReturn(Optional.empty());

        Optional<Organizacao> saveOrganizacao = repository.findByNIF(111222333);

        assertEquals(Optional.empty(), saveOrganizacao);
    }
}
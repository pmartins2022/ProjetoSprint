package com.grupo2.organizacao.service;

import com.grupo2.organizacao.dto.NifDTO;
import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.dto.mapper.OrganizacaoDTOMapper;
import com.grupo2.organizacao.exception.OptionalVazioException;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.repository.OrganizacaoRepository;
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
class OrganizacaoServiceUnitTest
{
    @MockBean
    OrganizacaoRepository repository;

    @MockBean
    OrganizacaoDTOMapper mapper;

    @MockBean
    NifRestRepository nifRestRepository;

    @InjectMocks
    OrganizacaoService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindOrganizacao()
    {
        Organizacao mockOrganizacao = mock(Organizacao.class);
        OrganizacaoDTO mockOrganizacaoDTO = mock(OrganizacaoDTO.class);

        when(repository.findByID(1L)).thenReturn(Optional.of(mockOrganizacao));
        when(mapper.toDTO(mockOrganizacao)).thenReturn(mockOrganizacaoDTO);

        Optional<OrganizacaoDTO> saved = service.findByID(1L);

        assertEquals(mockOrganizacaoDTO, saved.get());
    }

    @Test
    public void shouldNotFindOrganizacao_NotExists()
    {
        when(repository.findByID(1L)).thenReturn(Optional.empty());

        Optional<OrganizacaoDTO> saved = service.findByID(1L);

        assertEquals(Optional.empty(), saved);
    }

    @Test
    public void shouldCreateValidOrganizacao()
    {
        Organizacao mockOrganizacao = mock(Organizacao.class);
        OrganizacaoDTO mockOrganizacaoDTO = mock(OrganizacaoDTO.class);
        when(mockOrganizacaoDTO.getNif()).thenReturn(111222333);
        NifDTO mockNifDTO = mock(NifDTO.class);

        when(mapper.toModel(mockOrganizacaoDTO)).thenReturn(mockOrganizacao);
        when(mapper.toDTO(mockOrganizacao)).thenReturn(mockOrganizacaoDTO);

        when(nifRestRepository.findByNif(mockOrganizacaoDTO.getNif())).thenReturn(Optional.of(mockNifDTO));
        when(repository.save(mockOrganizacao)).thenReturn(mockOrganizacao);

        OrganizacaoDTO saveOrganizacao = service.createAndSave(mockOrganizacaoDTO);

        assertEquals(saveOrganizacao, mockOrganizacaoDTO);
    }

    @Test
    public void shouldNotCreateValidOrganizacao()
    {
        OrganizacaoDTO mockOrganizacaoDTO = mock(OrganizacaoDTO.class);

        when(nifRestRepository.findByNif(1)).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class,()->service.createAndSave(mockOrganizacaoDTO));
    }

    @Test
    public void shouldFindByNifOrganizacao()
    {
        Organizacao mockOrganizacao = mock(Organizacao.class);
        OrganizacaoDTO mockOrganizacaoDTO = mock(OrganizacaoDTO.class);

        when(mockOrganizacao.getNif()).thenReturn(111222333);
        when(mockOrganizacaoDTO.getNif()).thenReturn(111222333);

        when(repository.findByNIF(111222333)).thenReturn(Optional.of(mockOrganizacao));

        when(mapper.toDTO(mockOrganizacao)).thenReturn(mockOrganizacaoDTO);

        Optional<OrganizacaoDTO> saveOrganizacao = service.findByNIF(111222333);

        assertEquals(saveOrganizacao.get(),mockOrganizacaoDTO);
    }

    @Test
    public void shouldNotFindByNifOrganizacao_Empty()
    {
        when(repository.findByNIF(111222333)).thenReturn(Optional.empty());

        Optional<OrganizacaoDTO> saveOrganizacao = service.findByNIF(111222333);

        assertEquals(Optional.empty(), saveOrganizacao);
    }

    @Test
    public void shouldFindAllOrganizacao()
    {
        Organizacao mockOrganizacao = mock(Organizacao.class);
        OrganizacaoDTO mockOrganizacaoDTO = mock(OrganizacaoDTO.class);

        List<Organizacao> mockList = List.of(mockOrganizacao,mockOrganizacao,mockOrganizacao);
        List<OrganizacaoDTO> mockListDTO = List.of(mockOrganizacaoDTO,mockOrganizacaoDTO,mockOrganizacaoDTO);

        when(repository.findAll()).thenReturn(mockList);

        when(mapper.toDTO(mockOrganizacao)).thenReturn(mockOrganizacaoDTO);

        List<OrganizacaoDTO> all = service.findAll();

        assertEquals(all,mockListDTO);
    }
}
package com.grupo2.organizacao.service;

import com.grupo2.organizacao.dto.NifDTO;
import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.exception.OptionalVazioException;
import com.grupo2.organizacao.repository.rest.NifRestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class OrganizacaoServiceIntegrationTests
{
    @MockBean
    private NifRestRepository nifRestRepository;

    @Autowired
    private OrganizacaoService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateOrganizacao()
    {
        OrganizacaoDTO organizacaoDTO = new OrganizacaoDTO("denominacao",123456789);

        //mock do rest repository apenas
        NifDTO mockNifDTO = mock(NifDTO.class);
        when(mockNifDTO.getName()).thenReturn("Nome");
        when(nifRestRepository.findByNif(organizacaoDTO.getNif())).thenReturn(Optional.of(mockNifDTO));

        OrganizacaoDTO save = service.createAndSave(organizacaoDTO);

        assertEquals(save, organizacaoDTO);
    }

    @Test
    public void shouldNotCreateOrganizacao_InvalidNif()
    {
        OrganizacaoDTO organizacaoDTO = new OrganizacaoDTO("denominacao",123456789);

        //mock do rest repository apenas
        NifDTO mockNifDTO = mock(NifDTO.class);
        when(mockNifDTO.getName()).thenReturn("Nome");
        when(nifRestRepository.findByNif(organizacaoDTO.getNif())).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class,()->service.createAndSave(organizacaoDTO));
    }

    @Test
    public void shouldFindAllOrganizacao()
    {
        OrganizacaoDTO org = new OrganizacaoDTO("denominacao", 123456789);
        OrganizacaoDTO org1 = new OrganizacaoDTO("denominacao1", 987654321);

        //mock do rest repository apenas
        NifDTO mockNifDTO = mock(NifDTO.class);
        when(mockNifDTO.getName()).thenReturn("Nome");
        when(nifRestRepository.findByNif(org.getNif())).thenReturn(Optional.of(mockNifDTO));
        when(nifRestRepository.findByNif(org1.getNif())).thenReturn(Optional.of(mockNifDTO));

        service.createAndSave(org);
        service.createAndSave(org1);

        List<OrganizacaoDTO> all = service.findAll();

        assertEquals(2, all.size());
    }

    @Test
    public void shouldFindById()
    {
        OrganizacaoDTO org = new OrganizacaoDTO("denominacao", 123456789);

        //mock do rest repository apenas
        NifDTO mockNifDTO = mock(NifDTO.class);
        when(mockNifDTO.getName()).thenReturn("Nome");
        when(nifRestRepository.findByNif(org.getNif())).thenReturn(Optional.of(mockNifDTO));

        OrganizacaoDTO save = service.createAndSave(org);

        Optional<OrganizacaoDTO> id = service.findByID(save.getId());

        assertTrue(id.isPresent());
        assertEquals(save, id.get());
    }

    @Test
    public void shouldNotFindById_NotFound()
    {
        Optional<OrganizacaoDTO> id = service.findByID(100L);

        assertTrue(id.isEmpty());
    }

    @Test
    public void shouldFindByNif()
    {
        OrganizacaoDTO org = new OrganizacaoDTO("denominacao", 123456789);

        //mock do rest repository apenas
        NifDTO mockNifDTO = mock(NifDTO.class);
        when(mockNifDTO.getName()).thenReturn("Nome");
        when(nifRestRepository.findByNif(org.getNif())).thenReturn(Optional.of(mockNifDTO));

        OrganizacaoDTO save = service.createAndSave(org);

        Optional<OrganizacaoDTO> nif = service.findByNIF(123456789);

        assertTrue(nif.isPresent());
        assertEquals(save, nif.get());
    }

    @Test
    public void shouldNotFindByNif_NotFound()
    {
        Optional<OrganizacaoDTO> nif = service.findByNIF(22222);

        assertTrue(nif.isEmpty());
    }
}
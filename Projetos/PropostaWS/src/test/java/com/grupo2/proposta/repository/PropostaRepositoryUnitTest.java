package com.grupo2.proposta.repository;

import com.grupo2.proposta.dto.EdicaoUCDTO;
import com.grupo2.proposta.dto.OrganizacaoDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.jpa.mapper.PropostaJPAMapper;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import com.grupo2.proposta.repository.rest.EdicaoUCRestRepository;
import com.grupo2.proposta.repository.rest.OrganizacaoRestRepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
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
class PropostaRepositoryUnitTest
{
    @MockBean
    PropostaJPARepository jpaRepository;

    @MockBean
    PropostaJPAMapper mapper;

    @MockBean
    UtilizadorRestRepository utilizadorRestRepository;

    @MockBean
    EdicaoUCRestRepository edicaoUCRestRepository;

    @MockBean
    OrganizacaoRestRepository organizacaoRestRepository;

    @InjectMocks
    PropostaRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidProposta()
    {
        Proposta propostaMock = mock(Proposta.class);
        PropostaJPA propostaJPAMock = mock(PropostaJPA.class);
        UtilizadorDTO utilizadorDTOMock = mock(UtilizadorDTO.class);
        OrganizacaoDTO organizacaoDTOMock = mock(OrganizacaoDTO.class);
        EdicaoUCDTO edicaoUCDTOMock = mock(EdicaoUCDTO.class);

        when(utilizadorRestRepository.findById(propostaMock.getId())).thenReturn(Optional.of(utilizadorDTOMock));
        when(organizacaoRestRepository.findById(propostaMock.getId())).thenReturn(Optional.of(organizacaoDTOMock));
        when(edicaoUCRestRepository.findById(propostaMock.getId())).thenReturn(Optional.of(edicaoUCDTOMock));

        when(mapper.toModel(propostaJPAMock)).thenReturn(propostaMock);
        when(mapper.toJPA(propostaMock)).thenReturn(propostaJPAMock);

        when(jpaRepository.save(propostaJPAMock)).thenReturn(propostaJPAMock);

        Proposta saved = repository.createProposta(propostaMock);

        assertEquals(saved, propostaMock);
    }

    @Test
    public void shouldNotCreateValidProposta()
    {
        Proposta propostaMock = mock(Proposta.class);

        when(utilizadorRestRepository.findById(propostaMock.getId())).thenReturn(Optional.empty());

        assertThrows(BaseDadosException.class,()->repository.createProposta(propostaMock));
    }

    @Test
    public void shouldNotCreateValidProposta_2()
    {
        Proposta propostaMock = mock(Proposta.class);
        UtilizadorDTO utilizadorDTOMock = mock(UtilizadorDTO.class);

        when(utilizadorRestRepository.findById(propostaMock.getId())).thenReturn(Optional.of(utilizadorDTOMock));
        when(organizacaoRestRepository.findById(propostaMock.getId())).thenReturn(Optional.empty());

        assertThrows(BaseDadosException.class,()->repository.createProposta(propostaMock));
    }

    @Test
    public void shouldNotCreateValidProposta_3()
    {
        Proposta propostaMock = mock(Proposta.class);
        UtilizadorDTO utilizadorDTOMock = mock(UtilizadorDTO.class);
        OrganizacaoDTO organizacaoDTOMock = mock(OrganizacaoDTO.class);

        when(utilizadorRestRepository.findById(propostaMock.getId())).thenReturn(Optional.of(utilizadorDTOMock));
        when(organizacaoRestRepository.findById(propostaMock.getId())).thenReturn(Optional.of(organizacaoDTOMock));
        when(edicaoUCRestRepository.findById(propostaMock.getId())).thenReturn(Optional.empty());

        assertThrows(BaseDadosException.class,()->repository.createProposta(propostaMock));
    }

    @Test
    public void shouldFindAnoLetivo_Exists()
    {
        Proposta propostaMock = mock(Proposta.class);
        PropostaJPA propostaJPAMock = mock(PropostaJPA.class);

        when(propostaMock.getId()).thenReturn(1L);
        when(propostaJPAMock.getId()).thenReturn(1L);

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(propostaJPAMock));

        when(mapper.toModel(propostaJPAMock)).thenReturn(propostaMock);

        Optional<Proposta> saved = repository.findById(1L);

        assertEquals(saved.get(),propostaMock);
    }

    @Test
    public void shouldNotFindAnoLetivo_NotExists()
    {
        when(jpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Proposta> saved = repository.findById(1L);

        assertEquals(Optional.empty(), saved);
    }
}
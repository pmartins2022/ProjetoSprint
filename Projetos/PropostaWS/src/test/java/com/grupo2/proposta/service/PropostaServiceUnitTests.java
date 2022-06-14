package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.OrganizacaoDTO;
import com.grupo2.proposta.dto.ProjetoDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import com.grupo2.proposta.dto.factory.ProjetoDTOFactory;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.TipoUtilizador;
import com.grupo2.proposta.repository.PropostaRepository;
import com.grupo2.proposta.repository.rest.ProjetoRestRepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PropostaServiceUnitTests
{
    @MockBean
    private PropostaRepository repository;
    @MockBean
    private UtilizadorRestRepository utilizadorRestRepository;
    @MockBean
    private ProjetoRestRepository projetoRestRepository;
    @MockBean
    private PropostaDTOMapper mapper;
    @MockBean
    private ProjetoDTOFactory projetoDTOFactory;

    @InjectMocks
    private PropostaService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateProposta()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        Proposta proposta = mock(Proposta.class);

        when(mapper.toModel(prop)).thenReturn(proposta);
        when(mapper.toDTO(proposta)).thenReturn(prop);

        when(repository.save(proposta)).thenReturn(proposta);

        PropostaDTO dto = service.createProposta(prop,"");

        assertEquals(prop, dto);
    }

    @Test
    public void shouldNotCreateProposta_invalid()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        Proposta proposta = mock(Proposta.class);

        when(mapper.toModel(prop)).thenReturn(proposta);
        when(mapper.toDTO(proposta)).thenReturn(prop);

        when(repository.save(proposta)).thenThrow(BaseDadosException.class);

        assertThrows(BaseDadosException.class, () -> service.createProposta(prop,""));
    }

    @Test
    public void shouldAcceptProposta()
    {
        Proposta proposta = mock(Proposta.class);

        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);
        UtilizadorDTO utilizadorDTOAl = mock(UtilizadorDTO.class);

        ProjetoDTO projetoDTO = mock(ProjetoDTO.class);

        when(repository.findById(1L)).thenReturn(Optional.of(proposta));

        when(utilizadorRestRepository.findById(1L, "aaa")).thenReturn(Optional.of(utilizadorDTO));
        when(utilizadorRestRepository.findById(2L, "aaa")).thenReturn(Optional.of(utilizadorDTOAl));

        when(utilizadorDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.ORIENTADOR);
        when(utilizadorDTOAl.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        when(projetoDTOFactory.createProjeto(1L, 1L, 2L)).thenReturn(projetoDTO);
        when(projetoRestRepository.create(projetoDTO)).thenReturn(projetoDTO);

        ProjetoDTO acceptProposta = service.acceptCandidaturaProposta(1L, 1L, 2L);

        assertEquals(acceptProposta, projetoDTO);
    }

    @Test
    public void shouldNotAcceptProposta_invalidID()
    {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IdInvalidoException.class, () -> service.acceptCandidaturaProposta(1L, 1L, 2L));
    }

    @Test
    public void shouldNotAcceptProposta_invalidUpdate()
    {
        Proposta proposta = mock(Proposta.class);

        when(repository.findById(1L)).thenReturn(Optional.of(proposta));

        doThrow(new AtualizacaoInvalidaException()).when(proposta).aprovarProposta();

        assertThrows(AtualizacaoInvalidaException.class, () -> service.acceptCandidaturaProposta(1L, 1L, 2L));
    }

    @Test
    public void shouldNotAcceptProposta_invalidUserIdOrientador()
    {
        Proposta proposta = mock(Proposta.class);

        when(repository.findById(1L)).thenReturn(Optional.of(proposta));
        when(utilizadorRestRepository.findById(1L, "aaa")).thenReturn(Optional.empty());

        assertThrows(IdInvalidoException.class, () -> service.acceptCandidaturaProposta(1L, 1L, 2L));
    }

    @Test
    public void shouldNotAcceptProposta_invalidUserIdAlun()
    {
        Proposta proposta = mock(Proposta.class);

        UtilizadorDTO orDto = mock(UtilizadorDTO.class);

        when(repository.findById(1L)).thenReturn(Optional.of(proposta));
        when(utilizadorRestRepository.findById(1L, "aaa")).thenReturn(Optional.of(orDto));
        when(orDto.getTipoUtilizador()).thenReturn(TipoUtilizador.ORIENTADOR);
        when(utilizadorRestRepository.findById(2L, "aaa")).thenReturn(Optional.empty());

        assertThrows(IdInvalidoException.class, () -> service.acceptCandidaturaProposta(1L, 1L, 2L));
    }

    @Test
    public void shouldNotAcceptProposta_wrongUserTypeOrientador()
    {
        Proposta proposta = mock(Proposta.class);
        UtilizadorDTO orientadorDTO = mock(UtilizadorDTO.class);

        when(orientadorDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);

        when(utilizadorRestRepository.findById(1L, "aaa")).thenReturn(Optional.of(orientadorDTO));

        when(repository.findById(1L)).thenReturn(Optional.of(proposta));

        assertThrows(IdInvalidoException.class, () -> service.acceptCandidaturaProposta(1L, 1L, 2L));
    }

    @Test
    public void shouldNotAcceptProposta_wrongUserTypeAluno()
    {
        Proposta proposta = mock(Proposta.class);
        UtilizadorDTO alunoDTO = mock(UtilizadorDTO.class);
        UtilizadorDTO orDTO = mock(UtilizadorDTO.class);

        when(orDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.ORIENTADOR);
        when(alunoDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.DOCENTE);

        when(utilizadorRestRepository.findById(1L, "aaa")).thenReturn(Optional.of(orDTO));
        when(utilizadorRestRepository.findById(2L, "aaa")).thenReturn(Optional.of(alunoDTO));

        when(repository.findById(1L)).thenReturn(Optional.of(proposta));

        assertThrows(IdInvalidoException.class, () -> service.acceptCandidaturaProposta(1L, 1L, 2L));
    }

    @Test
    public void shouldRejectProposta()
    {
        PropostaDTO propDTO = mock(PropostaDTO.class);
        Proposta prop = mock(Proposta.class);

        when(repository.findById(1L)).thenReturn(Optional.of(prop));

        when(repository.atualizarProposta(prop)).thenReturn(Optional.of(prop));

        when(mapper.toDTO(prop)).thenReturn(propDTO);

        Optional<PropostaDTO> proposta = service.rejeitarProposta(1L);

        assertTrue(proposta.isPresent());

        assertEquals(proposta.get(),propDTO);
    }

    @Test
    public void shouldNotRejectProposta_invalidID()
    {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<PropostaDTO> optional = service.rejeitarProposta(1L);

        assertTrue(optional.isEmpty());
    }

    @Test
    public void shouldNotRejectProposta_invalidUpdate()
    {
        Proposta prop = mock(Proposta.class);

        when(repository.findById(1L)).thenReturn(Optional.of(prop));

        doThrow(new AtualizacaoInvalidaException()).when(prop).reprovarProposta();

        assertThrows(AtualizacaoInvalidaException.class,()->service.rejeitarProposta(1L));
    }

    @Test
    public void shouldFindByID()
    {
        Proposta prop = mock(Proposta.class);
        PropostaDTO propDTO = mock(PropostaDTO.class);

        List<Proposta> list = List.of(prop,prop,prop);

        when(repository.findByIdUtilizador(1L)).thenReturn(list);

        when(mapper.toDTO(prop)).thenReturn(propDTO);

        List<PropostaDTO> dtos = service.findByIdUtilizador(1L);

        assertEquals(dtos.size(),list.size());
    }

    @Test
    public void shouldFindByID_Empty()
    {
        List<Proposta> list = List.of();

        when(repository.findByIdUtilizador(1L)).thenReturn(list);

        List<PropostaDTO> dtos = service.findByIdUtilizador(1L);

        assertTrue(dtos.isEmpty());
    }

    @Test
    public void shouldFindByNif()
    {
        Proposta prop = mock(Proposta.class);
        PropostaDTO propDTO = mock(PropostaDTO.class);

        OrganizacaoDTO dto = mock(OrganizacaoDTO.class);
        when(dto.getNif()).thenReturn(1L);

        List<Proposta> list = List.of(prop,prop,prop);

        when(repository.findByNif(dto)).thenReturn(list);

        when(mapper.toDTO(prop)).thenReturn(propDTO);

        List<PropostaDTO> saved = service.findByNif(1,"");

        assertEquals(list.size(),saved.size());
    }

    @Test
    public void shouldFindByNif_Empty()
    {
        List<Proposta> list = List.of();

        OrganizacaoDTO org = mock(OrganizacaoDTO.class);

        when(org.getNif()).thenReturn(1L);

        when(repository.findByNif(org)).thenReturn(list);

        List<PropostaDTO> nif = service.findByNif(1,"");

        assertTrue(nif.isEmpty());
    }

    @Test
    public void shouldGetAllByTitulo()
    {
        Proposta prop = mock(Proposta.class);
        PropostaDTO propDTO = mock(PropostaDTO.class);

        List<Proposta> list = List.of(prop,prop,prop);

        when(repository.findAllByTitulo("AAA")).thenReturn(list);

        when(mapper.toDTO(prop)).thenReturn(propDTO);

        List<PropostaDTO> dtos = service.findByTitulo("AAA");

        assertEquals(list.size(),dtos.size());
    }

    @Test
    public void shouldGetAllByTitulo_Empty()
    {
        List<Proposta> list = List.of();

        when(repository.findAllByTitulo("AAA")).thenReturn(list);

        List<PropostaDTO> aaa = service.findByTitulo("AAA");

        assertTrue(aaa.isEmpty());
    }
}
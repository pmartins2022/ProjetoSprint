package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.*;
import com.grupo2.proposta.dto.factory.ProjetoDTOFactory;
import com.grupo2.proposta.dto.mapper.ConviteDTOMapper;
import com.grupo2.proposta.dto.mapper.PropostaCandidaturaDTOMapper;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.model.*;
import com.grupo2.proposta.model.factory.ConviteIDFactory;
import com.grupo2.proposta.repository.ConviteRepository;
import com.grupo2.proposta.repository.PropostaCandidaturaRepo;
import com.grupo2.proposta.repository.PropostaRepository;
import com.grupo2.proposta.repository.rest.EdicaoUCRestRepository;
import com.grupo2.proposta.repository.rest.OrganizacaoRestRepository;
import com.grupo2.proposta.repository.rest.ProjetoRestRepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
import com.grupo2.proposta.security.LoginContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
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
    @MockBean
    private OrganizacaoRestRepository organizacaoRestRepository;
    @MockBean
    private ConviteRepository conviteRepository;
    @MockBean
    private EdicaoUCRestRepository edicaoUCRestRepository;
    @MockBean
    private ConviteDTOMapper conviteDTOMapper;
    @MockBean
    private PropostaCandidaturaRepo propostaCandidaturaRepo;
    @MockBean
    private PropostaCandidaturaDTOMapper candidaturaDTOMapper;
    @MockBean
    private ConviteIDFactory conviteIDFactory;

    private static MockedStatic<LoginContext> loginContext;

    @InjectMocks
    private PropostaService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    static void setUpBeforeClass()
    {
        loginContext = org.mockito.Mockito.mockStatic(LoginContext.class);
    }


    @Test
    public void shouldNotCreateProposta_invalid()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        Proposta proposta = mock(Proposta.class);

        when(mapper.toModel(prop)).thenReturn(proposta);
        when(mapper.toDTO(proposta)).thenReturn(prop);

        when(repository.save(proposta)).thenThrow(BaseDadosException.class);

        assertThrows(BaseDadosException.class, () -> service.createCandidaturaProposta(prop));
    }

    @Test
    public void shouldAcceptCandidaturaProposta()
    {
        Proposta proposta = mock(Proposta.class);
        PropostaDTO mock = mock(PropostaDTO.class);

        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);
        UtilizadorDTO utilizadorDTOAl = mock(UtilizadorDTO.class);

        EdicaoUCDTO edicaoUCDTO = mock(EdicaoUCDTO.class);

        when(edicaoUCRestRepository.findByRucID(1L)).thenReturn(List.of(edicaoUCDTO));
        when(repository.findById(1L)).thenReturn(Optional.of(proposta));

        when(proposta.getEdicaoUCId()).thenReturn(1L);
        when(proposta.getEstadoAtual()).thenReturn(PropostaEstado.CANDIDATURA);
        when(edicaoUCDTO.getId()).thenReturn(1L);

        when(repository.save(proposta)).thenReturn(proposta);

        when(mapper.toDTO(proposta)).thenReturn(mock);

        PropostaDTO acceptProposta = service.acceptCandidaturaProposta(1L, 1L);

        assertEquals(mock, acceptProposta);
    }

    @Test
    public void shouldNotAcceptCandidaturaProposta_invalidRUCID()
    {
        when(edicaoUCRestRepository.findByRucID(1L)).thenReturn(List.of());

        assertThrows(OptionalVazioException.class, () -> service.acceptCandidaturaProposta(1L, 1L));
    }

    @Test
    public void shouldNotAcceptCandidaturaProposta_invalidPropostaID()
    {
        EdicaoUCDTO dto = mock(EdicaoUCDTO.class);

        when(edicaoUCRestRepository.findByRucID(1L)).thenReturn(List.of(dto));

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OptionalVazioException.class, () -> service.acceptCandidaturaProposta(1L, 1L));
    }

    @Test
    public void shouldRejectCandidaturaProposta()
    {
        PropostaDTO propDTO = mock(PropostaDTO.class);
        Proposta prop = mock(Proposta.class);

        when(repository.findById(1L)).thenReturn(Optional.of(prop));

        when(repository.atualizarProposta(prop)).thenReturn(Optional.of(prop));

        when(mapper.toDTO(prop)).thenReturn(propDTO);

        Optional<PropostaDTO> proposta = service.rejeitarCandidaturaProposta(1L);

        assertTrue(proposta.isPresent());

        assertEquals(proposta.get(),propDTO);
    }

    @Test
    public void shouldNotRejectProposta_invalidID()
    {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<PropostaDTO> optional = service.rejeitarCandidaturaProposta(1L);

        assertTrue(optional.isEmpty());
    }

    @Test
    public void shouldNotRejectProposta_invalidUpdate()
    {
        Proposta prop = mock(Proposta.class);

        when(repository.findById(1L)).thenReturn(Optional.of(prop));

        doThrow(new AtualizacaoInvalidaException()).when(prop).reprovarProposta();

        assertThrows(AtualizacaoInvalidaException.class,()->service.rejeitarCandidaturaProposta(1L));
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

        when(organizacaoRestRepository.findByNIF(1234,"")).thenReturn(Optional.of(dto));

        when(repository.findByNif(dto)).thenReturn(list);

        when(mapper.toDTO(prop)).thenReturn(propDTO);

        List<PropostaDTO> saved = service.findByNif(1234,"");

        assertEquals(list.size(),saved.size());
    }

    @Test
    public void shouldFindByNif_Empty()
    {
        OrganizacaoDTO org = mock(OrganizacaoDTO.class);

        when(organizacaoRestRepository.findById(1L)).thenReturn(Optional.of(org));

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

    @Test
    public void shouldAcceptOrientacaoProposta()
    {
        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);
        Convite convite = mock(Convite.class);
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(utilizadorRestRepository.findById(1L)).thenReturn(Optional.of(utilizadorDTO));
        when(conviteRepository.findByDocenteAndProposta(1L,1L)).thenReturn(Optional.of(convite));
        when(conviteDTOMapper.toDTO(convite)).thenReturn(conviteDTO);
        when(utilizadorDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.DOCENTE);
        when(convite.getEstado()).thenReturn(EstadoConvite.PENDENTE);

        ConviteDTO dto = service.acceptOrientacaoProposta(conviteDTO, "");

        assertEquals(dto,conviteDTO);
    }

    @Test
    public void shouldRejectOrientacaoProposta()
    {
        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);
        Convite convite = mock(Convite.class);
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(utilizadorRestRepository.findById(1L)).thenReturn(Optional.of(utilizadorDTO));
        when(conviteRepository.findByDocenteAndProposta(1L,1L)).thenReturn(Optional.of(convite));
        when(conviteDTOMapper.toDTO(convite)).thenReturn(conviteDTO);
        when(utilizadorDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.DOCENTE);
        when(convite.getEstado()).thenReturn(EstadoConvite.PENDENTE);

        ConviteDTO dto = service.rejectOrientacaoProposta(conviteDTO, "");

        assertEquals(dto,conviteDTO);
    }

    @Test
    public void shouldCreateConvite()
    {
        Convite conv = mock(Convite.class);
        ConviteDTO conviteDTO = mock(ConviteDTO.class);
        Proposta proposta = mock(Proposta.class);
        UtilizadorDTO alunoDTO = mock(UtilizadorDTO.class);
        UtilizadorDTO docenteDTO = mock(UtilizadorDTO.class);

        when(proposta.getEstadoAtual()).thenReturn(PropostaEstado.APROVADO);

        when(conviteDTO.getIdProposta()).thenReturn(1L);
        when(conviteDTO.getIdAluno()).thenReturn(1L);

        when(alunoDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.ALUNO);
        when(docenteDTO.getTipoUtilizador()).thenReturn(TipoUtilizador.DOCENTE);

        when(propostaCandidaturaRepo.isIncrito(conviteDTO.getIdProposta(), conviteDTO.getIdAluno())).thenReturn(true);

        when(repository.findById(conviteDTO.getIdProposta())).thenReturn(Optional.of(proposta));

        when(utilizadorRestRepository.findById(conviteDTO.getIdAluno())).thenReturn(Optional.of(alunoDTO));
        when(utilizadorRestRepository.findById(conviteDTO.getIdDocente())).thenReturn(Optional.of(docenteDTO));

        when(conviteDTOMapper.toModel(conviteDTO)).thenReturn(conv);

        when(conviteRepository.createAndSaveConvite(conv)).thenReturn(conv);

        when(conviteDTOMapper.toDTO(conv)).thenReturn(conviteDTO);

        ConviteDTO convite = service.createConvite(conviteDTO);

        assertEquals(convite,conviteDTO);
    }

    @Test
    public void shouldCandidatarAlunoProposta()
    {
        Proposta proposta = mock(Proposta.class);
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaDTO propostaCandidaturaDTO = mock(PropostaCandidaturaDTO.class);
        UtilizadorAuthDTO utilizadorAuthDTO = mock(UtilizadorAuthDTO.class);

        when(utilizadorAuthDTO.getId()).thenReturn(1L);

        when(proposta.getEstadoAtual()).thenReturn(PropostaEstado.APROVADO);

        when(repository.findById(1L)).thenReturn(Optional.of(proposta));

        loginContext.when(LoginContext::getCurrent).thenReturn(utilizadorAuthDTO);

        when(propostaCandidaturaRepo.isAlunoInscrito(1L)).thenReturn(false);

        when(propostaCandidaturaRepo.createAndSave(1L, 1L)).thenReturn(propostaCandidatura);

        when(candidaturaDTOMapper.toDTO(propostaCandidatura)).thenReturn(propostaCandidaturaDTO);

        PropostaCandidaturaDTO dto = service.candidatarAlunoProposta(1L);

        assertEquals(dto,propostaCandidaturaDTO);
    }

    @Test
    public void shouldAcceptProposta()
    {
        Proposta proposta = mock(Proposta.class);
        PropostaDTO propostaDTO = mock(PropostaDTO.class);
        ConviteID conviteID = mock(ConviteID.class);
        Convite convite = mock(Convite.class);
        ProjetoDTO projetoDTO = mock(ProjetoDTO.class);

        when(repository.findById(1L)).thenReturn(Optional.of(proposta));
        when(proposta.getEstadoAtual()).thenReturn(PropostaEstado.APROVADO);

        when(utilizadorRestRepository.isRole("ROLE_ORIENTADOR", 1L)).thenReturn(true);
        when(utilizadorRestRepository.isRole("ROLE_ALUNO", 2L)).thenReturn(true);
        when(conviteIDFactory.create(2L,1L,1L)).thenReturn(conviteID);
        when(conviteRepository.findById(conviteID)).thenReturn(Optional.of(convite));
        when(convite.getEstado()).thenReturn(EstadoConvite.ACEITE);

        when(projetoDTOFactory.createProjeto(1L,2L,1L)).thenReturn(projetoDTO);
        when(projetoRestRepository.create(projetoDTO)).thenReturn(projetoDTO);

        ProjetoDTO proj = service.acceptProposta(1L, 1L, 2L);

        assertEquals(proj,projetoDTO);
    }

    @Test
    public void shouldAcceptAlunoCandidaturaProposta()
    {
        PropostaCandidaturaIDDTO propostaCandidaturaIDDTO = mock(PropostaCandidaturaIDDTO.class);
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaDTO propostaCandidaturaDTO = mock(PropostaCandidaturaDTO.class);

        Proposta proposta = mock(Proposta.class);

        when(proposta.getId()).thenReturn(1L);
        when(propostaCandidaturaID.getidproposta()).thenReturn(1L);
        when(propostaCandidaturaIDDTO.getIdProposta()).thenReturn(1L);

        List<Proposta> list = List.of(proposta);

        EdicaoUCDTO edicaoUCDTO = mock(EdicaoUCDTO.class);

        when(edicaoUCDTO.getId()).thenReturn(1L);

        when(candidaturaDTOMapper.toModelID(propostaCandidaturaIDDTO)).thenReturn(propostaCandidaturaID);
        when(propostaCandidaturaRepo.findByID(propostaCandidaturaID)).thenReturn(Optional.of(propostaCandidatura));
        when(propostaCandidatura.getEstado()).thenReturn(EstadoCandidatura.PENDENTE);

        when(edicaoUCRestRepository.findByRucIDAndEstadoEdicaoUC(1L)).thenReturn(Optional.of(edicaoUCDTO));

        when(repository.findByEdicaoUCId(1L)).thenReturn(list);

        when(propostaCandidaturaRepo.updateAndSave(propostaCandidatura)).thenReturn(propostaCandidatura);

        when(candidaturaDTOMapper.toDTO(propostaCandidatura)).thenReturn(propostaCandidaturaDTO);

        PropostaCandidaturaDTO candidaturaDTO = service.acceptAlunoCandidaturaProposta(1L, propostaCandidaturaIDDTO);

        assertEquals(candidaturaDTO,propostaCandidaturaDTO);
    }

    @Test
    public void shouldARejectAlunoCandidaturaProposta()
    {
        PropostaCandidaturaIDDTO propostaCandidaturaIDDTO = mock(PropostaCandidaturaIDDTO.class);
        PropostaCandidaturaID propostaCandidaturaID = mock(PropostaCandidaturaID.class);
        PropostaCandidatura propostaCandidatura = mock(PropostaCandidatura.class);
        PropostaCandidaturaDTO propostaCandidaturaDTO = mock(PropostaCandidaturaDTO.class);

        Proposta proposta = mock(Proposta.class);

        when(proposta.getId()).thenReturn(1L);
        when(propostaCandidaturaID.getidproposta()).thenReturn(1L);
        when(propostaCandidaturaIDDTO.getIdProposta()).thenReturn(1L);


        List<Proposta> list = List.of(proposta);

        EdicaoUCDTO edicaoUCDTO = mock(EdicaoUCDTO.class);

        when(edicaoUCDTO.getId()).thenReturn(1L);

        when(candidaturaDTOMapper.toModelID(propostaCandidaturaIDDTO)).thenReturn(propostaCandidaturaID);
        when(propostaCandidaturaRepo.findByID(propostaCandidaturaID)).thenReturn(Optional.of(propostaCandidatura));
        when(propostaCandidatura.getEstado()).thenReturn(EstadoCandidatura.PENDENTE);

        when(edicaoUCRestRepository.findByRucIDAndEstadoEdicaoUC(1L)).thenReturn(Optional.of(edicaoUCDTO));

        when(repository.findByEdicaoUCId(1L)).thenReturn(list);

        when(propostaCandidaturaRepo.updateAndSave(propostaCandidatura)).thenReturn(propostaCandidatura);

        when(candidaturaDTOMapper.toDTO(propostaCandidatura)).thenReturn(propostaCandidaturaDTO);

        PropostaCandidaturaDTO candidaturaDTO = service.rejectAlunoCandidaturaProposta(1L, propostaCandidaturaIDDTO);

        assertEquals(candidaturaDTO,propostaCandidaturaDTO);
    }

    @Test
    public void shouldFindAllByEstado()
    {
        Proposta proposta = mock(Proposta.class);
        when(repository.findAllByEstado(1)).thenReturn(List.of(proposta));
        List<PropostaDTO> estado = service.findAllByEstado(1);
        assertEquals(1, estado.size());
    }

    @Test
    public void shouldRejectProposta()
    {
        Proposta proposta = mock(Proposta.class);

        when(proposta.getEstadoAtual()).thenReturn(PropostaEstado.CANDIDATURA);
        when(repository.findById(1L)).thenReturn(Optional.of(proposta));

        assertDoesNotThrow(()->service.rejectProposta(1L));
    }

}
package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.*;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.exception.ValidacaoInvalidaException;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.model.*;
import com.grupo2.proposta.model.factory.PropostaFactory;
import com.grupo2.proposta.repository.ConviteRepository;
import com.grupo2.proposta.repository.PropostaRepository;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import com.grupo2.proposta.repository.rest.EdicaoUCRestRepository;
import com.grupo2.proposta.repository.rest.OrganizacaoRestRepository;
import com.grupo2.proposta.repository.rest.ProjetoRestRepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class PropostaServiceIntegrationTests
{
    @Autowired
    @InjectMocks
    private PropostaService service;

    @Autowired
    private PropostaFactory factory;

    @Autowired
    private PropostaDTOMapper mapper;

    @Autowired
    private PropostaRepository repository;

    @MockBean
    private OrganizacaoRestRepository organizacaoRestRepository;

    @Autowired
    private ConviteRepository conviteRepository;

    @MockBean
    private ProjetoRestRepository projetoRestRepository;

    @Autowired
    private PropostaJPARepository jpaRepository;

    @MockBean
    private EdicaoUCRestRepository edicaoUCRestRepository;

    @MockBean
    private UtilizadorRestRepository utilizadorRestRepository;


    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldNotAcceptProposta_InvalidID()
    {
        assertThrows(OptionalVazioException.class,()->service.acceptCandidaturaProposta(99L,1L));
    }


    @Test
    public void shouldNotAcceptProposta_InvalidRUCID()
    {
        PropostaJPA p = new PropostaJPA(1L,1L,1L,"AAAAAAAAAA","BBBBBBBBBB","CCCCCCCCCC",1L,PropostaEstado.APROVADO);
        PropostaJPA save = jpaRepository.save(p);

        assertThrows(OptionalVazioException.class,()->service.acceptCandidaturaProposta(save.getId(),2L));
    }

    @Test
    public void shouldRejectProposta()
    {
        PropostaJPA p = new PropostaJPA(1L,1L,1L,"AAAAAAAAAA","BBBBBBBBBB","CCCCCCCCCC",1L,PropostaEstado.CANDIDATURA);
        PropostaJPA save = jpaRepository.save(p);

        Optional<PropostaDTO> dto = service.rejeitarCandidaturaProposta(save.getId());

        assertTrue(dto.isPresent());
        assertEquals(PropostaEstado.REPROVADO,dto.get().getEstadoAtual());
    }

    @Test
    public void shouldNotRejectProposta_InvalidID()
    {
        Optional<PropostaDTO> dto = service.rejeitarCandidaturaProposta(99L);
        assertTrue(dto.isEmpty());
    }

    @Test
    public void shouldNotRejectProposta_InvalidUpdate()
    {
        PropostaJPA p = new PropostaJPA(1L,1L,1L,"AAAAAAAAAA","BBBBBBBBBB","CCCCCCCCCC",1L,PropostaEstado.APROVADO);
        PropostaJPA save = jpaRepository.save(p);

        assertThrows(AtualizacaoInvalidaException.class,()->service.rejeitarCandidaturaProposta(save.getId()));
    }

    @Test
    public void shouldFindByIDUtilizador()
    {
        PropostaJPA p = new PropostaJPA(1L,1L,1L,"AAAAAAAAAA","BBBBBBBBBB","CCCCCCCCCC",1L,PropostaEstado.CANDIDATURA);
        PropostaJPA save = jpaRepository.save(p);

        List<PropostaDTO> dto = service.findByIdUtilizador(1L);

        assertEquals(1,dto.size());
    }

    @Test
    public void shouldNotFindByIDUtilizador_InvalidID()
    {
        List<PropostaDTO> dto = service.findByIdUtilizador(99L);
        assertTrue(dto.isEmpty());
    }

    @Test
    public void shouldFindByTitulo()
    {
        PropostaJPA p = new PropostaJPA(1L,1L,1L,"AAABBBCCCD","BBBBBBBBBB","CCCCCCCCCC",1L,PropostaEstado.CANDIDATURA);
        PropostaJPA save = jpaRepository.save(p);

        List<PropostaDTO> dto = service.findByTitulo("AAABBBCCCD");

        assertEquals(1,dto.size());
    }

    @Test
    public void shouldNotFindByTitulo_InvalidID()
    {
        List<PropostaDTO> dto = service.findByTitulo("AFASFAAFS");
        assertTrue(dto.isEmpty());
    }

    @Test
    public void shouldCreateCandidaturaProposta()
    {
        Proposta proposta = new Proposta(1L, 1L, 1L, "tituloMuitaGiro",
                "problemaMuitaGrave", "objetivoQueSeVaiRealizando", 1L, PropostaEstado.CANDIDATURA);

        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L, "nome", "sobrenome", "email@email.pt",
                TipoUtilizador.ALUNO);

        OrganizacaoDTO organizacaoDTO = new OrganizacaoDTO(1L, "denominacao", 123456789L);

        EdicaoUCDTO edicaoUCDTO = new EdicaoUCDTO(1L, "unidadeCurricular", "1990-1991", 1L);

        when(utilizadorRestRepository.findById(proposta.getUtilizadorId())).thenReturn(Optional.of(utilizadorDTO));
        when(organizacaoRestRepository.findById(proposta.getOrganizacaoId())).thenReturn(Optional.of(organizacaoDTO));
        when(edicaoUCRestRepository.findById(proposta.getEdicaoUCId())).thenReturn(Optional.of(edicaoUCDTO));

        Proposta save = repository.save(proposta);

        assertEquals(proposta, save);
    }

    @Test
    public void shouldFindByNif()
    {

        OrganizacaoDTO organizacaoDTO = new OrganizacaoDTO(1L, "denominacao", 123456789L);

        Proposta proposta = new Proposta(1L, 1L, 1L, "tituloMuitaGiro",
                "problemaMuitaGrave", "objetivoQueSeVaiRealizando", 1L, PropostaEstado.CANDIDATURA);


        Proposta save = repository.save(proposta);

        when(organizacaoRestRepository.findByNIF(123456789, "encode")).thenReturn(Optional.of(organizacaoDTO));

        List<Proposta> list = repository.findByNif(organizacaoDTO);

        assertEquals(list.size(), 1);
    }

    @Test
    public void shouldAcceptOrientacaoProposta()
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L, "nome", "sobrenome", "email@email.pt",
                TipoUtilizador.DOCENTE);
        Proposta proposta = new Proposta(1L, 1L, 1L, "tituloMuitaGiro",
                "problemaMuitaGrave", "objetivoQueSeVaiRealizando", 1L, PropostaEstado.CANDIDATURA);

        Convite convite = new Convite(1L, 1L, 1L, EstadoConvite.ACEITE);

        when(utilizadorRestRepository.findById(1L)).thenReturn(Optional.of(utilizadorDTO));
        when(conviteRepository.findByDocenteAndProposta(1L, 1L)).thenReturn(Optional.of(convite));

        assertEquals(convite.getEstado(), EstadoConvite.ACEITE);
    }

    @Test
    public void shouldAtualizarProposta()
    {
        PropostaJPA propostaJPA = new PropostaJPA(1L, 1L, 1L, "tituloMuitaGiro",
                "problemaMuitaGrave", "objetivoQueSeVaiRealizando", 1L, PropostaEstado.CANDIDATURA);

        Proposta proposta = new Proposta(1L, 1L, 1L, "tituloMuitaGiro",
                "problemaMuitaGrave", "objetivoQueSeVaiRealizando", 1L, PropostaEstado.CANDIDATURA);

        jpaRepository.save(propostaJPA);
        proposta.aprovarProposta();

        Optional<PropostaDTO> save = service.atualizarProposta(proposta);

        Optional<PropostaJPA> newSave = jpaRepository.findById(save.get().getId());

        assertEquals(newSave.get().getEstadoAtual(), PropostaEstado.APROVADO);
    }

    @Test
    public void shouldCreateProject()
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(1L, 1L, 1L, 1L);

        when(projetoRestRepository.create(projetoDTO)).thenReturn(projetoDTO);

        ProjetoDTO save = service.createProject(1L, 1L, 1L);

        assertEquals(projetoDTO, save);
    }

    @Test
    public void shouldCreateCovite()
    {
        ConviteDTO convite = new ConviteDTO(1L, 1L, 1L, EstadoConvite.PENDENTE);

        repository.findById(convite.getIdProposta());


        PropostaDTO proposta = new PropostaDTO(1L, 1L, 1L, "tituloMuitaGiro",
                "problemaMuitaGrave", "objetivoQueSeVaiRealizando", 1L, PropostaEstado.APROVADO);

        UtilizadorDTO alunoDTO = new UtilizadorDTO(1L, "nome", "sobrenome", "email@email.pt",
                TipoUtilizador.ALUNO);

        UtilizadorDTO docenteDTO = new UtilizadorDTO(1L, "nome2", "sobrenome2", "email2@email.pt",
                TipoUtilizador.DOCENTE);


        service.createCandidaturaProposta(proposta);

        when(utilizadorRestRepository.findById(1L)).thenReturn(Optional.of(alunoDTO));
        when(utilizadorRestRepository.findById(1L)).thenReturn(Optional.of(docenteDTO));

        ConviteDTO save = service.createConvite(convite);

        assertEquals(convite, save);

    }

   /* public ConviteDTO createConvite(ConviteDTO conviteDTO)
    {
        Optional<Proposta> proposta = repository.findById(conviteDTO.getIdProposta());

        Convite convite = conviteRepository.createAndSaveConvite(conviteDTOMapper.toModel(conviteDTO));

        return conviteDTOMapper.toDTO(convite);
    }*/
}
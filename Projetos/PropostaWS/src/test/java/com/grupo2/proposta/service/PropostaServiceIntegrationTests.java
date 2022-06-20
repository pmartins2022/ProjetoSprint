package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.*;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.model.*;
import com.grupo2.proposta.model.factory.PropostaFactory;
import com.grupo2.proposta.repository.ConviteRepository;
import com.grupo2.proposta.repository.PropostaRepository;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import com.grupo2.proposta.repository.rest.EdicaoUCRestRepository;
import com.grupo2.proposta.repository.rest.OrganizacaoRestRepository;
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
    @MockBean
    private EdicaoUCRestRepository edicaoUCRestRepository;

    @MockBean
    private UtilizadorRestRepository utilizadorRestRepository;

    @MockBean
    private OrganizacaoRestRepository organizacaoRestRepository;

    @MockBean
    private ConviteRepository conviteRepository;

    @Autowired
    private PropostaFactory factory;

    @Autowired
    private PropostaDTOMapper mapper;

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private PropostaJPARepository jpaRepository;

    @Autowired
    @InjectMocks
    private PropostaService service;


    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldNotAcceptProposta_InvalidRUCID()
    {
        when(edicaoUCRestRepository.findByRucID(99L)).thenReturn(List.of());
        assertThrows(OptionalVazioException.class,()->service.acceptCandidaturaProposta(99L,1L));
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
}
package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.model.factory.PropostaFactory;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PropostaServiceIntegrationTests
{
    @Autowired
    private PropostaService service;

    @Autowired
    private PropostaFactory factory;

    @Autowired
    private PropostaDTOMapper mapper;

    @Autowired
    private PropostaJPARepository jpaRepository;


    @Test
    public void shouldNotAcceptProposta_InvalidID()
    {
        assertThrows(IdInvalidoException.class,()->service.acceptProposta(99L,1L,1L));
    }

    @Test
    public void shouldNotAcceptProposta_InvalidUpdate()
    {
        PropostaJPA p = new PropostaJPA(1L,1L,1L,"AAAAAAAAAA","BBBBBBBBBB","CCCCCCCCCC",1L,PropostaEstado.APROVADO);
        PropostaJPA save = jpaRepository.save(p);

        assertThrows(AtualizacaoInvalidaException.class,()->service.acceptProposta(save.getId(),2L,1L));
    }

    @Test
    public void shouldRejectProposta()
    {
        PropostaJPA p = new PropostaJPA(1L,1L,1L,"AAAAAAAAAA","BBBBBBBBBB","CCCCCCCCCC",1L,PropostaEstado.CANDIDATURA);
        PropostaJPA save = jpaRepository.save(p);

        Optional<PropostaDTO> dto = service.rejeitarProposta(save.getId());

        assertTrue(dto.isPresent());
        assertEquals(PropostaEstado.REPROVADO,dto.get().getEstadoAtual());
    }

    @Test
    public void shouldNotRejectProposta_InvalidID()
    {
        Optional<PropostaDTO> dto = service.rejeitarProposta(99L);
        assertTrue(dto.isEmpty());
    }

    @Test
    public void shouldNotRejectProposta_InvalidUpdate()
    {
        PropostaJPA p = new PropostaJPA(1L,1L,1L,"AAAAAAAAAA","BBBBBBBBBB","CCCCCCCCCC",1L,PropostaEstado.APROVADO);
        PropostaJPA save = jpaRepository.save(p);

        assertThrows(AtualizacaoInvalidaException.class,()->service.rejeitarProposta(save.getId()));
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
}
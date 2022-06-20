package com.grupo2.proposta.controller;

import com.grupo2.proposta.dto.*;
import com.grupo2.proposta.exception.*;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import com.grupo2.proposta.security.LoginContext;
import com.grupo2.proposta.security.SecurityUtils;
import com.grupo2.proposta.service.PropostaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaControllerUnitTests
{
    @MockBean
    private PropostaService service;

    @MockBean
    private HttpServletRequest request;

    private static MockedStatic<LoginContext> loginContext;

    @InjectMocks
    private PropostaController controller;

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
    public void shouldCreateProposta_valid()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.createCandidaturaProposta(prop)).thenReturn(prop);

        ResponseEntity<PropostaDTO> proposta = controller.createCandidaturaProposta(prop);

        assertEquals(proposta.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldNotCreateProposta_invalid()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.createCandidaturaProposta(prop)).thenThrow(BaseDadosException.class);

        assertThrows(BaseDadosException.class,()->controller.createCandidaturaProposta(prop));
    }

    @Test
    public void shouldAcceptProposta_valid()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.acceptCandidaturaProposta(1L, 1L)).thenReturn(prop);

        ResponseEntity<Object> objectResponseEntity = controller.acceptProposta(1L, 1L);

        assertEquals(objectResponseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldNotAcceptProposta_invalidId()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.acceptProposta(1L, 1L, 1L)).thenThrow(IdInvalidoException.class);

        assertThrows(IdInvalidoException.class,()->controller.acceptProposta(1L, 1L));
    }

    @Test
    public void shouldNotAcceptProposta_invalidAtualizacao()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.acceptProposta(1L, 1L, 1L)).thenThrow(AtualizacaoInvalidaException.class);

        assertThrows(AtualizacaoInvalidaException.class,()->controller.acceptProposta(1L, 1L));
    }

    @Test
    public void shouldListById()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        List<PropostaDTO> list = List.of(prop,prop,prop);

        when(service.findByIdUtilizador(1L)).thenReturn(list);

        ResponseEntity<Object> response = controller.listbyIdUtilizador(1L);

        assertEquals(response.getBody(), list);
    }

    @Test
    public void shouldThrow_ListById_empty()
    {
        List<PropostaDTO> list = List.of();

        when(service.findByIdUtilizador(1L)).thenReturn(list);

        assertThrows(ListaVaziaException.class,()->controller.listbyIdUtilizador(1L));
    }

    @Test
    public void shouldRejectProposta()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        when(service.rejeitarCandidaturaProposta(1L)).thenReturn(Optional.of(prop));

        ResponseEntity<PropostaDTO> response = controller.rejectCandidaturaProposta(1L);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldNotRejectProposta_invalidId()
    {
        when(service.rejeitarCandidaturaProposta(1L)).thenReturn(Optional.empty());

        assertThrows(IdInvalidoException.class,()->controller.rejectCandidaturaProposta(1L));
    }

    @Test
    public void shouldNotRejectProposta_invalid()
    {
        when(service.rejeitarCandidaturaProposta(1L)).thenThrow(AtualizacaoInvalidaException.class);

        assertThrows(AtualizacaoInvalidaException.class,()->controller.rejectCandidaturaProposta(1L));
    }

    @Test
    public void shouldListByNif()
    {
        PropostaDTO prop = mock(PropostaDTO.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getHeader(SecurityUtils.AUTH)).thenReturn("O");

        List<PropostaDTO> list = List.of(prop,prop,prop);

        when(service.findByNif(1,"O")).thenReturn(list);

        ResponseEntity<Object> entity = controller.listbyNif(1,request);

        assertEquals(entity.getBody(),list);
    }

    @Test
    public void shouldNotListByNif_Empty()
    {
        List<PropostaDTO> list = List.of();

        when(service.findByNif(1,"")).thenReturn(list);

        assertThrows(ListaVaziaException.class,()->controller.listbyNif(1,request));
    }

    @Test
    public void shouldListByTitulo()
    {
        PropostaDTO prop = mock(PropostaDTO.class);

        List<PropostaDTO> list = List.of(prop,prop,prop);

        when(service.findByTitulo("AAA")).thenReturn(list);

        ResponseEntity<Object> response = controller.listbyTitulo("AAA");

        assertEquals(response.getBody(),list);
    }

    @Test
    public void shouldNotListByTitulo_Empty()
    {
        List<PropostaDTO> list = List.of();

        when(service.findByTitulo("AAA")).thenReturn(list);

        assertThrows(ListaVaziaException.class,()->controller.listbyTitulo("AAA"));
    }

    @Test
    public void shouldFindAll()
    {
        PropostaDTO prop = mock(PropostaDTO.class);
        List<PropostaDTO> list = List.of(prop,prop,prop);

        when(service.findAllByEstado(1)).thenReturn(list);

        ResponseEntity<List<PropostaDTO>> responseEntity = controller.findAllByEstado(1);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

   @Test
    public void shouldAcceptAluno()
    {
        UtilizadorAuthDTO alunoDTO = mock(UtilizadorAuthDTO.class);
        PropostaCandidaturaIDDTO propostaCandidaturaID = mock(PropostaCandidaturaIDDTO.class);
        PropostaCandidaturaDTO propostaCandidaturaDTO = mock(PropostaCandidaturaDTO.class);

        when(service.acceptAlunoCandidaturaProposta(1L, propostaCandidaturaID)).thenReturn(propostaCandidaturaDTO);

        loginContext.when(LoginContext::getCurrent).thenReturn(alunoDTO);

        ResponseEntity<Object> objectResponseEntity = controller.acceptAlunoCandidaturaProposta(propostaCandidaturaID);

        assertEquals(objectResponseEntity.getStatusCode(),HttpStatus.OK);
    }

   @Test
    public void shouldRejectAluno()
    {
        UtilizadorAuthDTO alunoDTO = mock(UtilizadorAuthDTO.class);
        PropostaCandidaturaIDDTO propostaCandidaturaID = mock(PropostaCandidaturaIDDTO.class);
        PropostaCandidaturaDTO propostaCandidaturaDTO = mock(PropostaCandidaturaDTO.class);

        when(service.rejectAlunoCandidaturaProposta(1L, propostaCandidaturaID)).thenReturn(propostaCandidaturaDTO);

        loginContext.when(LoginContext::getCurrent).thenReturn(alunoDTO);

        ResponseEntity<Object> objectResponseEntity = controller.rejectAlunoCandidaturaProposta(propostaCandidaturaID);

        assertEquals(objectResponseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldCandidatarAluno()
    {
        PropostaCandidaturaDTO propostaCandidaturaDTO = mock(PropostaCandidaturaDTO.class);

        when(service.candidatarAlunoProposta(1L)).thenReturn(propostaCandidaturaDTO);

        ResponseEntity<Object> objectResponseEntity = controller.candidatarAlunoProposta(1L);

        assertEquals(objectResponseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void shouldNotCandidatarAluno_empty()
    {
        when(service.candidatarAlunoProposta(1L)).thenThrow(OptionalVazioException.class);

        assertThrows(OptionalVazioException.class,()->controller.candidatarAlunoProposta(1L));
    }

    @Test
    public void shouldNotCandidatarAluno_invalid()
    {
        when(service.candidatarAlunoProposta(1L)).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class,()->controller.candidatarAlunoProposta(1L));
    }

    @Test
    public void shouldNotCandidatarAluno_notUdate()
    {
        when(service.candidatarAlunoProposta(1L)).thenThrow(AtualizacaoInvalidaException.class);

        assertThrows(AtualizacaoInvalidaException.class,()->controller.candidatarAlunoProposta(1L));
    }

}
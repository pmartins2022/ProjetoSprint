
package com.grupo2.proposta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.proposta.dto.*;
import com.grupo2.proposta.exception.*;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.security.LoginContext;
import com.grupo2.proposta.service.PropostaService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class PropostaControllerIntegrationTests
{
    @MockBean
    private PropostaService service;

    @MockBean
    private HttpServletRequest request;

    private static MockedStatic<LoginContext> loginContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    static void setUpBeforeClass()
    {
        loginContext = org.mockito.Mockito.mockStatic(LoginContext.class);
    }

    @AfterAll
    public static void close()
    {
        loginContext.close();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldCreateCandidaturaProposta() throws Exception
    {

        PropostaDTO dto = new PropostaDTO(1L, 1L, "titulo", "problema", "objetivo", 1L, PropostaEstado.CANDIDATURA);
        when(service.createCandidaturaProposta(dto)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/proposta/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        PropostaDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), PropostaDTO.class);

        assertEquals(dto, responseDto);
    }


    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldNotCreateCandidaturaProposta_ValidacaoInvalida() throws Exception
    {
        PropostaDTO dto = new PropostaDTO(1L, 1L, "titulo", "problema", "objetivo", 1L, PropostaEstado.CANDIDATURA);
        when(service.createCandidaturaProposta(dto)).thenThrow(new ValidacaoInvalidaException());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/proposta/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldNotCreateCandidaturaProposta_BaseDadosException() throws Exception
    {
        PropostaDTO dto = new PropostaDTO(1L, 1L, "titulo", "problema", "objetivo", 1L, PropostaEstado.CANDIDATURA);
        when(service.createCandidaturaProposta(dto)).thenThrow(new BaseDadosException());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/proposta/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }


    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldListByIdUtilizador() throws Exception
    {
        PropostaDTO dto = new PropostaDTO(1L, 1L, "titulo", "problema", "objetivo", 1L, PropostaEstado.CANDIDATURA);

        when(service.findByIdUtilizador(1L)).thenReturn(List.of(dto,dto,dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/proposta/listarPorId/{id}",1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<PropostaDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), List.class);
        assertEquals(3, responseDto.size());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldNotListByIdUtilizador_Empty() throws Exception
    {
        when(service.findByIdUtilizador(1L)).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/proposta/listarPorId/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldListByTitulo() throws Exception
    {
        PropostaDTO dto = new PropostaDTO(1L, 1L, "titulo", "problema", "objetivo", 1L, PropostaEstado.CANDIDATURA);

        when(service.findByTitulo("titulo")).thenReturn(List.of(dto,dto,dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/proposta/listarPorTitulo?titulo=titulo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<PropostaDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), List.class);
        assertEquals(3, responseDto.size());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldNotListByTitulo_Empty() throws Exception
    {
        when(service.findByTitulo("titulo")).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/proposta/listarPorTitulo?titulo=titulo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldListByNif() throws Exception
    {
        PropostaDTO dto = new PropostaDTO(1L, 1L, "titulo", "problema", "objetivo", 1L, PropostaEstado.CANDIDATURA);

        when(service.findByNif(123456789,null)).thenReturn(List.of(dto,dto,dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/proposta/listarPorNif/{nif}",123456789)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<PropostaDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), List.class);
        assertEquals(3, responseDto.size());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldNotListByNif_Empty() throws Exception
    {
        when(service.findByNif(123456789,null)).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/proposta/listarPorNif/{nif}",123456789)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldFindAllByEstado() throws Exception
    {
        PropostaDTO dto = new PropostaDTO(1L, 1L, "titulo", "problema", "objetivo", 1L, PropostaEstado.CANDIDATURA);

        //when(request.getHeader(SecurityUtils.AUTH)).thenReturn("1");
        when(service.findAllByEstado(0)).thenReturn(List.of(dto,dto,dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/proposta?estado=0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<PropostaDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), List.class);
        assertEquals(3, responseDto.size());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldAcceptAlunoCandidaturaProposta() throws Exception
    {
        PropostaCandidaturaIDDTO id = new PropostaCandidaturaIDDTO(1L, 1L);
        PropostaCandidaturaDTO dto = new PropostaCandidaturaDTO(1L,1L, EstadoCandidatura.ACEITE);

        UtilizadorAuthDTO curr = new UtilizadorAuthDTO(1L,"admin","admin","ROLE_DOCENTE");

        loginContext.when(LoginContext::getCurrent).thenReturn(curr);

        when(service.acceptAlunoCandidaturaProposta(1L,id)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/proposta/aceitarCandidaturaAluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(id)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldRejectAlunoCandidaturaProposta() throws Exception
    {
        PropostaCandidaturaIDDTO id = new PropostaCandidaturaIDDTO(1L, 1L);
        PropostaCandidaturaDTO dto = new PropostaCandidaturaDTO(1L,1L, EstadoCandidatura.REJEITADO);

        UtilizadorAuthDTO curr = new UtilizadorAuthDTO(1L,"admin","admin","ROLE_DOCENTE");

        loginContext.when(LoginContext::getCurrent).thenReturn(curr);

        when(service.rejectAlunoCandidaturaProposta(1L,id)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/proposta/rejeitarCandidaturaAluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(id)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ALUNO")
    public void shouldCandidatarAlunoProposta() throws Exception
    {
        PropostaCandidaturaDTO dto = new PropostaCandidaturaDTO(1L,1L, EstadoCandidatura.PENDENTE);

        when(service.candidatarAlunoProposta(1L)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/proposta/candidatarAlunoProposta/{propostaID}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        PropostaCandidaturaDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), PropostaCandidaturaDTO.class);
        assertEquals(dto, responseDto);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldAcceptProposta() throws Exception
    {
        ProjetoDTO proj = new ProjetoDTO(1L,2L,1L);

        when(service.acceptProposta(1L, 1L,2L)).thenReturn(proj);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/proposta/aceitarProposta/1?orientador=1&aluno=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ProjetoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), ProjetoDTO.class);

        assertEquals(proj, responseDto);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldRejectProposta() throws Exception
    {
        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/proposta/rejeitarProposta/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldAcceptCandidaturaProposta() throws Exception
    {
        PropostaDTO prop = new PropostaDTO(1L,1L,"titulo","problema","objetivo",1L,PropostaEstado.APROVADO);

        UtilizadorAuthDTO curr = new UtilizadorAuthDTO(1L,"admin","admin","ROLE_DOCENTE");

        loginContext.when(LoginContext::getCurrent).thenReturn(curr);

        when(service.acceptCandidaturaProposta(1L, 1L)).thenReturn(prop);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.put("/proposta/aceitarCandidatura/{idProposta}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        PropostaDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), PropostaDTO.class);

        assertEquals(prop, responseDto);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldRejectCandidaturaProposta() throws Exception
    {
        PropostaDTO prop = new PropostaDTO(1L,1L,"titulo","problema","objetivo",1L,PropostaEstado.APROVADO);

        UtilizadorAuthDTO curr = new UtilizadorAuthDTO(1L,"admin","admin","ROLE_DOCENTE");

        loginContext.when(LoginContext::getCurrent).thenReturn(curr);

        when(service.rejeitarCandidaturaProposta(1L)).thenReturn(Optional.of(prop));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.put("/proposta/rejeitarCandidatura/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        PropostaDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), PropostaDTO.class);

        assertEquals(prop, responseDto);
    }
}
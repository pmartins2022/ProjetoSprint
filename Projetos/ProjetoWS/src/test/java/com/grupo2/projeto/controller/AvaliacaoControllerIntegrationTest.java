package com.grupo2.projeto.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.AvaliacaoNotaDTO;
import com.grupo2.projeto.dto.EdicaoUCDTO;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.model.AvaliacaoNota;
import com.grupo2.projeto.model.EstadoAvaliacao;
import com.grupo2.projeto.security.LoginContext;
import com.grupo2.projeto.service.AvaliacaoNotaService;
import com.grupo2.projeto.service.AvaliacaoService;
import com.grupo2.projeto.service.ProjetoService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class AvaliacaoControllerIntegrationTest
{
    @MockBean
    private AvaliacaoService service;

    @MockBean
    private AvaliacaoNotaService avaliacaoNotaService;

    @MockBean
    private HttpServletRequest request;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //private static MockedStatic<LoginContext> loginContext;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    static void setUpBeforeClass()
    {
        //loginContext = org.mockito.Mockito.mockStatic(LoginContext.class);
    }

    @AfterAll
    public static void close()
    {
        //loginContext.close();
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldCreateAvaliacao() throws Exception
    {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(1L, 1L, 1L, 1L, 1L, 1L, "ESTADO", "15-02-1991");

        doNothing().when(service).createAndSave(avaliacaoDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/avaliacao/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avaliacaoDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        AvaliacaoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), AvaliacaoDTO.class);

        assertEquals(avaliacaoDTO, responseDto);
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldFindById() throws Exception
    {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(1L, 1L, 1L, 1L, 1L, 1L, "ESTADO", "15-02-1991");

        when(service.findById(1L)).thenReturn(avaliacaoDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/avaliacao/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        AvaliacaoDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), AvaliacaoDTO.class);

        assertEquals(avaliacaoDTO,dto);
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldFindAll() throws Exception
    {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(1L, 1L, 1L, 1L, 1L, 1L, "ESTADO", "15-02-1991");

        when(service.findAll()).thenReturn(List.of(avaliacaoDTO));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/avaliacao/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<AvaliacaoDTO> dto = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<AvaliacaoDTO>>() {});

        assertEquals(1,dto.size());
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldFindAvaliacaoByPresidenteID() throws Exception
    {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(1L, 1L, 1L, 1L, 1L, 1L, "ESTADO", "15-02-1991");

        when(service.findAllByPresidente(1L)).thenReturn(List.of(avaliacaoDTO));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/avaliacao/listarAvaliacao/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<AvaliacaoDTO> dto = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<AvaliacaoDTO>>() {});

        assertEquals(1,dto.size());
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldCreateAvaliacaoNota() throws Exception
    {
        AvaliacaoNotaDTO avaliacaoNotaDTO = new AvaliacaoNotaDTO(1L, 1L, 1L, "ESTADO", EstadoAvaliacao.PENDENTE);

        doNothing().when(avaliacaoNotaService).createAvaliacaoNota(avaliacaoNotaDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/avaliacao/avaliar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avaliacaoNotaDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        AvaliacaoNotaDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), AvaliacaoNotaDTO.class);

        assertEquals(avaliacaoNotaDTO, responseDto);
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldFindallbyEstadoAndRucID() throws Exception
    {
        EdicaoUCDTO edicaoUCDTO = new EdicaoUCDTO(1L, "UCcODE", "ANOLETIVO", 1L);

        AvaliacaoNotaDTO avaliacaoNotaDTO = new AvaliacaoNotaDTO(1L, 1L, 1L, "ESTADO", EstadoAvaliacao.PENDENTE);

        when(avaliacaoNotaService.findAllByEstadoAndRucID(edicaoUCDTO.getRucID(), EstadoAvaliacao.PENDENTE.name())).thenReturn(List.of(avaliacaoNotaDTO));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/avaliacao/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<AvaliacaoDTO> dto = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<AvaliacaoDTO>>() {});

        assertEquals(1,dto.size());
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldReviewNota() throws Exception
    {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(1L, 1L, 1L, 1L, 1L, 1L, EstadoAvaliacao.PENDENTE.name(), "15-02-1991");

        AvaliacaoNotaDTO avaliacaoNotaDTO = new AvaliacaoNotaDTO(1L, 1L, 1L, "ESTADO", EstadoAvaliacao.PENDENTE);

        doNothing().when(avaliacaoNotaService).reviewAvaliacaoNota(1L,avaliacaoDTO.toString());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/avaliacao/reverAvaliacaoNota/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avaliacaoNotaDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        AvaliacaoNotaDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), AvaliacaoNotaDTO.class);

        assertEquals(avaliacaoNotaDTO, responseDto);
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldEditarNota() throws Exception
    {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(1L, 1L, 1L, 1L, 1L, 1L, EstadoAvaliacao.PENDENTE.name(), "15-02-1991");

        AvaliacaoNotaDTO avaliacaoNotaDTO = new AvaliacaoNotaDTO(1L, 1L, 1L, "ESTADO", EstadoAvaliacao.PENDENTE);

        doNothing().when(avaliacaoNotaService).editarAvaliacaoNota(avaliacaoDTO.getIdMomentoAvaliacao(),avaliacaoNotaDTO.getNota(),avaliacaoNotaDTO.getJustificacao());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/avaliacao/editarAvaliacao/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avaliacaoNotaDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        AvaliacaoNotaDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), AvaliacaoNotaDTO.class);

        assertEquals(avaliacaoNotaDTO, responseDto);
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldFindallbyvaliacaoNotaByAvaliacaoID() throws Exception
    {
        AvaliacaoNotaDTO avaliacaoNotaDTO = new AvaliacaoNotaDTO(1L, 1L, 1L, "ESTADO", EstadoAvaliacao.PENDENTE);

        when(avaliacaoNotaService.findAvaliacaoNotaByAvaliacaoID(1L)).thenReturn(avaliacaoNotaDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/nota/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<AvaliacaoDTO> dto = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<AvaliacaoDTO>>() {});

        assertEquals(1,dto.size());
    }
}
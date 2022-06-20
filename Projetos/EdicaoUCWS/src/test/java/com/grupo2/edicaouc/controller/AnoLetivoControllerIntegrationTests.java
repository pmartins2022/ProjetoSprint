package com.grupo2.edicaouc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.security.LoginContext;
import com.grupo2.edicaouc.service.AnoLetivoService;
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
@Transactional
@SpringBootTest
class AnoLetivoControllerIntegrationTests
{
    @MockBean
    private AnoLetivoService service;

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


    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldCreateAnoLetivo() throws Exception
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");
        when(service.createAndSaveAnoLetivo(dto)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/anoLetivo/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        AnoLetivoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), AnoLetivoDTO.class);
        assertEquals(dto, responseDto);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldNotCreateAnoLetivo_InvalidAtributtes() throws Exception
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");
        when(service.createAndSaveAnoLetivo(dto)).thenThrow(ValidacaoInvalidaException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/anoLetivo/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldNotCreateAnoLetivo_Exists() throws Exception
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");
        when(service.createAndSaveAnoLetivo(dto)).thenThrow(ErroGeralException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/anoLetivo/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldNotCreateAnoLetivo_OptionalVazio() throws Exception
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");
        when(service.createAndSaveAnoLetivo(dto)).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/anoLetivo/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldListAllAnoLetivo() throws Exception
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");
        when(service.findAll()).thenReturn(List.of(dto, dto, dto, dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/anoLetivo/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<AnoLetivoDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), List.class);
        assertEquals(4, responseDto.size());
    }

    @Test
    public void shouldNotListAllAnoLetivo_NotExists() throws Exception
    {
        when(service.findAll()).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/anoLetivo/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldFindBySigla() throws Exception
    {
        AnoLetivoDTO dto = new AnoLetivoDTO("2000-2001");

        when(service.findByID("2000-2001")).thenReturn(Optional.of(dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/anoLetivo/{id}","2000-2001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        AnoLetivoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), AnoLetivoDTO.class);
        assertEquals(dto, responseDto);
    }

    @Test
    public void shouldNotFindByNIF_NotExists() throws Exception
    {
        when(service.findByID("2000-2001")).thenReturn(Optional.empty()); //empty ou Excecao??

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/anoLetivo/{id}","2000-2001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
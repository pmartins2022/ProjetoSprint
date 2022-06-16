package com.grupo2.proposta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.exception.ValidacaoInvalidaException;
import com.grupo2.proposta.security.LoginContext;
import com.grupo2.proposta.service.PropostaService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ConviteControllerIntegrationTests
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

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ALUNO")
    public void shouldCreateConvite() throws Exception
    {
        ConviteDTO conviteDTO = new ConviteDTO(1L,2L,1L);

        when(service.createConvite(conviteDTO)).thenReturn(conviteDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/convite/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conviteDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        ConviteDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), ConviteDTO.class);

        assertEquals(conviteDTO,dto);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ALUNO")
    public void shouldNotCreateConvite_OptionalVazioException() throws Exception
    {
        ConviteDTO conviteDTO = new ConviteDTO(1L, 2L, 1L);

        when(service.createConvite(conviteDTO)).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/convite/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conviteDTO)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ALUNO")
    public void shouldNotCreateConvite_ValidacaoInvalidaException() throws Exception
    {
        ConviteDTO conviteDTO = new ConviteDTO(1L,2L,1L);

        when(service.createConvite(conviteDTO)).thenThrow(ValidacaoInvalidaException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/convite/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conviteDTO)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ALUNO")
    public void shouldNotCreateConvite_IdInvalidoException() throws Exception
    {
        ConviteDTO conviteDTO = new ConviteDTO(1L,2L,1L);

        when(service.createConvite(conviteDTO)).thenThrow(IdInvalidoException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/convite/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conviteDTO)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldAcceptOrientacao() throws Exception
    {
        ConviteDTO conviteDTO = new ConviteDTO(1L,2L,1L);
        when(service.acceptOrientacaoProposta(1L,2L,null)).thenReturn(conviteDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/convite/aceitarOrientacao/1?orientador=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        ConviteDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), ConviteDTO.class);

        assertEquals(conviteDTO,dto);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldRejectOrientacao() throws Exception
    {
        ConviteDTO conviteDTO = new ConviteDTO(1L,2L,1L);
        when(service.rejectOrientacaoProposta(1L,2L,null)).thenReturn(conviteDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/convite/rejeitarOrientacao/1?orientador=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        ConviteDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), ConviteDTO.class);

        assertEquals(conviteDTO,dto);
    }
}
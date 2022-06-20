package com.grupo2.edicaouc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.EdicaoMomentoAvaliacaoDTO;
import com.grupo2.edicaouc.dto.MomentoAvaliacaoDTO;
import com.grupo2.edicaouc.security.LoginContext;
import com.grupo2.edicaouc.service.AnoLetivoService;
import com.grupo2.edicaouc.service.MomentoAvaliacaoService;
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
@Transactional
@SpringBootTest
class MomentoAvaliacaoControllerIntegrationTests
{
    @MockBean
    private MomentoAvaliacaoService service;

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
    @WithMockUser(username = "docente", password = "password", authorities = "ROLE_DOCENTE")
    public void shouldCreateMomento() throws Exception
    {
        MomentoAvaliacaoDTO dto = new MomentoAvaliacaoDTO(1L, "denominacao");
        when(service.createAndSaveMomentoAvaliacao(dto)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/momento/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        MomentoAvaliacaoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                MomentoAvaliacaoDTO.class);
        assertEquals(dto, responseDto);
    }

    @Test
    public void shouldCreateEdicaoMomento() throws Exception
    {
        EdicaoMomentoAvaliacaoDTO dto = new EdicaoMomentoAvaliacaoDTO(1L, 1L);
        when(service.createAndSaveEdicaoMomentoAvaliacao(dto)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/momento/criar/edicaoMomento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        EdicaoMomentoAvaliacaoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                EdicaoMomentoAvaliacaoDTO.class);
        assertEquals(dto, responseDto);
    }
}
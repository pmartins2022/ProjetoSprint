package com.grupo2.edicaouc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.edicaouc.dto.MomentoAvaliacaoDTO;
import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.security.LoginContext;
import com.grupo2.edicaouc.service.MomentoAvaliacaoService;
import com.grupo2.edicaouc.service.UnidadeCurricularService;
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
class UnidadeCurricularControllerIntegrationTests
{
    @MockBean
    private UnidadeCurricularService service;

    @MockBean
    private HttpServletRequest request;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldCreateUC() throws Exception
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("sigla", "denominacao");
        when(service.createAndSaveUnidadeCurricular(dto)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/uc/criar")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        UnidadeCurricularDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                UnidadeCurricularDTO.class);
        assertEquals(dto, responseDto);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldNotCreateUC_Exists() throws Exception
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("sigla", "denominacao");
        when(service.createAndSaveUnidadeCurricular(dto)).thenThrow(ErroGeralException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/uc/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldCreateUC_InvalidAtributtes() throws Exception
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("sigla", "denominacao");
        when(service.createAndSaveUnidadeCurricular(dto)).thenThrow(ValidacaoInvalidaException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/uc/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldFindByID() throws Exception
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("sigla", "denominacao");
        when(service.findBySigla("sigla")).thenReturn(Optional.of(dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/uc/{sigla}", "sigla")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        UnidadeCurricularDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                UnidadeCurricularDTO.class);
        assertEquals(dto, responseDto);
    }

    @Test
    public void shouldNotFindByID_NotExists() throws Exception
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("sigla", "denominacao");
        when(service.findBySigla("sigla")).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/uc/{sigla}", "sigla")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }


    @Test
    public void shouldUpdateDenominacao() throws Exception
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("sigla", "denominacao");
        when(service.update("sigla", "denominacao")).thenReturn(Optional.of(dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.put("/uc/{sigla}/?denominacao=", "sigla", "denominacao")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        UnidadeCurricularDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                UnidadeCurricularDTO.class);
        assertEquals(dto.getDenominacao(), responseDto.getDenominacao());
    }

    @Test
    public void shouldNotUpdateDenominacao_OptionalVazio() throws Exception
    {
        when(service.update("sigla", "denominacao")).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.put("/uc/{sigla}/?denominacao=", "sigla", "denominacao")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
            .andReturn();
    }

    @Test
    public void shouldListAllUC() throws Exception
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("sigla", "denominacao");
        when(service.findAll()).thenReturn(List.of(dto, dto, dto, dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/uc/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<UnidadeCurricularDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                List.class);
        assertEquals(4, responseDto.size());
    }

    @Test
    public void shouldNotListAllUC_NotExists() throws Exception
    {
        when(service.update("sigla", "denominacao")).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/uc/sigla/?denominacao=", "denominacao")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

}
package com.grupo2.organizacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.exception.ListaVaziaException;
import com.grupo2.organizacao.exception.OptionalVazioException;
import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.repository.jpa.OrganizacaoJPARepository;
import com.grupo2.organizacao.service.OrganizacaoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class OrganizacaoControllerIntegrationTests
{
    @MockBean
    private OrganizacaoService service;

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

    /*
    @Test
    public void shouldFindByID() throws Exception
    {
        OrganizacaoDTO dto = new OrganizacaoDTO(1L, "denominacao", 500000135);

        when(service.findByID(1L)).thenReturn(Optional.of(dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/organizacao?nif=", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        OrganizacaoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), OrganizacaoDTO.class);
        assertEquals(dto, responseDto);
    }


    @Test
    public void shouldFindByNIF() throws Exception
    {
        OrganizacaoDTO dto = new OrganizacaoDTO(1L, "denominacao", 500000135);

        when(service.findByNIF(500000135)).thenReturn(Optional.of(dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/organizacao?nif=", 500000135)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        OrganizacaoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), OrganizacaoDTO.class);
        assertEquals(dto, responseDto);
    }
    
     */

    @Test
    public void shouldNotFindByID_NotExists() throws Exception
    {
        when(service.findByID(1L)).thenReturn(Optional.empty());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/organizacao?nif=", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

    }

    @Test
    public void shouldNotFindByNIF_NotExists() throws Exception
    {
        when(service.findByNIF(500000135)).thenReturn(Optional.empty());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/organizacao/?nif=", 500000135)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldFindAllOrganizacao() throws Exception
    {
        OrganizacaoDTO dto = new OrganizacaoDTO(1L, "denominacao", 500000135);

        when(service.findAll()).thenReturn(List.of(dto, dto, dto, dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/organizacao/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<OrganizacaoDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), List.class);
        assertEquals(4, responseDto.size());
    }

    @Test
    public void shouldNotFindAllOrganizacao_NotExists() throws Exception
    {
        when(service.findAll()).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/organizacao/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

}
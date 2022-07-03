package com.grupo2.projeto.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.model.EstadoConteudo;
import com.grupo2.projeto.service.AvaliacaoNotaService;
import com.grupo2.projeto.service.AvaliacaoService;
import com.grupo2.projeto.service.ConteudoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ConteudoControllerIntegrationTest
{
    @MockBean
    private ConteudoService service;

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

    @Test
    @WithMockUser(username = "aluno", password = "aluno", authorities = "ROLE_ALUNO")
    public void shouldCreateConteudo() throws Exception
    {
        ConteudoDTO conteudoDTO = new ConteudoDTO(1L, 1L,"titulo", "caminho", "documento", "linguagem", "estado");

        doNothing().when(service).createAndSave(any(ConteudoDTO.class));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/conteudo/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conteudoDTO)))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldFindById() throws Exception
    {
        ConteudoDTO conteudoDTO = new ConteudoDTO(1L, 1L,"titulo", "caminho", "documento", "linguagem", "estado");

        when(service.findById(1L)).thenReturn(conteudoDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/conteudo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conteudoDTO)))
                .andExpect(status().isOk())
                .andReturn();

        ConteudoDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), ConteudoDTO.class);

        assertEquals(conteudoDTO, responseDto);
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldAccept() throws Exception
    {
        ConteudoDTO conteudoDTO = new ConteudoDTO(1L, 1L,"titulo", "caminho", "documento", "linguagem", "estado");

        doNothing().when(service).acceptConteudo(1L);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.patch("/conteudo/aceitarConteudo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conteudoDTO)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "docente", password = "docente", authorities = "ROLE_DOCENTE")
    public void shouldFindAllByProjetoId() throws Exception
    {
        ConteudoDTO conteudoDTO = new ConteudoDTO(1L, 1L,"titulo", "caminho", "documento", "linguagem", "PENDENTE");

        when(service.findAllByIdProjeto(1L)).thenReturn(List.of(conteudoDTO));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/conteudo/projetoID/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conteudoDTO)))
                .andExpect(status().isOk())
                .andReturn();

        List<ConteudoDTO> list = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<ConteudoDTO>>() {});

        assertEquals(1,list.size());
    }
}
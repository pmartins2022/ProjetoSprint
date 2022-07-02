package com.grupo2.projeto.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.filter.ProjetoFilterBody;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.security.LoginContext;
import com.grupo2.projeto.service.ProjetoFilterService;
import com.grupo2.projeto.service.ProjetoService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ProjetoControllerIntegrationTest
{
    @MockBean
    private ProjetoService service;

    @MockBean
    private ProjetoFilterService filterService;

    @MockBean
    private HttpServletRequest request;

    //private static MockedStatic<LoginContext> loginContext;

    @InjectMocks
    private ProjetoController controller;

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
        //loginContext = org.mockito.Mockito.mockStatic(LoginContext.class);
    }

    @AfterAll
    static void close()
    {
        //loginContext.close();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ALUNO")
    public void shouldFindById() throws Exception
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(1L,2L,1L);

        when(service.findById(1L)).thenReturn(Optional.of(projetoDTO));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/projeto/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ProjetoDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), ProjetoDTO.class);

        assertEquals(projetoDTO,dto);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ALUNO")
    public void shouldNotFindById() throws Exception
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(1L,2L,1L);

        when(service.findById(1L)).thenReturn(Optional.empty());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/projeto/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldCreateProjeto() throws Exception
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(1L,2L,1L);

        doNothing().when(service).createAndSave(projetoDTO);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/projeto/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projetoDTO)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_DOCENTE")
    public void shouldFindAllByOrientadorID() throws Exception
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(1L,2L,1L);

        when(service.findAllByOrientadorId(1L)).thenReturn(List.of(projetoDTO));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/projeto/orientadorID/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<ProjetoDTO> dto = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<ProjetoDTO>>() {});

        assertEquals(1,dto.size());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldNotProjetoFilter() throws Exception
    {
        ProjetoFilterBody body = new ProjetoFilterBody(null,null);

        when(filterService.filtrarProjetos(body)).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/projeto/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
package com.grupo2.edicaouc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import com.grupo2.edicaouc.service.EdicaoUCService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class EdicaoUCControllerIntegrationTests
{
    @MockBean
    private EdicaoUCService service;

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
    public void shouldCreateEdicaoUC() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.PENDENTE);
        when(service.createEdicaoUC(any(EdicaoUCDTO.class))).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/edicaoUC/criar")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn();

        EdicaoUCDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                EdicaoUCDTO.class);
        assertEquals(dto.toString(), responseDto.toString());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldNotCreateEdicaoUC_InvalidAtributtes() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.PENDENTE);

        when(service.createEdicaoUC(any(EdicaoUCDTO.class))).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/edicaoUC/criar")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldNotCreateEdicaoUC_InvalidRole() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.PENDENTE);

        when(service.createEdicaoUC(any(EdicaoUCDTO.class))).thenThrow(ErroGeralException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/edicaoUC/criar")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldListEdicaoUCByUCCode() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.PENDENTE);

        when(service.findAllEdicaoByUCCode("sigla")).thenReturn(List.of(dto, dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/listar/{UCCode}", "sigla")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<EdicaoUCDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                List.class);
        assertEquals(2, responseDto.size());
    }

    @Test
    public void shouldNotListEdicaoUCByUCCode_Empty() throws Exception
    {
        when(service.findAllEdicaoByUCCode("sigla")).thenThrow(ListaVaziaException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/listar/{UCCode}", "sigla")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldListAllEdicaoUC() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.PENDENTE);

        when(service.findAllEdicaoUC()).thenReturn(List.of(dto, dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<EdicaoUCDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                List.class);
        assertEquals(2, responseDto.size());
    }

    @Test
    public void shouldNotListAllEdicaoUC_Empty() throws Exception
    {
        when(service.findAllEdicaoUC()).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/listar")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldFindByID() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.PENDENTE);

        when(service.findById(1L)).thenReturn(Optional.of(dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        EdicaoUCDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                EdicaoUCDTO.class);
        assertEquals(dto.toString(), responseDto.toString());
    }

    @Test
    public void shouldNotFindByID_NotExists() throws Exception
    {
        when(service.findById(1L)).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldListEdicaoUCByRucID() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.PENDENTE);

        when(service.findByRucID(1L)).thenReturn(List.of(dto, dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/ruc/{rucID}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<EdicaoUCDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                List.class);
        assertEquals(2, responseDto.size());
    }

    @Test
    public void shouldNotListEdicaoUCByRucID_Empty() throws Exception
    {
        when(service.findByRucID(1L)).thenThrow(ListaVaziaException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/ruc/{rucID}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldListEdicaoUCByRucIDActive() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.ATIVA);

        when(service.findByRucIDAndEstadoEdicaoUC(1L, EstadoEdicaoUC.ATIVA)).thenReturn(Optional.of(dto));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/ruc/{rucID}/active", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        EdicaoUCDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                EdicaoUCDTO.class);
        assertEquals(dto.toString(), responseDto.toString());
    }

    @Test
    public void shouldNotListEdicaoUCByRucIDActive_Empty() throws Exception
    {
        when(service.findByRucIDAndEstadoEdicaoUC(1L,  EstadoEdicaoUC.ATIVA)).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicaoUC/ruc/{rucID}/active", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "docente", password = "password", authorities = "ROLE_DOCENTE")
    public void shouldReturnEdicaoUCDeactivated() throws Exception
    {
        EdicaoUCDTO dto = new EdicaoUCDTO("sigla", "denominacao", 1L,EstadoEdicaoUC.PENDENTE);

        when(service.desativarEdicao( 1L)).thenReturn(dto);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.patch("/edicaoUC/desativarEdicao/{edicaoUCID}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        EdicaoUCDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),
                EdicaoUCDTO.class);
        assertEquals(dto.toString(), responseDto.toString());
    }

    @Test
    @WithMockUser(username = "docente", password = "password", authorities = "ROLE_DOCENTE")
    public void shouldNotReturnEdicaoUCDeactivated_InvalidAtributtes() throws Exception
    {
        when(service.desativarEdicao( 1L)).thenThrow(OptionalVazioException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.patch("/edicaoUC/desativarEdicao/{edicaoUCID}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "docente", password = "password", authorities = "ROLE_DOCENTE")
    public void shouldNotReturnEdicaoUCDeactivated_InvalidCredentials() throws Exception
    {
        when(service.desativarEdicao( 1L)).thenThrow(ErroGeralException.class);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.patch("/edicaoUC/desativarEdicao/{edicaoUCID}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

}
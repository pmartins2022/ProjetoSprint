package com.grupo2.utilizadores.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.ListaVaziaException;
import com.grupo2.utilizadores.exception.OptionalVazioException;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.repository.jpa.UtilizadorJPARepository;
import com.grupo2.utilizadores.service.UtilizadorService;
import org.junit.jupiter.api.Test;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollectionOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class UtilizadorControllerIntegrationTest {

    @Autowired
    private UtilizadorController controller;

    @Autowired
    UtilizadorJPARepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    UtilizadorService service;



    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldRegistar_Valid_MockMvc() throws Exception {

        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"Utilizador","Sobrenome","mail@example.com","utilizador","password", TipoUtilizador.DOCENTE);

        when(service.registar(utilizadorDTO)).thenReturn(utilizadorDTO);
        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/utilizador/registar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(utilizadorDTO)))
                .andExpect(status().isOk())
                .andReturn();
        UtilizadorDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), UtilizadorDTO.class);

        assertEquals(utilizadorDTO,dto);
    }


    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldFindByID() throws Exception {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L, "Utilizador", "Sobrenome", "mail@example.com", "utilizador", "password", TipoUtilizador.DOCENTE);

        when(service.findByID(1L)).thenReturn(Optional.of(utilizadorDTO));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/utilizador/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        UtilizadorDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), UtilizadorDTO.class);
    }

    @Test
    public void shouldNotFindByID() throws Exception
    {
        when(service.findAll()).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/utilizador/{id}",1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }


    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldFindByUsername() throws Exception
    {
        UtilizadorAuthDTO utilizadorAuthDTO =new UtilizadorAuthDTO(2L,"username","password","docente");
        when(service.findByUsername("username")).thenReturn(Optional.of(utilizadorAuthDTO));

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/utilizador/find")
                        .param("username", "username")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        UtilizadorAuthDTO responseDto = objectMapper.readValue(response.getResponse().getContentAsString(), UtilizadorAuthDTO.class);
        assertEquals(utilizadorAuthDTO.getUsername(), responseDto.getUsername());
    }
    @Test
    public void shouldNotFindByUsername() throws Exception
    {
        when(service.findAll()).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/utilizador/find")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldListAllUtilizadores() throws Exception
    {
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L, "Utilizador", "Sobrenome", "mail@example.com", "utilizador", "password", TipoUtilizador.DOCENTE);


        when(service.findAll()).thenReturn(List.of(utilizadorDTO,utilizadorDTO,utilizadorDTO,utilizadorDTO));
        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/utilizador/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<UtilizadorDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),List.class);
        assertEquals(4,responseDto.size());
    }

    @Test
    public void shouldNotListAll_UsersNotExists() throws Exception
    {
        when(service.findAll()).thenReturn(List.of());

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/utilizador/listar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void shouldListAllDocentes() throws Exception{
        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L, "Utilizador", "Sobrenome", "mail@example.com", "utilizador", "password", TipoUtilizador.DOCENTE);

        when(service.findAllDocentes()).thenReturn(List.of(utilizadorDTO,utilizadorDTO));
        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.get("/utilizador/docentes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<UtilizadorDTO> responseDto = objectMapper.readValue(response.getResponse().getContentAsString(),List.class);

        assertEquals(2,responseDto.size());
    }


}
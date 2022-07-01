package com.grupo2.utilizadores.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.OptionalVazioException;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.repository.jpa.UtilizadorJPARepository;
import com.grupo2.utilizadores.service.UtilizadorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void shouldNotFindUtilizador_NotExists()
    {
        assertThrows(OptionalVazioException.class, ()-> controller.findByID(2L));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldCreateAndSave_Valid_MockMvc() throws Exception {

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
    public void shouldGetUtilizadorById_Valid_MockMvc() throws Exception {

        UtilizadorDTO utilizadorDTO = new UtilizadorDTO(1L,"Utilizador","Sobrenome","mail@example.com","utilizador","password",TipoUtilizador.DOCENTE);
        Optional<UtilizadorDTO> dto1 =Optional.of(utilizadorDTO);
        when(service.findByID(5L)).thenReturn(dto1);

        MvcResult response = mockMvc
                .perform( MockMvcRequestBuilders.get("http://localhost:8085/utilizador/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        UtilizadorDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), UtilizadorDTO.class);

        assertEquals(utilizadorDTO.getId(),dto.getId());
    }
    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ROLE_ADMIN")
    public void shouldGetUtilizadorByUsername_Valid_MockMvc() throws Exception {
        UtilizadorAuthDTO utilizadorAuthDTO =new UtilizadorAuthDTO(2L,"username","password","docente");
        Optional<UtilizadorAuthDTO> dto1 =Optional.of(utilizadorAuthDTO);
        when(service.findByUsername("Utilizador")).thenReturn(dto1);

        MvcResult response = mockMvc
                .perform( MockMvcRequestBuilders.get("http://localhost:8085/utilizador?name=username")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        UtilizadorDTO dto = objectMapper.readValue(response.getResponse().getContentAsString(), UtilizadorDTO.class);

        assertEquals(utilizadorAuthDTO.getUsername(),dto.getUsername());
    }


}
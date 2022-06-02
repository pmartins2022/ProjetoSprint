package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.mapper.ProjetoDTOMapper;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjetoServiceIntegrationTest
{
    @MockBean
    ProjetoRepository repository;

    @MockBean
    ProjetoDTOMapper mapper;

    @InjectMocks
    ProjetoService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }




}
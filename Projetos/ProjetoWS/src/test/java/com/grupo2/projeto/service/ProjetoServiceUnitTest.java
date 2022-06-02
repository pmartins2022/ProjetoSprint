package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.mapper.ProjetoDTOMapper;
import com.grupo2.projeto.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.mock;

class ProjetoServiceUnitTest
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
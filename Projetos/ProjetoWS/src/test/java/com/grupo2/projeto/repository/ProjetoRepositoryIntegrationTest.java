package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.mapper.ProjetoJPAMapper;
import com.grupo2.projeto.repository.jpa.ProjetoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class ProjetoRepositoryIntegrationTest
{
    @MockBean
    ProjetoJPARepository jpaRepository;

    @MockBean
    ProjetoJPAMapper mapper;

    @InjectMocks
    ProjetoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

}
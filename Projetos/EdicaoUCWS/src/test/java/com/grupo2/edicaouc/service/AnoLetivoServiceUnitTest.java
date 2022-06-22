package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.mapper.AnoLetivoDTOMapper;
import com.grupo2.edicaouc.jpa.AnoLetivoJPA;
import com.grupo2.edicaouc.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.repository.AnoLetivoRepository;
import com.grupo2.edicaouc.repository.jpa.AnoLetivoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AnoLetivoServiceUnitTest
{
    @MockBean
    AnoLetivoJPAMapper mapper;

    @MockBean
    AnoLetivoJPARepository anoLetivoJPARepository;

    @InjectMocks
    AnoLetivoRepository repository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateValidAnoLetivo()
    {
        AnoLetivo al = mock(AnoLetivo.class);
        AnoLetivoJPA jpa = mock(AnoLetivoJPA.class);

        when(mapper.toModel(jpa)).thenReturn(al);
        when(mapper.toJpa(al)).thenReturn(jpa);

        when(al.getSigla()).thenReturn("AAA");

        when(anoLetivoJPARepository.findById("AAA")).thenReturn(Optional.empty());

        repository.createAndSaveAnoLetivo(al);
    }
}
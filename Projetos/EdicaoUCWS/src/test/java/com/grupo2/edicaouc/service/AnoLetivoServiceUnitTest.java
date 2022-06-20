package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.mapper.AnoLetivoDTOMapper;
import com.grupo2.edicaouc.jpa.AnoLetivoJPA;
import com.grupo2.edicaouc.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.repository.AnoLetivoRepository;
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
class AnoLetivoServiceUnitTest {
    @InjectMocks
    AnoLetivoRepository repository;


    @InjectMocks
    AnoLetivoService service;
    @MockBean
    AnoLetivoJPAMapper mapper;

    @MockBean
    JpaRepository jpaRepository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }
//TODO
    @Test
    public void shouldCreateValidAnoLetivo(){
        AnoLetivo anoLetivoMock = mock(AnoLetivo.class);
        AnoLetivoJPA anoLetivoJPAMock = mock(AnoLetivoJPA.class);

        when(mapper.toModel(anoLetivoJPAMock)).thenReturn(anoLetivoMock);
        when(mapper.toJpa(anoLetivoMock)).thenReturn(anoLetivoJPAMock);
        when(repository.findById(anoLetivoMock.getSigla())).thenReturn(Optional.of(anoLetivoMock));
        when(jpaRepository.save(anoLetivoJPAMock)).thenReturn(anoLetivoJPAMock);

        AnoLetivo saved = repository.createAndSaveAnoLetivo(anoLetivoMock);

        assertEquals(saved, anoLetivoMock);

}
}
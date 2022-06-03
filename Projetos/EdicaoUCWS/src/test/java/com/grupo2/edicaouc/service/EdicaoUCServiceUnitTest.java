package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCDTOMapper;
import com.grupo2.edicaouc.exception.ErrorDetail;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.repository.EdicaoUCRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class EdicaoUCServiceUnitTest {

    @MockBean
    EdicaoUCRepository repository;

    @MockBean
    EdicaoUCDTOMapper mapper;

    @InjectMocks
    EdicaoUCService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void shouldCreateValidEdicaoUC()
    {
        EdicaoUCDTO edicaoUCDTOMOCK = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUCMock = mock(EdicaoUC.class);

        when(mapper.toModel(edicaoUCDTOMOCK)).thenReturn(edicaoUCMock);
        when(repository.saveEdicaoUC(edicaoUCMock)).thenReturn(edicaoUCMock);
        when(mapper.toDTO(edicaoUCMock)).thenReturn(edicaoUCDTOMOCK);

        when(edicaoUCDTOMOCK.getUcCode()).thenReturn("1");
        when(edicaoUCDTOMOCK.getAnoLetivoCode()).thenReturn("2000-2001");

        EdicaoUCDTO saved = service.createEdicaoUC(edicaoUCDTOMOCK);

        assertEquals(edicaoUCDTOMOCK, saved);
    }

    @Test
    public void shouldFindAllEdicaoByUCCode()
    {
        EdicaoUCDTO edicaoUCDTOMOCK = mock(EdicaoUCDTO.class);
        EdicaoUCDTO edicaoUCDTOMOCK2 = mock(EdicaoUCDTO.class);
        EdicaoUCDTO edicaoUCDTOMOCK3 = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUCMOCK = mock(EdicaoUC.class);
        EdicaoUC edicaoUCMOCK2 = mock(EdicaoUC.class);
        EdicaoUC edicaoUCMOCK3 = mock(EdicaoUC.class);

        when(edicaoUCDTOMOCK.getUcCode()).thenReturn("1");
        when(edicaoUCDTOMOCK.getAnoLetivoCode()).thenReturn("2000-2001");
        when(edicaoUCDTOMOCK2.getUcCode()).thenReturn("2");
        when(edicaoUCDTOMOCK2.getAnoLetivoCode()).thenReturn("2004-2005");
        when(edicaoUCDTOMOCK3.getUcCode()).thenReturn("2");
        when(edicaoUCDTOMOCK3.getAnoLetivoCode()).thenReturn("2002-2003");
        List<EdicaoUCDTO> edicaoUCListDTO = List.of(edicaoUCDTOMOCK2,edicaoUCDTOMOCK3);

        when(edicaoUCMOCK.getUCCode()).thenReturn("1");
        when(edicaoUCMOCK.getAnoLetivoCode()).thenReturn("2000-2001");
        when(edicaoUCMOCK2.getUCCode()).thenReturn("2");
        when(edicaoUCMOCK2.getAnoLetivoCode()).thenReturn("2004-2005");
        when(edicaoUCMOCK3.getUCCode()).thenReturn("2");
        when(edicaoUCMOCK3.getAnoLetivoCode()).thenReturn("2002-2003");
        List<EdicaoUC> edicaoUCList = List.of(edicaoUCMOCK2,edicaoUCMOCK3);


        when(mapper.toDTO(edicaoUCMOCK)).thenReturn(edicaoUCDTOMOCK);
        when(mapper.toDTO(edicaoUCMOCK2)).thenReturn(edicaoUCDTOMOCK2);
        when(mapper.toDTO(edicaoUCMOCK3)).thenReturn(edicaoUCDTOMOCK3);
        when(repository.findAllEdicaoByUCCode("2")).thenReturn(edicaoUCList);
        List<EdicaoUCDTO> resultado =service.findAllEdicaoByUCCode("2");

        assertEquals(resultado,edicaoUCListDTO);
    }
    @Test
    public void shouldFindEdicaoUC_Exists()
    {
        EdicaoUCDTO edicaoUCDTO = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUC = mock(EdicaoUC.class);

        when(repository.findById(1L)).thenReturn(Optional.of(edicaoUC));
        when(mapper.toDTO(edicaoUC)).thenReturn(edicaoUCDTO);

        Optional<EdicaoUCDTO> saved = service.findById(1L);

        assertEquals(edicaoUCDTO, saved.get());
    }
    @Test
    public void shouldNotFindEdicaoUC_Empty()
    {
        EdicaoUCDTO edicaoUCDTO = mock(EdicaoUCDTO.class);
        EdicaoUC edicaoUC = mock(EdicaoUC.class);

        when(repository.findById(1L)).thenReturn(Optional.empty());
        when(mapper.toDTO(edicaoUC)).thenReturn(edicaoUCDTO);

        Optional<EdicaoUCDTO> saved = service.findById(1L);

        assertTrue(saved.isEmpty());
    }
}
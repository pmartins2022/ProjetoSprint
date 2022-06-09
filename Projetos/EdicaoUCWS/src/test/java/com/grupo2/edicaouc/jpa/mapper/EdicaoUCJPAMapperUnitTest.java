package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EdicaoUCJPAMapperUnitTest {

    @MockBean
    EdicaoUCFactory factory;

    @InjectMocks
    EdicaoUCJPAMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldConvertValidEdicao_ValidAtributtes()
    {
        EdicaoUCJPA edicaoUCJPAMOCK = mock(EdicaoUCJPA.class);
        EdicaoUC edicaoUCMOCK = mock(EdicaoUC.class);

        when(edicaoUCJPAMOCK.getId()).thenReturn(1L);
        when(edicaoUCJPAMOCK.getUCCode()).thenReturn("MAT");
        when(edicaoUCJPAMOCK.getAnoLetivoCode()).thenReturn("2001-2002");

        when(factory.createEdicaoUC(1L,"MAT","2001-2002")).thenReturn(edicaoUCMOCK);

        EdicaoUC edicaoUC = mapper.toModel(edicaoUCJPAMOCK);
        assertEquals(edicaoUC,edicaoUCMOCK);
    }
    @Test
    public void shouldNotConvertValidEdicaoUC_InvalidAtributtes()
    {
        EdicaoUCJPA edicaoUCJPAMOCK = mock(EdicaoUCJPA.class);
        when(factory.createEdicaoUC(edicaoUCJPAMOCK.getId(),edicaoUCJPAMOCK.getUCCode(), edicaoUCJPAMOCK.getAnoLetivoCode())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(edicaoUCJPAMOCK));
    }

    @Test
    public void shouldConvertValidEdicaoUCDTO_ValidAtributtes()
    {
        EdicaoUC edicaoUCMOCK = mock(EdicaoUC.class);

        when(edicaoUCMOCK.getId()).thenReturn(1L);
        when(edicaoUCMOCK.getUCCode()).thenReturn("MAT");
        when(edicaoUCMOCK.getAnoLetivoCode()).thenReturn("2001-2002");

        EdicaoUCJPA jpa = mapper.toJPA(edicaoUCMOCK);

        assertEquals(jpa.getId(),edicaoUCMOCK.getId());
        assertEquals(jpa.getAnoLetivoCode(),edicaoUCMOCK.getAnoLetivoCode());
        assertEquals(jpa.getUCCode(),edicaoUCMOCK.getUCCode());
    }

    @Test
    public void shouldNotConvertValidEdicaoUCDTO_InvalidAtributtes()
    {
        EdicaoUCJPA edicaoUCJPAMOCK = mock(EdicaoUCJPA.class);

        when(factory.createEdicaoUC(edicaoUCJPAMOCK.getId(), edicaoUCJPAMOCK.getUCCode(), edicaoUCJPAMOCK.getAnoLetivoCode())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(edicaoUCJPAMOCK));
    }

}
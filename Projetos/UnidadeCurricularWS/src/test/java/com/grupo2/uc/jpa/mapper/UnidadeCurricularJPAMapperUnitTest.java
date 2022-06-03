package com.grupo2.uc.jpa.mapper;

import com.grupo2.uc.exception.ValidacaoInvalidaException;
import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import com.grupo2.uc.model.UnidadeCurricular;
import com.grupo2.uc.model.factory.UnidadeCurricularFactory;
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
class UnidadeCurricularJPAMapperUnitTest {
    @MockBean
    UnidadeCurricularFactory factory;

    @InjectMocks
    UnidadeCurricularJPAMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldConvertValidUnidadeCurricular_ValidAtributtes()
    {
        UnidadeCurricularJPA unidadeCurricularJPAMOCK = mock(UnidadeCurricularJPA.class);
        UnidadeCurricular unidadeCurricularMOCK = mock(UnidadeCurricular.class);

        when(unidadeCurricularJPAMOCK.getSigla()).thenReturn("MAT");
        when(unidadeCurricularJPAMOCK.getDenominacao()).thenReturn("Matematica");

        when(factory.createUnidadeCurricular("MAT","Matematica")).thenReturn(unidadeCurricularMOCK);

        UnidadeCurricular unidadeCurricular = mapper.toModel(unidadeCurricularJPAMOCK);
        assertEquals(unidadeCurricular,unidadeCurricularMOCK);
    }

    @Test
    public void shouldNotConvertValidUnidadeCurricular_InvalidAtributtes()
    {
        UnidadeCurricularJPA unidadeCurricularJPAMOCK = mock(UnidadeCurricularJPA.class);

        when(factory.createUnidadeCurricular(unidadeCurricularJPAMOCK.getSigla(),unidadeCurricularJPAMOCK.getDenominacao())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(unidadeCurricularJPAMOCK));
    }

    @Test
    public void shouldConvertValidUnidadeCurricularDTO_ValidAtributtes()
    {
        UnidadeCurricular unidadeCurricularMOCK = mock(UnidadeCurricular.class);

        when(unidadeCurricularMOCK.getSigla()).thenReturn("MAT");
        when(unidadeCurricularMOCK.getDenominacao()).thenReturn("Matematica");

        UnidadeCurricularJPA jpa = mapper.toJPA(unidadeCurricularMOCK);

        assertEquals(jpa.getSigla(),unidadeCurricularMOCK.getSigla());
        assertEquals(jpa.getDenominacao(),unidadeCurricularMOCK.getDenominacao());

    }

    @Test
    public void shouldNotConvertValidUnidadeCurricularDTO_InvalidAtributtes()
    {
        UnidadeCurricularJPA unidadeCurricularJPAMOCK = mock(UnidadeCurricularJPA.class);

        when(factory.createUnidadeCurricular(unidadeCurricularJPAMOCK.getSigla(),unidadeCurricularJPAMOCK.getDenominacao())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(unidadeCurricularJPAMOCK));
    }

}


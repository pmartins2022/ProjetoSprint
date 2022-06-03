package com.grupo2.anoLetivoWS.jpa.mapper;

import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.jpa.AnoLetivoJPA;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.model.factory.AnoLetivoFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnoLetivoJPAMapperIntegrationTests
{
    @Autowired
    private AnoLetivoJPAMapper mapper;


    @Test
    public void shouldConvertValidAnoLetivo_ValidAtributtes()
    {
        AnoLetivoJPA anoLetivoJPA = new AnoLetivoJPA("2001-2002");
        String sigla = anoLetivoJPA.getSigla();

        AnoLetivo anoLetivo = mapper.toModel(anoLetivoJPA);

        assertEquals(sigla, anoLetivo.getSigla());
    }

    @Test
    public void shouldNotConvertValidAnoLetivo_InvalidAtributtes()
    {
        AnoLetivoJPA anoLetivoJPA = new AnoLetivoJPA("2001asd-2002");

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(anoLetivoJPA));
    }

    @Test
    public void shouldConvertValidAnoLetivoDTO_ValidAtributtes()
    {
        AnoLetivo anoLetivo = new AnoLetivo("2001-2002");
        String sigla = anoLetivo.getSigla();

        AnoLetivoJPA anoLetivoJPA = mapper.toJpa(anoLetivo);

        assertEquals(sigla, anoLetivo.getSigla());
    }
}
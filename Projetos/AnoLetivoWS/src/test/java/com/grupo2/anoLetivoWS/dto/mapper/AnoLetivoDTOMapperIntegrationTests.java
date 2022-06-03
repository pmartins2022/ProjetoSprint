package com.grupo2.anoLetivoWS.dto.mapper;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.model.factory.AnoLetivoFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnoLetivoDTOMapperIntegrationTests
{
    @Autowired
    private AnoLetivoDTOMapper mapper;


    @Test
    public void shouldConvertValidAnoLetivo_ValidAtributtes()
    {
        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO("2001-2002");
        String sigla = anoLetivoDTO.getSigla();

        AnoLetivo anoLetivo = mapper.toModel(anoLetivoDTO);

        assertEquals(sigla, anoLetivo.getSigla());
    }

    @Test
    public void shouldNotConvertValidAnoLetivo_InvalidAtributtes()
    {
        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO("2001asd-2002");

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(anoLetivoDTO));
    }

    @Test
    public void shouldConvertValidAnoLetivoDTO_ValidAtributtes()
    {
        AnoLetivo anoLetivo = new AnoLetivo("2001-2002");
        String sigla = anoLetivo.getSigla();

        AnoLetivoDTO anoLetivoDTO = mapper.toDTO(anoLetivo);

        assertEquals(sigla, anoLetivo.getSigla());
    }
}
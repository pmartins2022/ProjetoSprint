package com.grupo2.anoLetivoWS.model.factory;

import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnoLetivoFactoryTest
{
    @Autowired
    private AnoLetivoFactory factory;

    @Test
    public void shouldCreateValidAnoLetivo()
    {
        assertDoesNotThrow(()-> factory.createAnoLetivo("2001-2002"));
    }

    @Test
    public void shouldNotCreateValidAnoLetivo()
    {
        assertThrows(ValidacaoInvalidaException.class, ()-> factory.createAnoLetivo("2001ol,o,l-2002"));
    }
}
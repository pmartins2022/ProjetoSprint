package com.grupo2.anoLetivoWS.model;

import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnoLetivoTest
{
    @Test
    public void shouldCreateValidAnoLetivo()
    {
        assertDoesNotThrow(()->new AnoLetivo("2001-2002"));
    }

    @Test
    public void shouldNotCreateValidAnoLetivo()
    {
        assertThrows(ValidacaoInvalidaException.class, ()->new AnoLetivo("2001ol,o,l-2002"));
    }

    @Test
    public void shouldEqual_SameMemoryLocation()
    {
        AnoLetivo anoLetivo = new AnoLetivo("2001-2002");

        assertEquals(anoLetivo, anoLetivo);
    }

    @Test
    public void shouldEqual_SameSigla()
    {
        AnoLetivo anoLetivo = new AnoLetivo("2001-2002");
        AnoLetivo anoLetivo1 = new AnoLetivo("2001-2002");

        assertEquals(anoLetivo, anoLetivo1);
    }

    @Test
    public void shouldNotEqual_DiffClass()
    {
        AnoLetivo anoLetivo = new AnoLetivo("2001-2002");

        assertNotEquals(anoLetivo, new Object());
    }


    @Test
    public void shouldNotEqual_DiffSigla()
    {
        AnoLetivo anoLetivo = new AnoLetivo("2001-2002");
        AnoLetivo anoLetivo1 = new AnoLetivo("2003-2004");

        assertNotEquals(anoLetivo, anoLetivo1);
    }
}
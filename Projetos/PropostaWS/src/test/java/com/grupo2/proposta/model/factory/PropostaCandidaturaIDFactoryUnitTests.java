package com.grupo2.proposta.model.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropostaCandidaturaIDFactoryUnitTests
{
    @Autowired
    PropostaCandidaturaIDFactory factory;

    @Test
    public void shouldCreatePropostaCandidaturaID()
    {
        assertDoesNotThrow(()->factory.create(1L,1L));
    }
}
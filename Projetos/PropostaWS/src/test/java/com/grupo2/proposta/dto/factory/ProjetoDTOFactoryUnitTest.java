package com.grupo2.proposta.dto.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjetoDTOFactoryUnitTest
{
    @Autowired
    private ProjetoDTOFactory factory;

    @Test
    public void shouldCreateProjeto()
    {
        assertDoesNotThrow(()->factory.createProjeto(1L,1L,1L));
    }
}
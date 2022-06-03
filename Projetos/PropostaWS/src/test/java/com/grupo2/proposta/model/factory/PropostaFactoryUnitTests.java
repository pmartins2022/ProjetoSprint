package com.grupo2.proposta.model.factory;

import com.grupo2.proposta.exception.ValidacaoInvalidaException;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.PropostaEstado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropostaFactoryUnitTests
{
    @Autowired
    PropostaFactory factory;

    @Test
    public void shouldCreateProposta()
    {
        assertDoesNotThrow(()->factory.createProposta(1L,1L,"AAAAAAAAAA","AAAAAAAAAA","AAAAAAAAAA",1L, PropostaEstado.CANDIDATURA));
    }

    @Test
    public void shouldCreateProposta_2ndConstructor()
    {
        assertDoesNotThrow(()->factory.createProposta(1L,1L,1L,"AAAAAAAAAA","AAAAAAAAAA","AAAAAAAAAA",1L, PropostaEstado.CANDIDATURA));

    }

    @Test
    public void shouldNotCreateProposta_invalidTitulo()
    {
        assertThrows(ValidacaoInvalidaException.class,()->factory.createProposta(1L,1L,"fff","AAAAAAAAAA","AAAAAAAAAA",1L, PropostaEstado.CANDIDATURA));
    }
}
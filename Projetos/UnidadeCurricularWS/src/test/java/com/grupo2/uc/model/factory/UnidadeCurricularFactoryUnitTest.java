package com.grupo2.uc.model.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UnidadeCurricularFactoryUnitTest {
    @Autowired
    private UnidadeCurricularFactory factory;

    @Test
    public void shouldCreateValidUnidadeCurricular()
    {
        assertDoesNotThrow(()-> factory.createUnidadeCurricular("MATA","Matematica Avançada"));
    }
}

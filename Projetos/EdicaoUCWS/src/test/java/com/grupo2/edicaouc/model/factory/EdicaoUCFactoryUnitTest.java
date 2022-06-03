package com.grupo2.edicaouc.model.factory;

import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
class EdicaoUCFactoryUnitTest {

    @Autowired
    private EdicaoUCFactory factory;

    @Test
    public void shouldCreateValidEdicaoUC()
    {
        assertDoesNotThrow(()-> factory.createEdicaoUC(1L,"MAT","2001-2002"));
    }

}
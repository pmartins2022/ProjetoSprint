package com.example.javafx.controller;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EdicaoUCControllerIntegrationTest
{
    @Autowired
    EdicaoUCController controller;

    /*@Test
    public void shouldCreateEdicaoUC_valid()
    {
        EdicaoUCDTO edicaoUCDTO = new EdicaoUCDTO("ucCode", "2000-2001");

        EdicaoUCDTO save = controller.createEdicaoUC("ucCode", "2000-2001");

        assertEquals(edicaoUCDTO,save);
    }*/

}
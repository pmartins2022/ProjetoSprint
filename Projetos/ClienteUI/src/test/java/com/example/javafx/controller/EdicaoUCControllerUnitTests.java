package com.example.javafx.controller;

import com.example.javafx.service.EdicaoUCService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EdicaoUCControllerUnitTests
{
    @MockBean
    EdicaoUCService service;

    @InjectMocks
    EdicaoUCController controller;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


}
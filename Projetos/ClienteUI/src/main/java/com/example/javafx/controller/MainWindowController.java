package com.example.javafx.controller;

import com.example.javafx.service.AnoLetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainWindowController
{
    @Autowired
    private AnoLetivoService service;

    public String getTestString()
    {
        return service.getString();
    }
}

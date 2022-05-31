package com.example.javafx.controller;

import com.example.javafx.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PropostaController
{
    @Autowired
    private PropostaService propostaService;
}

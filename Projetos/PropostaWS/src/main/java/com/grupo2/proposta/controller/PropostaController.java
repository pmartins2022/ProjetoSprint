package com.grupo2.proposta.controller;

import com.grupo2.proposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropostaController
{
    @Autowired
    private PropostaService service;
}

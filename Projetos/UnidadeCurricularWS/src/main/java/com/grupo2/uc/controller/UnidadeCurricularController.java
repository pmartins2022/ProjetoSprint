package com.grupo2.uc.controller;

import com.grupo2.uc.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uc")
public class UnidadeCurricularController
{
    @Autowired
    private UnidadeCurricularService service;

    //TODO: criar os variados casos de uso no controller
}
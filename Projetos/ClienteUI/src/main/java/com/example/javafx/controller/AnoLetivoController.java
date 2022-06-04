package com.example.javafx.controller;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.service.AnoLetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AnoLetivoController
{
    @Autowired
    private AnoLetivoService anoLetivoService;

    public AnoLetivoDTO createAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws RestPostException
    {
        return anoLetivoService.createAnoLetivo(anoLetivoDTO);
    }

}
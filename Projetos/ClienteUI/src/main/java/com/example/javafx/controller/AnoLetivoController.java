package com.example.javafx.controller;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.RestException;
import com.example.javafx.service.AnoLetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AnoLetivoController
{
    @Autowired
    private AnoLetivoService anoLetivoService;

    public AnoLetivoDTO createAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws RestException
    {
        return anoLetivoService.createAnoLetivo(anoLetivoDTO);
    }

    public String putTest(AnoLetivoDTO dto, long pathVar, long id)
    {
        return anoLetivoService.putTest(dto, pathVar, id);
    }
}
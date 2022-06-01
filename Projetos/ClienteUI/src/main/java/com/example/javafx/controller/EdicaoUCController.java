package com.example.javafx.controller;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.exception.RestException;
import com.example.javafx.service.EdicaoUCService;
import com.example.javafx.dto.UnidadeCurricularDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EdicaoUCController
{
    @Autowired
    private EdicaoUCService service;

    /*
    Primeiro vou bscar a lista
    Depois tento criar  (no seu WS de EdicaoUC ele a verifica se existe)
     */
    public List<UnidadeCurricularDTO> findAllUC()
    {
        List<UnidadeCurricularDTO> listUCDTO = service.findAllUC();

        return listUCDTO;
    }

    public List<AnoLetivoDTO> findAllAnoLetivo()
    {
        List<AnoLetivoDTO> listAnoLetivoDTO = service.findAllAnoLetivo();

        return listAnoLetivoDTO;
    }

    public EdicaoUCDTO createEdicaoUC(UnidadeCurricularDTO ucDTO, AnoLetivoDTO anoLetivoDTO) throws RestException
    {
        EdicaoUCDTO edicaoUCDTO = service.createAndSave(ucDTO, anoLetivoDTO);

        return edicaoUCDTO;
    }
}
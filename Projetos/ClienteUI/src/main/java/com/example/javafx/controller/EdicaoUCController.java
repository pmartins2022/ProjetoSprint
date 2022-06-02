package com.example.javafx.controller;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.exception.RestPostException;
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

    public EdicaoUCDTO createEdicaoUC(UnidadeCurricularDTO ucDTO, AnoLetivoDTO anoLetivoDTO) throws RestPostException
    {
        EdicaoUCDTO edicaoUCDTO = service.createAndSave(ucDTO, anoLetivoDTO);

        return edicaoUCDTO;
    }
}

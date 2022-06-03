package com.example.javafx.controller;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.service.EdicaoUCService;
import com.example.javafx.dto.UnidadeCurricularDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EdicaoUCController
{
    @Autowired
    private EdicaoUCService service;

    private final List<UnidadeCurricularDTO> listaUC;
    private final List<AnoLetivoDTO> listaAL;

    public EdicaoUCController()
    {
        listaUC = new ArrayList<>();
        listaAL = new ArrayList<>();
    }

    /*
    Primeiro vou bscar a lista
    Depois tento criar  (no seu WS de EdicaoUC ele a verifica se existe)
     */
    public List<String> findAllUC()
    {
        service.findAllUC().forEach(e -> {
            if (!listaUC.contains(e))
                listaUC.add(e);
        });
        return listaUC.stream().map(UnidadeCurricularDTO::getSigla).toList();
    }

    public List<String> findAllAnoLetivo()
    {
        service.findAllAnoLetivo().forEach(e -> {
            if (!listaAL.contains(e))
                listaAL.add(e);
        });
        return listaAL.stream().map(AnoLetivoDTO::getSigla).toList();
    }

    public EdicaoUCDTO createEdicaoUC(int uc, int anoLetivo)
    {
        UnidadeCurricularDTO ucDTO = listaUC.get(uc);
        AnoLetivoDTO anoLetivoDTO = listaAL.get(anoLetivo);

        return service.createAndSave(ucDTO, anoLetivoDTO);
    }
}
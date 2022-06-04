package com.example.javafx.dto.factory;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestPostException;
import org.springframework.stereotype.Component;

@Component
public class EdicaoUCDTOFactory
{
    public EdicaoUCDTO createEdicaoUCDTO(String ucSigla, String anoLetivoSigla)
    {
        return new EdicaoUCDTO(ucSigla, anoLetivoSigla);
    }
}

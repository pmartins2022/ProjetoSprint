package com.example.javafx.dto.factory;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestPostException;
import org.springframework.stereotype.Component;

/**
 * Classe factory para criar edições de uma unidade curricular.
 */
@Component
public class EdicaoUCDTOFactory
{
    /**
     * Criar uma edição de uma unidade curricular.
     * @param ucSigla Sigla da unidade curricular.
     * @param anoLetivoSigla Sigla do ano letivo.
     * @return Edição de uma unidade curricular.
     */
    public EdicaoUCDTO createEdicaoUCDTO(String ucSigla, String anoLetivoSigla, Long rucID)
    {
        return new EdicaoUCDTO(ucSigla, anoLetivoSigla, rucID);
    }
}

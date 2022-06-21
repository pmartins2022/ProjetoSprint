package com.example.javafx.controller.admin;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.service.EdicaoUCService;
import com.example.javafx.dto.UnidadeCurricularDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controller para uma edição de uma unidade curricular.
 */
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

    /**
     * Buscar a lista de unidades curriculares.
     * @return Lista de unidades curriculares em formato String.
     */
    public List<String> findAllUC()
    {
        listaUC.clear();

        listaUC.addAll(service.findAllUC());

        return listaUC.stream().map(UnidadeCurricularDTO::getSigla).toList();
    }

    /**
     * Buscar a lista de anos letivos.
     * @return Lista de anos letivos em formato String.
     */
    public List<String> findAllAnoLetivo()
    {
        listaAL.clear();

        listaAL.addAll(service.findAllAnoLetivo());

        return listaAL.stream().map(AnoLetivoDTO::getSigla).toList();
    }

    /**
     * Criar uma edição de uma unidade curricular.
     * @param uc indice da unidade curricular.
     * @param anoLetivo indice do ano letivo.
     * @return Edição de uma unidade curricular.
     */
    public EdicaoUCDTO createEdicaoUC(int uc, int anoLetivo, Long rucId)
    {
        UnidadeCurricularDTO ucDTO = listaUC.get(uc);
        AnoLetivoDTO anoLetivoDTO = listaAL.get(anoLetivo);

        return service.createAndSave(ucDTO, anoLetivoDTO,rucId);
    }
}
package com.example.javafx.service;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.repository.rest.AnoLetivoRestRepo;
import com.example.javafx.repository.rest.EdicaoUCRestRepo;
import com.example.javafx.repository.rest.UnidadeCurricularRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdicaoUCService
{
    @Autowired
    private AnoLetivoRestRepo anoLetivoRestRepo;

    @Autowired
    private UnidadeCurricularRestRepo unidadeCurricularRestRepo;
    @Autowired
    private EdicaoUCRestRepo edicaoUCRestRepo;

    public List<UnidadeCurricularDTO> findAllUC()
    {
        List<UnidadeCurricularDTO> listUCDTO = unidadeCurricularRestRepo.findAll();

        return listUCDTO;
    }

    public List<AnoLetivoDTO> findAllAnoLetivo()
    {

        List<AnoLetivoDTO> listAnoLetivoDTO = anoLetivoRestRepo.findAll();

        return listAnoLetivoDTO;
    }

    public EdicaoUCDTO createAndSave(UnidadeCurricularDTO ucDTO, AnoLetivoDTO anoLetivoDTO)
    {
        EdicaoUCDTO edicaoUCDTO = new EdicaoUCDTO(ucDTO.getSigla(), anoLetivoDTO.getSigla());

        EdicaoUCDTO saved = edicaoUCRestRepo.createEdicaoUC(edicaoUCDTO);

        return saved;
    }
}

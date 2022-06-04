package com.example.javafx.service;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.dto.factory.EdicaoUCDTOFactory;
import com.example.javafx.exception.RestPostException;
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
    @Autowired
    private EdicaoUCDTOFactory factory;

    public List<UnidadeCurricularDTO> findAllUC()
    {
        List<UnidadeCurricularDTO> listUCDTO = unidadeCurricularRestRepo.findAll();

        return listUCDTO;
    }

    public List<AnoLetivoDTO> findAllAnoLetivo() throws RestPostException
    {

        List<AnoLetivoDTO> listAnoLetivoDTO = anoLetivoRestRepo.findAll();

        return listAnoLetivoDTO;
    }

    public EdicaoUCDTO createAndSave(UnidadeCurricularDTO ucDTO, AnoLetivoDTO anoLetivoDTO) throws RestPostException
    {
        EdicaoUCDTO edicaoUCDTO = factory.createEdicaoUCDTO(ucDTO.getSigla(), anoLetivoDTO.getSigla());

        EdicaoUCDTO saved = edicaoUCRestRepo.createEdicaoUC(edicaoUCDTO);

        return saved;
    }
}

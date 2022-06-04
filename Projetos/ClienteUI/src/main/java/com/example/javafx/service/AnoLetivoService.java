package com.example.javafx.service;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.AnoLetivoRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnoLetivoService
{
    @Autowired
    private AnoLetivoRestRepo restRepo;

    public String getString()
    {
        return restRepo.getString();
    }

    public AnoLetivoDTO createAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws RestPostException
    {
        return restRepo.createAnoLetivo(anoLetivoDTO);
    }
}
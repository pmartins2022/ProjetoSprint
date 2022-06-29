package com.example.javafx.service;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.repository.rest.AnoLetivoRestRepo;
import com.example.javafx.repository.rest.AvaliacaoRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService
{
    @Autowired
    private AvaliacaoRestRepo restRepo;

    public List<AvaliacaoDTO> findEditableAvaliacoes()
    {
        return restRepo.findEditableAvaliacoes();
    }
}

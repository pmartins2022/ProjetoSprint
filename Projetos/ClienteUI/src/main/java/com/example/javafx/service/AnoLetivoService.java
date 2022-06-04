package com.example.javafx.service;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.AnoLetivoRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe service para ano letivo.
 */
@Service
public class AnoLetivoService
{
    @Autowired
    private AnoLetivoRestRepo restRepo;

    /**
     * Criar um ano letivo.
     * @param anoLetivoDTO Ano Letivo a criar.
     * @return Ano Letivo criado.
     * @throws RestPostException Erro ao criar ano letivo.
     */
    public AnoLetivoDTO createAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws RestPostException
    {
        return restRepo.createAnoLetivo(anoLetivoDTO);
    }
}
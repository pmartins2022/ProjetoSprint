package com.example.javafx.controller.admin;

import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.service.AnoLetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Classe controller para ano letivo.
 */
@Controller
public class AnoLetivoController
{
    @Autowired
    private AnoLetivoService anoLetivoService;

    /**
     * Criar um ano letivo
     * @param anoLetivoDTO Ano letivo a ser criado.
     * @return Ano letivo criado.
     * @throws RestPostException Exceção ao criar ano letivo.
     */
    public AnoLetivoDTO createAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws RestPostException
    {
        return anoLetivoService.createAnoLetivo(anoLetivoDTO);
    }

}
package com.example.javafx.controller;

import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UnidadeCurricularController
{
    private final List<UnidadeCurricularDTO> lista;

    @Autowired
    private UnidadeCurricularService unidadeCurricularService;

    public UnidadeCurricularController()
    {
        lista = new ArrayList<>();
    }

    public UnidadeCurricularDTO createUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws RestPostException
    {
        return unidadeCurricularService.createUnidadeCurricular(unidadeCurricularDTO);
    }

    public UnidadeCurricularDTO getFromLista(int index)
    {
        return lista.get(index);
    }

    public List<String> findAllUnidadeCurricular()
    {
        unidadeCurricularService.findAll().forEach(e -> {
            if (!lista.contains(e))
                lista.add(e);
        });

        return lista.stream().map(UnidadeCurricularDTO::getSigla).toList();
    }
}
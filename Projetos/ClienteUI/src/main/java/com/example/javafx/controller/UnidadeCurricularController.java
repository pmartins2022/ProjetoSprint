package com.example.javafx.controller;

import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controller para uma unidade curricular.
 */
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

    /**
     * Criar uma unidade curricular.
     * @param unidadeCurricularDTO Unidade curricular a ser criada.
     * @return Unidade curricular criada.
     * @throws RestPostException Exceção ao criar unidade curricular.
     */
    public UnidadeCurricularDTO createUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws RestPostException
    {
        return unidadeCurricularService.createUnidadeCurricular(unidadeCurricularDTO);
    }

    /**
     * Obter uma unidade curricular pelo index da lista.
     * @param index indice da unidade curricular.
     * @return Unidade curricular.
     */
    public UnidadeCurricularDTO getFromLista(int index)
    {
        return lista.get(index);
    }

    /**
     * Buscar a lista de unidades curriculares.
     * @return Lista de unidades curriculares em formato String.
     */
    public List<String> findAllUnidadeCurricular()
    {
        unidadeCurricularService.findAll().forEach(e -> {
            if (!lista.contains(e))
                lista.add(e);
        });

        return lista.stream().map(UnidadeCurricularDTO::getSigla).toList();
    }
}
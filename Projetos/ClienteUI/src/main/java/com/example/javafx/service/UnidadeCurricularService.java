package com.example.javafx.service;

import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.UnidadeCurricularRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para unidade curricular.
 */
@Service
public class UnidadeCurricularService
{
    @Autowired
    private UnidadeCurricularRestRepo restRepo;

    /**
     * Criar uma unidade curricular.
     * @param unidadeCurricularDTO Unidade Curricular a criar.
     * @return Unidade Curricular criada.
     * @throws RestPostException Erro ao criar unidade curricular.
     */
    public UnidadeCurricularDTO createUnidadeCurricular(UnidadeCurricularDTO unidadeCurricularDTO) throws RestPostException
    {
        return restRepo.createUnidadeCurricular(unidadeCurricularDTO);
    }

    public List<UnidadeCurricularDTO> findAll()
    {
        return restRepo.findAll();
    }
}

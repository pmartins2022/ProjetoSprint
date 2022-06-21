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

/**
 * Classe service para edição de uma unidade curricular.
 */
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

    /**
     * Obter lista de todas as unidades curriculares.
     * @return
     */
    public List<UnidadeCurricularDTO> findAllUC()
    {
        List<UnidadeCurricularDTO> listUCDTO = unidadeCurricularRestRepo.findAll();

        return listUCDTO;
    }

    /**
     * Obter lista de todos os anos letivos.
     * @return Lista de anos letivos.
     * @throws RestPostException Erro ao obter lista de anos letivos.
     */
    public List<AnoLetivoDTO> findAllAnoLetivo() throws RestPostException
    {
        List<AnoLetivoDTO> listAnoLetivoDTO = anoLetivoRestRepo.findAll();

        return listAnoLetivoDTO;
    }

    /**
     * Criar uma edição de unidade curricular.
     * @param ucDTO informacao da Unidade Curricular.
     * @param anoLetivoDTO informacao de Ano Letivo.
     * @return Edição de unidade curricular criada.
     * @throws RestPostException Erro ao criar edição de unidade curricular.
     */
    public EdicaoUCDTO createAndSave(UnidadeCurricularDTO ucDTO, AnoLetivoDTO anoLetivoDTO, Long rucId) throws RestPostException
    {
        EdicaoUCDTO edicaoUCDTO = factory.createEdicaoUCDTO(ucDTO.getSigla(), anoLetivoDTO.getSigla(),rucId);

        EdicaoUCDTO saved = edicaoUCRestRepo.createEdicaoUC(edicaoUCDTO);

        return saved;
    }
}

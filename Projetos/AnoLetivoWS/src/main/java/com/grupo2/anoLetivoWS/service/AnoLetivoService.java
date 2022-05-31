package com.grupo2.anoLetivoWS.service;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.dto.mapper.AnoLetivoDTOMapper;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.repository.AnoLetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnoLetivoService
{
    @Autowired
    private AnoLetivoRepository repository;

    @Autowired
    private AnoLetivoDTOMapper mapper;

    public AnoLetivoDTO createAndSaveAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws ValidacaoInvalidaException
    {
        AnoLetivo anoLetivo = mapper.toModel(anoLetivoDTO);

        AnoLetivo savedAnoLetivo = repository.createAndSaveAnoLetivo(anoLetivo);

        return mapper.toDTO(savedAnoLetivo);
    }

    public List<AnoLetivoDTO> findAll()
    {
        List<AnoLetivo> lista = repository.findAll();

        List<AnoLetivoDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }
}

package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.mapper.AnoLetivoDTOMapper;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.repository.AnoLetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe que inclui metodo que comunicam com o repositorio atual. Possui metodo para criar anos letivos e
 * listagem dos anos letivos existentes.
 */
@Service
public class AnoLetivoService
{
    @Autowired
    private AnoLetivoRepository repository;

    @Autowired
    private AnoLetivoDTOMapper mapper;

    /**
     * @param anoLetivoDTO
     * @return
     * @throws ValidacaoInvalidaException
     */
    public AnoLetivoDTO createAndSaveAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws ValidacaoInvalidaException
    {
        AnoLetivo anoLetivo = mapper.toModel(anoLetivoDTO);

        AnoLetivo savedAnoLetivo = repository.createAndSaveAnoLetivo(anoLetivo);

        return mapper.toDTO(savedAnoLetivo);
    }

    public List<AnoLetivoDTO> findAll()
    {
        List<AnoLetivo> lista = repository.findAll();

        return lista.stream().map(mapper::toDTO).toList();
    }

    public Optional<AnoLetivoDTO> findByID(String id)
    {
        Optional<AnoLetivo> anoLetivo = repository.findById(id);

        if (anoLetivo.isEmpty())
        {
            return Optional.empty();
        }

        AnoLetivoDTO anoLetivoDTO = mapper.toDTO(anoLetivo.get());

        return Optional.of(anoLetivoDTO);
    }
}

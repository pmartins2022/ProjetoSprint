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
 Classe de Service de AnoLetivoService. Permite a ligação entre o AnoLetivoController e o resto da API
 */
@Service
public class AnoLetivoService
{
    @Autowired
    private AnoLetivoRepository repository;

    @Autowired
    private AnoLetivoDTOMapper mapper;

    /**
     * Devolve AnoLetivoDTO após persistência
     * @param anoLetivoDTO anoLetivo a guardar
     * @return AnoLetivoDTO guardado
     * @throws ValidacaoInvalidaException erro de validação dos dados do AnoLetivoDTO a guardar
     */
    public AnoLetivoDTO createAndSaveAnoLetivo(AnoLetivoDTO anoLetivoDTO) throws ValidacaoInvalidaException
    {
        AnoLetivo anoLetivo = mapper.toModel(anoLetivoDTO);

        AnoLetivo savedAnoLetivo = repository.createAndSaveAnoLetivo(anoLetivo);

        return mapper.toDTO(savedAnoLetivo);
    }

    /**
     * Devolve Lista de AnoLetivoDTO
     * @return Lista de AnoLetivoDTO
     */
    public List<AnoLetivoDTO> findAll()
    {
        List<AnoLetivo> lista = repository.findAll();

        return lista.stream().map(mapper::toDTO).toList();
    }

    /**
     * Devolve AnoLetivoDTO filtrado pelo ID ou Optional.Empty()
     * @param id id
     * @return AnoLetivoDTO ou Optional.Empty()
     */
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

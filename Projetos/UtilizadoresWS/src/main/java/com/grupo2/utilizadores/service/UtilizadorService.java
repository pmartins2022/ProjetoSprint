package com.grupo2.utilizadores.service;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.dto.mapper.UtilizadorDTOMapper;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilizadorService
{
    @Autowired
    private UtilizadorRepository repository;

    @Autowired
    private UtilizadorDTOMapper mapper;

    public Optional<UtilizadorDTO> findByID(Long id)
    {
        Optional<Utilizador> optionalUtilizador = repository.findByID(id);

        if (optionalUtilizador.isPresent())
        {
            UtilizadorDTO dto = mapper.toDTO(optionalUtilizador.get());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public UtilizadorDTO createAndSave(UtilizadorDTO dto)
    {
        Utilizador utilizador = mapper.toModel(dto);

        Utilizador saved = repository.save(utilizador);

        UtilizadorDTO dtoSaved = mapper.toDTO(saved);

        return dtoSaved;
    }
}

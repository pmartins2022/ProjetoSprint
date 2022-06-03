package com.grupo2.utilizadores.service;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.dto.mapper.UtilizadorDTOMapper;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe de Service do utilizador. Possui endpoints para createAndSave e findById.
 */
@Service
public class UtilizadorService
{
    /**
     * O repository a ser utilizado por este Service.
     */
    @Autowired
    private UtilizadorRepository repository;

    /**
     * O mapper a ser utilizado por este Service.
     */
    @Autowired
    private UtilizadorDTOMapper mapper;

    /**
     * Endpoint que possibilita encontrar o utilizador por id existente no serviço.
     * @param id um objeto com os dados do id
     * @return um utilizador.
     */
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

    /**
     * Endpoint que possibilita criar um utilizador.
     * @param dto um objeto com os dados do utilizador
     * @return um utilizador
     */
    public UtilizadorDTO createAndSave(UtilizadorDTO dto)
    {
        Utilizador utilizador = mapper.toModel(dto);

        Utilizador saved = repository.save(utilizador);

        UtilizadorDTO dtoSaved = mapper.toDTO(saved);

        return dtoSaved;
    }
}

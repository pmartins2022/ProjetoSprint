package com.grupo2.utilizadores.service;

import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.dto.mapper.UtilizadorDTOMapper;
import com.grupo2.utilizadores.exception.ErroGeralException;
import com.grupo2.utilizadores.exception.OptionalVazioException;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public UtilizadorDTO registar(UtilizadorDTO utilizadorDTO)
    {
        if (repository.findByUsername(utilizadorDTO.getUsername()).isPresent())
        {
            throw new IllegalArgumentException("Utilizador já existe");
        }

        Utilizador utilizador = mapper.toModel(utilizadorDTO);

        utilizador.setPassword(new BCryptPasswordEncoder().encode(utilizador.getPassword()));

        utilizador = repository.save(utilizador);
        return mapper.toDTO(utilizador);
    }

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
    public UtilizadorDTO createAndSave(UtilizadorDTO dto) throws ErroGeralException
    {
        Utilizador utilizador = mapper.toModel(dto);

        Utilizador saved = repository.save(utilizador);

        return mapper.toDTO(saved);
    }

    public Optional<UtilizadorAuthDTO> findByUsername(String username)
    {
        Optional<Utilizador> username1 = repository.findByUsername(username);

        if (username1.isPresent())
        {
            return Optional.of(mapper.toAuthDTO(username1.get()));
        }
        return Optional.empty();
    }

    public Boolean isRole(String role, Long id) throws OptionalVazioException
    {
        Optional<UtilizadorDTO> utilizador = findByID(id);

        if (utilizador.isEmpty())
        {
            throw new OptionalVazioException("Utilizador com esse id "+id+" não existe");
        }

        return utilizador.get().getTipoUtilizador().toString().equals(role);
    }

    public List<UtilizadorDTO> findAll()
    {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }
}

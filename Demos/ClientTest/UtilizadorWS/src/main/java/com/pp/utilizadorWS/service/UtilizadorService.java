package com.pp.utilizadorWS.service;

import com.pp.utilizadorWS.dto.UtilizadorDTO;
import com.pp.utilizadorWS.dto.mapper.UtilizadorDTOMapper;
import com.pp.utilizadorWS.model.TipoUtilizador;
import com.pp.utilizadorWS.model.Utilizador;
import com.pp.utilizadorWS.repository.jpa.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Service
public class UtilizadorService
{
    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private UtilizadorDTOMapper mapper;

    public Optional<UtilizadorDTO> findByUsername(String username)
    {
        Optional<Utilizador> username1 = utilizadorRepository.findByUsername(username);

        if (username1.isPresent())
        {
            return Optional.of(mapper.toDTO(username1.get()));
        }
        return Optional.empty();
    }

    public UtilizadorDTO registar(UtilizadorDTO utilizadorDTO)
    {
        if (utilizadorRepository.findByUsername(utilizadorDTO.getUsername()).isPresent())
        {
            throw new IllegalArgumentException("Utilizador já existe");
        }

        Utilizador utilizador = mapper.toModel(utilizadorDTO);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        utilizador.setPassword(new BCryptPasswordEncoder().encode(utilizador.getPassword()));

        utilizador = utilizadorRepository.save(utilizador);
        return mapper.toDTO(utilizador);
    }

    public TipoUtilizador getUserType(String token)
    {
        Base64.Decoder decoder = Base64.getDecoder();
        String decoded = Arrays.toString(decoder.decode(token));
        String user = decoded.split(":")[0];

        Optional<Utilizador> utilizador = utilizadorRepository.findByUsername(user);
        if (utilizador.isPresent())
        {
            return utilizador.get().getTipoUtilizador();
        }
        else
        {
            throw new IllegalArgumentException("Utilizador não encontrado");
        }
    }
}

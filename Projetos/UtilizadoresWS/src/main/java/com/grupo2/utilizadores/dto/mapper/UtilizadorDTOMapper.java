package com.grupo2.utilizadores.dto.mapper;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.model.factory.UtilizadorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UtilizadorDTOMapper
{
    @Autowired
    private UtilizadorFactory factory;

    public Utilizador toModel(UtilizadorDTO dto)
    {
        Utilizador utilizador = factory.createUtilizador(dto.getId(), dto.getNome(), dto.getSobrenome(),
                dto.getEmail(), dto.getTipoUtilizador());

        return utilizador;
    }

    public UtilizadorDTO toDTO(Utilizador utilizador)
    {
        UtilizadorDTO dto = new UtilizadorDTO(utilizador.getId(), utilizador.getNome(), utilizador.getSobrenome(),
                utilizador.getEmail(), utilizador.getTipoUtilizador());

        return dto;
    }
}


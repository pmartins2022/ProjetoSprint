package com.pp.utilizadorWS.dto.mapper;

import com.pp.utilizadorWS.dto.UtilizadorAuthDTO;
import com.pp.utilizadorWS.dto.UtilizadorDTO;
import com.pp.utilizadorWS.model.Utilizador;
import org.springframework.stereotype.Component;

@Component
public class UtilizadorDTOMapper
{
    public Utilizador toModel(UtilizadorDTO user)
    {
        return new Utilizador(user.getId(),user.getUsername(),user.getPassword(),user.getTipoUtilizador());
    }

    public UtilizadorDTO toDTO(Utilizador user)
    {
        return new UtilizadorDTO(user.getId(),user.getUsername(),user.getPassword(),user.getTipoUtilizador());
    }

    public UtilizadorAuthDTO toAuthDTO(Utilizador user)
    {
        return new UtilizadorAuthDTO(user.getId(), user.getUsername(), user.getPassword(), user.getTipoUtilizador().toString());
    }
}
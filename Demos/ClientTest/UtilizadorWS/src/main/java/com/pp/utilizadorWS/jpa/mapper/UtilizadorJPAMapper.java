package com.pp.utilizadorWS.jpa.mapper;

import com.pp.utilizadorWS.jpa.UtilizadorJPA;
import com.pp.utilizadorWS.model.Utilizador;
import org.springframework.stereotype.Component;

@Component
public class UtilizadorJPAMapper
{
    public UtilizadorJPA toJPA(Utilizador user)
    {
        return new UtilizadorJPA(user.getId(),user.getUsername(),user.getPassword(),user.getTipoUtilizador());
    }

    public Utilizador toModel(UtilizadorJPA user)
    {
        return new Utilizador(user.getId(),user.getUsername(),user.getPassword(),user.getTipoUtilizador());
    }
}
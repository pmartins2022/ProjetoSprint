package com.grupo2.utilizadores.jpa.mapper;

import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.model.factory.UtilizadorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilizadorJPAMapper
{
    @Autowired
    private UtilizadorFactory factory;

    public Utilizador toModel(UtilizadorJPA jpa)
    {
        Utilizador utilizador = factory.createUtilizador(jpa.getId(), jpa.getNome(), jpa.getSobrenome(), jpa.getEmail(),
                jpa.getTipoUtilizador());

        return utilizador;
    }

    public UtilizadorJPA toJPA(Utilizador utilizador)
    {
        UtilizadorJPA jpa = new UtilizadorJPA(utilizador.getId(), utilizador.getNome(), utilizador.getSobrenome(),
                utilizador.getEmail(), utilizador.getTipoUtilizador());

        return jpa;
    }

}

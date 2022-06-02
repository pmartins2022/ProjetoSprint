package com.grupo2.utilizadores.model.factory;

import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UtilizadorFactory
{
    public Utilizador createUtilizador(Long id, String nome, String sobrenome, String email,
                                       TipoUtilizador tipoUtilizador)
    {
        Utilizador utilizador = new Utilizador(id, nome, sobrenome, email, tipoUtilizador);

        return utilizador;
    }
}

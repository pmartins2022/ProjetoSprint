package com.grupo2.utilizadores.model.factory;

import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UtilizadorFactory
{
    /**
     * Cria objetos do tipo utilizador
     * @param id recebe o id dos mappers
     * @param nome recebe o nome dos mappers
     * @param sobrenome recebe o sobrenome dos mappers
     * @param email recebe o email dos mappers
     * @param tipoUtilizador recebe o tipoUtilizador dos mappers
     * @return um novo objeto do tipo Utilizador
     */

    public Utilizador createUtilizador(Long id, String nome, String sobrenome, String email,
                                       TipoUtilizador tipoUtilizador)
    {
        Utilizador utilizador = new Utilizador(id, nome, sobrenome, email, tipoUtilizador);

        return utilizador;
    }
}

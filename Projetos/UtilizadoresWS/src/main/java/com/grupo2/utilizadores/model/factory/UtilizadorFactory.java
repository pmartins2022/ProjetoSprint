package com.grupo2.utilizadores.model.factory;

import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Classe que gere a instanciacao de objetos do tipo Utilizador
 */

@Component
public class UtilizadorFactory
{
    /**
     * Cria objeto do tipo utilizador
     * @param id recebe o id dos mappers
     * @param nome recebe o nome dos mappers
     * @param sobrenome recebe o sobrenome dos mappers
     * @param email recebe o email dos mappers
     * @param tipoUtilizador recebe o tipoUtilizador dos mappers
     * @return um novo objeto do tipo Utilizador
     */
    public Utilizador createUtilizador(Long id, String nome, String sobrenome, String email,
                                       String username, String password, TipoUtilizador tipoUtilizador) throws ValidacaoInvalidaException
    {
        return new Utilizador(id, nome, sobrenome, email, username, password, tipoUtilizador);
    }

    public Utilizador createUtilizador(String nome, String sobrenome, String email,
                                        String username, String password, TipoUtilizador tipoUtilizador) throws ValidacaoInvalidaException
    {

        return new Utilizador(nome, sobrenome, email, username, password, tipoUtilizador);
    }
}

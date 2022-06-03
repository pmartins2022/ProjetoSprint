package com.grupo2.utilizadores.jpa.mapper;

import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.model.factory.UtilizadorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe para fazer a conversao entre objetos Utilizador de JPA para classe de dominio.
 */
@Service
public class UtilizadorJPAMapper
{
    /**
     * O factory a ser utilizado por este JPA Mapper.
     */
    @Autowired
    private UtilizadorFactory factory;

    /**
     * Fazer a conversao para classe de dominio.
     * @param jpa o objeto jpa com os dados
     * @return o objeto convertido
     */
    public Utilizador toModel(UtilizadorJPA jpa) throws ValidacaoInvalidaException
    {
        return factory.createUtilizador(jpa.getId(), jpa.getNome(), jpa.getSobrenome(), jpa.getEmail(),
                jpa.getTipoUtilizador());
    }

    /**
     * Fazer a conversao para classe JPA
     * @param utilizador o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public UtilizadorJPA toJPA(Utilizador utilizador) throws ValidacaoInvalidaException
    {

        return new UtilizadorJPA(utilizador.getId(), utilizador.getNome(), utilizador.getSobrenome(),
                utilizador.getEmail(), utilizador.getTipoUtilizador());
    }

}

package com.grupo2.utilizadores.dto.mapper;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.model.factory.UtilizadorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Classe para fazer a conversao entre objetos Utilizador de DTO para classe de dominio.
 */
@Component
public class UtilizadorDTOMapper
{
    /**
     * O factory a ser utilizado por este DTO Mapper.
     */
    @Autowired
    private UtilizadorFactory factory;

    /**
     * Fazer a conversao para classe de dominio.
     * @param dto o objeto dto com os dados
     * @return o objeto convertido
     */
    public Utilizador toModel(UtilizadorDTO dto)
    {
        Utilizador utilizador = factory.createUtilizador(dto.getId(), dto.getNome(), dto.getSobrenome(),
                dto.getEmail(), dto.getTipoUtilizador());

        return utilizador;
    }

    /**
     * Fazer a conversao para classe DTO
     * @param utilizador o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public UtilizadorDTO toDTO(Utilizador utilizador)
    {
        UtilizadorDTO dto = new UtilizadorDTO(utilizador.getId(), utilizador.getNome(), utilizador.getSobrenome(),
                utilizador.getEmail(), utilizador.getTipoUtilizador());

        return dto;
    }
}


package com.grupo2.utilizadores.controller;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.OptionalVazioException;
import com.grupo2.utilizadores.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Classe REST Controller de utilizador. Possui endpoints para findByID.
 */
@RestController
@RequestMapping("/utilizador")
public class UtilizadorController
{
    /**
     * O serviço a ser utilizado por este REST Controller.
     */
    @Autowired
    private UtilizadorService service;

    /**
     * Endpoint que possibilita encontrar o utilizador por id existente no serviço.
     * @param id um objeto com os dados do utilizador
     * @return utilizador, ou um erro se os dados estiverem invalidos.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByID(@PathVariable("id") Long id)
    {
        Optional<UtilizadorDTO> optionalUtilizadorDTO = service.findByID(id);

        if (optionalUtilizadorDTO.isEmpty())
        {
            throw new OptionalVazioException("Não existe Utilizador com esse ID");
        }
        return new ResponseEntity<>(optionalUtilizadorDTO, HttpStatus.OK);
    }
}

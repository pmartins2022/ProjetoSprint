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

@RestController
@RequestMapping("/utilizador")
public class UtilizadorController
{
    @Autowired
    private UtilizadorService service;

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

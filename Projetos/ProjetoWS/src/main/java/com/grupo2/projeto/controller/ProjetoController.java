package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projeto")
public class ProjetoController
{
    @Autowired
    private ProjetoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> getById(@PathVariable Long id) {

        try
        {
            Optional<ProjetoDTO> optionalProjetoDTO = service.getProjeto( id );

            if( optionalProjetoDTO.isPresent() ) {
                ProjetoDTO projetoDTO = optionalProjetoDTO.get();
                return new ResponseEntity<>(projetoDTO, HttpStatus.OK);
            }
            else
                return ResponseEntity.notFound().build();
        }
        catch (ErroGeralException e)
        {
            throw new ErroGeralException(e.getMessage());
        }
    }
}

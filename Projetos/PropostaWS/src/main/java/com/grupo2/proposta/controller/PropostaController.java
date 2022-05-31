package com.grupo2.proposta.controller;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proposta")
public class PropostaController
{
    @Autowired
    private PropostaService service;

    @PostMapping("/create")
    public ResponseEntity<PropostaDTO> createProposta(@RequestBody PropostaDTO dto)
    {
        try
        {
            PropostaDTO proposta = service.createProposta(dto);

            return new ResponseEntity<>(proposta, HttpStatus.OK);
        }
        catch (BaseDadosException e)
        {
            throw new BaseDadosException(e.getMessage());
        }
    }

    @GetMapping("/rejeitar/{id}")
    public ResponseEntity<Object> rejeitarProposta(@PathVariable(name = "id") Long id)
    {
        return null;
    }

}

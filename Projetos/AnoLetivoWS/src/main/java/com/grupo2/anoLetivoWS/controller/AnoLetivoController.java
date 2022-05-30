package com.grupo2.anoLetivoWS.controller;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.service.AnoLetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anoLetivo")
public class AnoLetivoController
{
    @Autowired
    private AnoLetivoService service;

    @PostMapping("/criar")
    public ResponseEntity<AnoLetivoDTO> createAnoLetivo(@RequestBody AnoLetivoDTO anoLetivoDTO)
    {
        try
        {
            AnoLetivoDTO dto = service.createAnoLetivo(anoLetivoDTO);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch (ErroGeralException e)
        {
            throw new ErroGeralException(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listAll()
    {
        List<AnoLetivoDTO> lista = service.getAll();

        if (lista.isEmpty())
        {
            return new ResponseEntity<>("Nao existem anos letivos.",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

}
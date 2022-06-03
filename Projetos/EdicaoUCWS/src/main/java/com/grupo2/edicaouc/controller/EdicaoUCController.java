package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.service.EdicaoUCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/edicaoUC")
public class EdicaoUCController
{
    @Autowired
    private EdicaoUCService service;

    @PostMapping("/criar")
    public ResponseEntity<Object> createEdicao(@RequestBody EdicaoUCDTO edicaoUCDTO)
    {
        try
        {
            EdicaoUCDTO edicaoUC = service.createEdicaoUC(edicaoUCDTO.getUcCode(), edicaoUCDTO.getAnoLetivoCode());
            return new ResponseEntity<>(edicaoUC, HttpStatus.CREATED);

        } catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        } catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        }
    }

    @GetMapping("/listar/{UCCode}")
    public ResponseEntity<List<EdicaoUCDTO>> listEdicaoByUCCode(@PathVariable(name = "UCCode") String UCCode)
    {
        List<EdicaoUCDTO> opEdicaoUC = service.findAllEdicaoByUCCode(UCCode);

        if (opEdicaoUC.isEmpty())
        {
            throw new ListaVaziaException("Nao existem edicoes UC com o codigo "+UCCode);
        }

        return new ResponseEntity<>(opEdicaoUC, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(name = "id") Long id)
    {
        Optional<EdicaoUCDTO> dto = service.findById(id);

        if (dto.isPresent())
        {
            return new ResponseEntity<>(dto,HttpStatus.OK);
        }

        throw new OptionalVazioException("Nao encontrou edicao UC com id "+id);
    }
}



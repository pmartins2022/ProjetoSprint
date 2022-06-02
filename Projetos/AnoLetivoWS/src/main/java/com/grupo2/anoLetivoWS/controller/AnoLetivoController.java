package com.grupo2.anoLetivoWS.controller;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.exception.ListaVaziaException;
import com.grupo2.anoLetivoWS.exception.OptionalVazioException;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.service.AnoLetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe REST Controller de ano letivo. Possui endpoints para createAnoLetivo e listAnoLetivo.
 */
@RestController
@RequestMapping("/anoLetivo")
public class AnoLetivoController
{

    /**
     * O serviço a ser utilizado por este REST Controller.
     */
    @Autowired
    private AnoLetivoService service;

    /**
     * Endpoint que possibilita a criaçao de um ano letivo.
     *
     * @param anoLetivoDTO um objeto com os dados do ano letivo
     * @return um ano letivo, ou um erro se os dados estiverem invalidos.
     */
    @PostMapping("/criar")
    public ResponseEntity<AnoLetivoDTO> createAndSaveAnoLetivo(@RequestBody AnoLetivoDTO anoLetivoDTO)
    {
        try
        {
            AnoLetivoDTO dto = service.createAndSaveAnoLetivo(anoLetivoDTO);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        } catch (ErroGeralException e)
        {
            throw new ErroGeralException(e.getMessage());
        }
    }

    /**
     * Endpoint que possibilita a listagem dos anos letivos existentes no serviço.
     *
     * @return a lista atual de anos letivos, ou um erro se nao existir nenhum.
     */
    @GetMapping("/listar")
    public ResponseEntity<Object> listAllAnoLetivo()
    {
        List<AnoLetivoDTO> lista = service.findAll();

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Anos Letivos");
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findBySigla(@PathVariable("id") String id)
    {
        Optional<AnoLetivoDTO> optionalAnoLetivoDTO = service.findByID(id);

        if (optionalAnoLetivoDTO.isEmpty())
        {
            throw new OptionalVazioException("Não existe Ano Letivo com esse ID: " + id);
        }
        return new ResponseEntity<>(optionalAnoLetivoDTO.get(), HttpStatus.OK);
        }
    }
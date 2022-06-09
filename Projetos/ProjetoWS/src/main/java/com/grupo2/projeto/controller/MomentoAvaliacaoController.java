package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.exception.ListaVaziaException;
import com.grupo2.projeto.service.MomentoAvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/momentoAvaliacao")
public class MomentoAvaliacaoController {
    @Autowired
    private MomentoAvaliacaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<MomentoAvaliacaoDTO> findById(@PathVariable Long id)
    {
        Optional<MomentoAvaliacaoDTO> optionalMomentoAvaliacaoDTO = service.findById(id); //TODO

        if (optionalMomentoAvaliacaoDTO.isPresent())
        {
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = optionalMomentoAvaliacaoDTO.get();
            return new ResponseEntity<>(momentoAvaliacaoDTO, HttpStatus.OK);
        } else
        {
            throw new ErroGeralException("Nao existe nenhum Momento de Avaliação com esse ID");
        }
    }
    @GetMapping("/listar")
    public ResponseEntity<Object> listAllMomentoAvaliacao()
    {
        List<MomentoAvaliacaoDTO> lista = service.findAll();

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Momentos de Avaliação");
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    @PostMapping("/criar")
    public ResponseEntity<MomentoAvaliacaoDTO> createMomentoAvaliacao(@RequestBody MomentoAvaliacaoDTO momentoAvaliacaoDTO)
    {
        MomentoAvaliacaoDTO momentoAvaliacaoDTOSaved = service.createAndSave(momentoAvaliacaoDTO);

        return new ResponseEntity<>(momentoAvaliacaoDTOSaved, HttpStatus.CREATED);
    }

}

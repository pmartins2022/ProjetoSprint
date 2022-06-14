package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.service.AvaliacaoService;
import com.grupo2.projeto.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/conteudo")
public class ConteudoController
{
    @Autowired
    private ConteudoService service;

    @PostMapping("/criar")
    public ResponseEntity<ConteudoDTO> createConteudo(@RequestBody ConteudoDTO conteudoDTO)
    {
        ConteudoDTO conteudoDTOSaved = service.createAndSave(conteudoDTO);

        return new ResponseEntity<>(conteudoDTOSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoDTO> findById(@PathVariable Long id)
    {
        Optional<ConteudoDTO> optionalConteudoDTO = service.findById(id);

        if (optionalConteudoDTO.isPresent())
        {
            ConteudoDTO conteudoDTO = optionalConteudoDTO.get();
            return new ResponseEntity<>(conteudoDTO, HttpStatus.OK);
        } else
        {
            throw new ErroGeralException("Nao existe conteudo com esse ID");
        }
    }
}

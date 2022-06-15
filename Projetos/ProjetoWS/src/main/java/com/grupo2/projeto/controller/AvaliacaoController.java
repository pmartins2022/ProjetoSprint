package com.grupo2.projeto.controller;


import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController
{
    @Autowired
    private AvaliacaoService service;

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/criar")
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO)
    {
        AvaliacaoDTO avaliacaoDTOSaved = service.createAndSave(avaliacaoDTO);

        return new ResponseEntity<>(avaliacaoDTOSaved, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long id)
    {
        Optional<AvaliacaoDTO> optionalAvaliacaoDTO = service.findById(id);

        if (optionalAvaliacaoDTO.isPresent())
        {
            AvaliacaoDTO avaliacaoDTO = optionalAvaliacaoDTO.get();
            return new ResponseEntity<>(avaliacaoDTO, HttpStatus.OK);
        } else
        {
            throw new ErroGeralException("Nao existe nenhuma avaliacao com esse ID");
        }
    }
}

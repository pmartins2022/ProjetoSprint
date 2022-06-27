package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.EdicaoMomentoAvaliacaoDTO;
import com.grupo2.edicaouc.dto.MomentoAvaliacaoDTO;
import com.grupo2.edicaouc.service.MomentoAvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/momento")
public class MomentoAvaliacaoController
{
    /**
     * Objeto da classe MomentoAvaliacaoService a ser utilizador pelo MomentoAvaliacaoController
     */
    @Autowired
    private MomentoAvaliacaoService service;

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/criar")
    public ResponseEntity<Object> criarMomento(@RequestBody MomentoAvaliacaoDTO dto)
    {
        MomentoAvaliacaoDTO save = service.createAndSaveMomentoAvaliacao(dto);

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PostMapping("/criar/edicaoMomento")
    public ResponseEntity<Object> criarEdicaoMomento(@RequestBody EdicaoMomentoAvaliacaoDTO dto)
    {
        EdicaoMomentoAvaliacaoDTO save = service.createAndSaveEdicaoMomentoAvaliacao(dto);

        return new ResponseEntity<>(save,HttpStatus.CREATED);
    }
}
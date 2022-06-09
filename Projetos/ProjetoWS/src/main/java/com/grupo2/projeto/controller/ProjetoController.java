package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
/**
 * Classe REST Controller de projeto. Possui endpoints para createProjeto e findById.
 */
@RestController
@RequestMapping("/projeto")
public class ProjetoController
{
    /**
     * O serviço a ser utilizado por este REST Controller.
     */
    @Autowired
    private ProjetoService service;

    /**
     * Endpoint que possibilita encontrar o projeto por id existente no serviço.
     * @param id um objeto com os dados do projeto
     * @return um projeto, ou um erro se os dados estiverem invalidos.
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCENTE','ROLE_ALUNO')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> findById(@PathVariable Long id)
    {
        Optional<ProjetoDTO> optionalProjetoDTO = service.findById(id);

        if (optionalProjetoDTO.isPresent())
        {
            ProjetoDTO projetoDTO = optionalProjetoDTO.get();
            return new ResponseEntity<>(projetoDTO, HttpStatus.OK);
        } else
        {
            throw new ErroGeralException("Nao existe nenhum projeto com esse ID");
        }
    }

    /**
     * Endpoint que possibilita a criaçao de um projeto.
     * @param projetoDTO um objeto com os dados do projeto
     * @return um projeto, ou um erro se os dados estiverem invalidos.
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCENTE')")
    @PostMapping("/criar")
    public ResponseEntity<ProjetoDTO> createProjeto(@RequestBody ProjetoDTO projetoDTO)
    {
        ProjetoDTO projetoDTOSaved = service.createAndSave(projetoDTO);

        return new ResponseEntity<>(projetoDTOSaved, HttpStatus.CREATED);
    }
}

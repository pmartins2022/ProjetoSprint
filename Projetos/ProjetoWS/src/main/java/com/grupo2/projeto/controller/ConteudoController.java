package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conteudo")
public class ConteudoController
{
    @Autowired
    private ConteudoService service;

    @PreAuthorize("hasAuthority('ROLE_ALUNO')")
    @PostMapping("/criar")
    public ResponseEntity<ConteudoDTO> createConteudo(@RequestBody ConteudoDTO conteudoDTO)
    {
        ConteudoDTO conteudoDTOSaved = service.createAndSave(conteudoDTO);

        return new ResponseEntity<>(conteudoDTOSaved, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
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

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/aceitarConteudo/{idConteudo}")
    public ResponseEntity<Object> acceptConteudo(@PathVariable("idConteudo") Long idConteudo)
    {
        try
        {
            ConteudoDTO conteudoUpdate = service.acceptConteudo(idConteudo);
            return new ResponseEntity<>(conteudoUpdate, HttpStatus.OK);
        } catch (OptionalVazioException e)
        {
            throw e;
        } catch (ValidacaoInvalidaException e)
        {
            throw e;
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/rejeitarConteudo/{id}")
    public ResponseEntity<ConteudoDTO> rejectConteudo(@PathVariable("idConteudo") Long idConteudo)
    {
        try
        {
            ConteudoDTO dto = service.rejeitarConteudo(idConteudo);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (AtualizacaoInvalidaException e)
        {
            throw e;
        } catch (IdInvalidoException e)
        {
            throw e;
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/projetoID/{id}")
    public ResponseEntity<List<ConteudoDTO>> findAllByIdProjeto(@PathVariable("id") Long id)
    {

        List<ConteudoDTO> dtoList = service.findAllByIdProjeto(id);
        if (dtoList.isEmpty())
        {
            throw new ListaVaziaException("Não existem conteúdos para esse id de projeto " + id);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}

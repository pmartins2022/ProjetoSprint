package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
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
    public ResponseEntity<Object> createConteudo(@RequestBody ConteudoDTO conteudoDTO)
    {
        try
        {
            service.createAndSave(conteudoDTO);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }


    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<ConteudoDTO> findById(@PathVariable Long id)
    {
        try
        {
            ConteudoDTO conteudoDTO = service.findById(id);
            return new ResponseEntity<>(conteudoDTO, HttpStatus.OK);
        } catch (OptionalVazioException e)
        {
            throw e;
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/aceitarConteudo/{idConteudo}")
    public ResponseEntity<Object> acceptConteudo(@PathVariable("idConteudo") Long idConteudo)
    {
        try
        {
            service.acceptConteudo(idConteudo);
            return ResponseEntity.ok(null);
        } catch (OptionalVazioException | ValidacaoInvalidaException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw new ErroGeralException("Erro ao aceitar conteudo: "+e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/rejeitarConteudo/{id}")
    public ResponseEntity<?> rejectConteudo(@PathVariable("idConteudo") Long idConteudo)
    {
        try
        {
            service.rejeitarConteudo(idConteudo);
            return ResponseEntity.ok(null);
        } catch (AtualizacaoInvalidaException | IdInvalidoException e)
        {
            throw e;
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/projetoID/{id}")
    public ResponseEntity<List<ConteudoDTO>> findAllByIdProjeto(@PathVariable("id") Long id)
    {

        try
        {
            List<ConteudoDTO> dtoList = service.findAllByIdProjeto(id);
            if (dtoList.isEmpty())
            {
                throw new ListaVaziaException("Não existem conteúdos para esse id de projeto " + id);
            }

            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new ErroGeralException("Erro geral na DB a tentar encontrar by ID Projeto: "+e.getMessage());
        }
    }
}

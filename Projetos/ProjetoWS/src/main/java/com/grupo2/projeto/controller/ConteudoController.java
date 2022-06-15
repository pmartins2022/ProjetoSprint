package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.dto.UtilizadorAuthDTO;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.security.LoginContext;
import com.grupo2.projeto.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/conteudo")
public class ConteudoController
{
    @Autowired
    private ConteudoService service;

   // @PreAuthorize("hasAuthority('ROLE_ALUNO')")
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
    @PostMapping("/aceitarConteudo/{idConteudo}")
    public ResponseEntity<Object> acceptConteudo(@PathVariable("idConteudo") Long idConteudo)
    {
        UtilizadorAuthDTO docente = LoginContext.getCurrent();

        try
        {
            ConteudoDTO conteudoUpdate = service.acceptConteudo(docente.getId(), idConteudo);
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
    @GetMapping("/rejeitarConteudo/{id}")
    public ResponseEntity<ConteudoDTO> rejectConteudo(@PathVariable("idConteudo") Long idConteudo)
    {
        try
        {
            Optional<ConteudoDTO> dto = service.rejeitarConteudo(idConteudo);
            if (dto.isEmpty())
            {
                throw new IdInvalidoException("O id da proposta " + idConteudo + " e invalido.");
            }
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        }
        catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }
    }
}

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
    /*@Autowired
    private ConteudoService service;

   /* @PreAuthorize("hasAuthority('ROLE_ALUNO')")
    @PostMapping("/criar")
    public ResponseEntity<Object> createConteudo(@RequestBody ConteudoDTO conteudoDTO)
    {
        try
        {
            int x = service.createAndSave(conteudoDTO);

            if (x == 0)
            {
                throw new OptionalVazioException("NAO FOI POSSIVEL GUARDAR NA DB");
            }

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
            int conteudoUpdate = service.acceptConteudo(idConteudo);

            if (conteudoUpdate == 0)
            {
                throw new OptionalVazioException("NAO FOI POSSIVEL ATUALIZAR NA DB");
            }

            return ResponseEntity.ok(null);
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
    public ResponseEntity<?> rejectConteudo(@PathVariable("idConteudo") Long idConteudo)
    {
        try
        {
            int dto = service.rejeitarConteudo(idConteudo);
            if (dto == 0)
            {
                throw new OptionalVazioException("NAO FOI POSSIBLE ALTERAR NA DB, ja foste");
            }
            return ResponseEntity.ok(null);
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
    }*/
}

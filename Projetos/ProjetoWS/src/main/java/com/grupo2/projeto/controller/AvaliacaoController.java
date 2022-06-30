package com.grupo2.projeto.controller;


import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.AvaliacaoNotaDTO;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.security.LoginContext;
import com.grupo2.projeto.service.AvaliacaoService;
import com.grupo2.projeto.service.MomentoAvaliacaoNotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController
{
    @Autowired
    private AvaliacaoService service;

    @Autowired
    private MomentoAvaliacaoNotaService momentoAvaliacaoNotaService;

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/criar")
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO)
    {
        try
        {
            service.createAndSave(avaliacaoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (OptionalVazioException e)
        {
            throw e;
        }
        catch (ValidacaoInvalidaException e)
        {
            throw e;
        }
        catch (IdInvalidoException e)
        {
            throw e;
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long id)
    {
        AvaliacaoDTO avaliacaoDTO = null;
        try
        {
            avaliacaoDTO = service.findById(id);
            return new ResponseEntity<>(avaliacaoDTO, HttpStatus.OK);
        } catch (Exception e)
        {
            throw new OptionalVazioException("Nao existe nenhuma avaliacao com esse ID");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listAllMomentoAvaliacao()
    {
        List<AvaliacaoDTO> lista = service.findAll();

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Momentos de Avaliação");
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/listarAvaliacao/{presidenteID}")
    public ResponseEntity<Object> listAvaliacaoByPresidenteID(@PathVariable("presidenteID") Long presidenteID)
    {
        List<AvaliacaoDTO> lista = service.findAllByPresidente(presidenteID);

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Avaliações");
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/avaliar")
    public ResponseEntity<Object> createAvaliacaoNota(@RequestBody AvaliacaoNotaDTO dto)
    {
        try
        {
            momentoAvaliacaoNotaService.createAvaliacaoNota(dto);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/{rucID}")
    public ResponseEntity<List<AvaliacaoNotaDTO>> findAllByEstadoAndRucID(@RequestParam("estado") String estado)
    {
        try
        {
            List<AvaliacaoNotaDTO> list = momentoAvaliacaoNotaService.findAllByEstadoAndRucID(LoginContext.getCurrent().getId(), estado);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        catch(IllegalArgumentException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PutMapping("/reverAvaliacao/{id}")
    public ResponseEntity<Object> reviewAvaliacao(@PathVariable("id") Long id, @RequestParam("avaliacao") String avaliacao)
    {
        try
        {
            momentoAvaliacaoNotaService.reviewAvaliacao(id, avaliacao);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IllegalArgumentException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch(OptionalVazioException e)
        {
            throw e;
        }
        catch(ValidacaoInvalidaException e)
        {
            throw e;
        }
    }
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/editarAvaliacao/{id}")
    public ResponseEntity<Object> editarAvaliacaoNota(@PathVariable("id") Long id, @RequestParam("nota") int nota,@RequestParam("justificacao") String justificacao)
    {
        try{
            momentoAvaliacaoNotaService.editarAvaliacaoNota(id,nota,justificacao);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IllegalArgumentException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch(OptionalVazioException e)
        {
            throw e;
        }
        catch(ValidacaoInvalidaException e)
        {
            throw e;
        }
    }


}

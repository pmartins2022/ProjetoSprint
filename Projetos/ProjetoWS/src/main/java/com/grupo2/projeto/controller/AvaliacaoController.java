package com.grupo2.projeto.controller;


import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.AvaliacaoNotaDTO;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.security.LoginContext;
import com.grupo2.projeto.service.AvaliacaoService;
import com.grupo2.projeto.service.AvaliacaoNotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController
{
    @Autowired
    private AvaliacaoService service;

    @Autowired
    private AvaliacaoNotaService avaliacaoNotaService;

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/criar")
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO)
    {
        try
        {
            service.createAndSave(avaliacaoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (OptionalVazioException | ValidacaoInvalidaException | IdInvalidoException e)
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
        } catch (OptionalVazioException e)
        {
            throw e;
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listAllAvaliacao()
    {
        try
        {
            List<AvaliacaoDTO> lista = service.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
        catch (ListaVaziaException e)
        {
            throw e;
        }
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
            avaliacaoNotaService.createAvaliacaoNota(dto);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/{rucID}")
    public ResponseEntity<List<AvaliacaoNotaDTO>> findAllByEstadoAndRucID(@PathVariable("rucID") Long rucID,  @RequestParam("estado") String estado)
    {
        try
        {
            List<AvaliacaoNotaDTO> list = avaliacaoNotaService.findAllByEstadoAndRucID(LoginContext.getCurrent().getId(), estado);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        catch(IllegalArgumentException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PutMapping("/reverAvaliacaoNota/{id}")
    public ResponseEntity<Object> reviewAvaliacaoNota(@PathVariable("id") Long id, @RequestParam("avaliacao") String avaliacao)
    {
        try
        {
            avaliacaoNotaService.reviewAvaliacaoNota(id, avaliacao);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IllegalArgumentException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch(OptionalVazioException | ValidacaoInvalidaException e)
        {
            throw e;
        }
    }
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/editarAvaliacao/{id}")
    public ResponseEntity<Object> editarAvaliacaoNota(@PathVariable("id") Long id, @RequestParam("nota") Long nota,@RequestParam("justificacao") String justificacao)
    {
        try{
            avaliacaoNotaService.editarAvaliacaoNota(id,nota,justificacao);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(IllegalArgumentException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch(OptionalVazioException | ValidacaoInvalidaException e)
        {
            throw e;
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/nota/{id}")
    public ResponseEntity<AvaliacaoNotaDTO> findAvaliacaoNotaByAvaliacaoID(@PathVariable("id") Long idAvaliacao)
    {
        try
        {
            AvaliacaoNotaDTO dto = avaliacaoNotaService.findAvaliacaoNotaByAvaliacaoID(idAvaliacao);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/listEditable")
    public ResponseEntity<List<AvaliacaoDTO>> findAllEditableAvaliacao(@PathVariable("id") Long idPresidente)
    {
        try
        {
            List<AvaliacaoDTO> list = service.findAllEditableAvaliacao(idPresidente);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}

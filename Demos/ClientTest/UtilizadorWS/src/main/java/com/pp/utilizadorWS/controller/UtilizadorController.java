package com.pp.utilizadorWS.controller;

import com.pp.utilizadorWS.dto.UtilizadorAuthDTO;
import com.pp.utilizadorWS.dto.UtilizadorDTO;
import com.pp.utilizadorWS.model.TipoUtilizador;
import com.pp.utilizadorWS.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/utilizador")
public class UtilizadorController
{
    @Autowired
    private UtilizadorService utilizadorService;

    @PostMapping("/registar")
    public ResponseEntity<?> registar(@RequestBody UtilizadorDTO utilizadorDTO)
    {
        try
        {
            UtilizadorDTO dto = utilizadorService.registar(utilizadorDTO);

            return ResponseEntity.ok(dto);
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find")
    public ResponseEntity<UtilizadorAuthDTO> findByUsername(@RequestParam(name = "username") String username)
    {
        Optional<UtilizadorAuthDTO> utilizadorDTO = utilizadorService.findByUsername(username);

        if (utilizadorDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(utilizadorDTO.get());
    }

    @GetMapping("/test")
    public String test()
    {
        return "Method test";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdmin()
    {
        return "eu sou admin";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_DOCENTE','ROLE_ADMIN')")
    @GetMapping("/docente")
    public String getDocente()
    {
        return "eu sou docente";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ALUNO','ROLE_ADMIN')")
    @GetMapping("/aluno")
    public String getAluno()
    {
        return "eu sou aluno";
    }
}
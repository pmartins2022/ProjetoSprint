package com.grupo2.projeto.controller;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.filter.ProjetoFilterBody;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.service.ProjetoFilterService;
import com.grupo2.projeto.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
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

    @Autowired
    private ProjetoFilterService filterService;

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
    public ResponseEntity<Object> createProjeto(@RequestBody ProjetoDTO projetoDTO)
    {
        try
        {
            service.createAndSave(projetoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/orientadorID/{id}")
    public ResponseEntity<List<ProjetoDTO>> findAllByOrientadorID(@PathVariable Long id)
    {
        List<ProjetoDTO> list = null;
        try
        {
            list = service.findAllByOrientadorId(id);
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e)
        {
            throw new ErroGeralException("Nao existe nenhum projeto com esse orientadorID");
        }

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/filter")
    public ResponseEntity<Object> projetoFilter(@RequestBody ProjetoFilterBody body)
    {
        System.out.println("Recebeu:");
        System.out.println(body.toString());

        try
        {
            var list = filterService.filtrarProjetos(body);

            System.out.println("Query: "+filterService.getLastQuery());

            if (list.size() == 0)
            {
                throw new OptionalVazioException("Nao existem projetos para o filtro especificado");
            }

            return ResponseEntity.ok(list);
        }
        catch (OptionalVazioException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Problema a fazer o parse: "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}

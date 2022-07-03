package com.grupo2.utilizadores.controller;

import com.grupo2.utilizadores.dto.UtilizadorAuthDTO;
import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.exception.ListaVaziaException;
import com.grupo2.utilizadores.exception.OptionalVazioException;
import com.grupo2.utilizadores.security.LoginContext;
import com.grupo2.utilizadores.security.SecurityUtils;
import com.grupo2.utilizadores.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Classe REST Controller de utilizador. Possui endpoints para findByID.
 */
@RestController
@RequestMapping("/utilizador")
public class UtilizadorController
{
    /**
     * O serviço a ser utilizado por este REST Controller.
     */
    @Autowired
    private UtilizadorService service;

    /**
     * Registar um novo utilizador na BD
     * @param utilizadorDTO informacao do novo utilizador
     * @param req informacao do request
     * @return informacao do utilizador registado, ou um erro
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/registar")
    public ResponseEntity<?> registar(@RequestBody UtilizadorDTO utilizadorDTO, HttpServletRequest req)
    {
        LoginContext.setToken(req.getHeader(SecurityUtils.AUTH));

        try
        {
            UtilizadorDTO dto = service.registar(utilizadorDTO);

            return ResponseEntity.ok(dto);
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint que possibilita encontrar o utilizador por id existente no serviço.
     * @param id um objeto com os dados do utilizador
     * @return utilizador, ou um erro se os dados estiverem invalidos.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByID(@PathVariable("id") Long id)
    {
        Optional<UtilizadorDTO> optionalUtilizadorDTO = service.findByID(id);

        if (optionalUtilizadorDTO.isEmpty())
        {
            throw new OptionalVazioException("Não existe Utilizador com esse ID");
        }
        return new ResponseEntity<>(optionalUtilizadorDTO, HttpStatus.OK);
    }

    /**
     * Tentar encontrar um utilizador, fornecendo o seu username
     * @param username o username a procurar
     * @return o utilizador que encontrou ou um erro se o utilizador com esse username nao existe
     */
    @GetMapping("/find")
    public ResponseEntity<UtilizadorAuthDTO> findByUsername(@RequestParam(name = "username") String username)
    {
        Optional<UtilizadorAuthDTO> utilizadorDTO = service.findByUsername(username);

        if (utilizadorDTO.isEmpty())
        {
            throw new OptionalVazioException("Não existe Utilizador com essas credencias.");
        }

        return ResponseEntity.ok(utilizadorDTO.get());
    }

    /**
     * Verificar se um utilizador e de um certo tipo (admin,docente ou aluno)
     * @param role o tipo de utilizador a procurar
     * @param id o id do utilizaodr
     * @return se o utilizador e desse tipo ou um erro se o ID nao existe
     */
    @GetMapping("/{role}/{id}")
    public ResponseEntity<Boolean> isRole(@PathVariable("role") String role, @PathVariable("id") Long id)
    {
        try
        {
            Boolean isRole = service.isRole(role, id);
            return new ResponseEntity<>(isRole, HttpStatus.OK);
        } catch (OptionalVazioException e)
        {
            throw new OptionalVazioException("Utilizador com esse id "+id+" não existe");
        }
    }

    /**
     * Endpoint que possibilita listar todos os utilizadores da BD
     * @return a lista de utilizadores ou erro se nao existirem utilizadores
     */
    @GetMapping("/listar")
    public ResponseEntity<Object> listAll()
    {
        List<UtilizadorDTO> lista = service.findAll();

        if (lista.isEmpty()) throw new ListaVaziaException("Nao ha utilizadores");

        return ResponseEntity.ok(lista);
    }

    /**
     * Endpoint que possibilita listar apenas os docentes da BD
     * @return a lista de docentes ou um erro se nao existirem docentes.
     */
    @GetMapping("/docentes")
    public ResponseEntity<Object> findAllDocentes()
    {
        List<UtilizadorDTO> lista = service.findAllDocentes();

        if (lista.isEmpty()) throw new ListaVaziaException("Nao ha docentes");

        return ResponseEntity.ok(lista);
    }

}

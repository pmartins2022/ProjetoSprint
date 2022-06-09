package com.grupo2.edicaouc.controller;

import com.grupo2.edicaouc.dto.EdicaoUCAlunoDTO;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.UtilizadorDTO;
import com.grupo2.edicaouc.exception.BaseDadosException;
import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.exception.ListaVaziaException;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.security.LoginContext;
import com.grupo2.edicaouc.service.EdicaoUCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Class REST Controller de Edição de Unidade Curricular. Possui endpoints para createEdicaoUC, findAllEdicaoByUCCode e findById.
 */
@RestController
@RequestMapping("edicaoUC")
public class EdicaoUCController
{
    /**
     * Objeto da class EdicaoUCService a ser utilizado pelo REST Controller.
     */
    @Autowired
    private EdicaoUCService service;

    /**
     * Endpoint que permite ccriar e guardar uma Edição de Unidade Curricular.
     * @param edicaoUCDTO objeto DTO com dados da Edicao de Unidade Curricular a criar.
     * @return Edicao de Unidade Curricular OU excecao da classe BaseDadosException e OptionalVazioException caso os dados não sejam válidos
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/criar")
    public ResponseEntity<Object> createEdicao(@RequestBody EdicaoUCDTO edicaoUCDTO)
    {
        try
        {
            EdicaoUCDTO edicaoUC = service.createEdicaoUC(edicaoUCDTO);
            return new ResponseEntity<>(edicaoUC, HttpStatus.CREATED);

        }
        catch (BaseDadosException e)
        {
            throw new BaseDadosException(e.getMessage());
        }
        catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        }
    }

    /**
     * Endpoint que permite listar as Edições de Unidade Curricular com um determinado UCCode (Código de Unidade Curriculuar) existentes no service.
     * @param UCCode variavel com o valor de código da Unidade Curricular.
     * @return Lista todas as Edições com código de Unidade Curricular pretendida.
     */
    @GetMapping("/listar/{UCCode}")
    public ResponseEntity<List<EdicaoUCDTO>> listEdicaoByUCCode(@PathVariable(name = "UCCode") String UCCode)
    {
        List<EdicaoUCDTO> opEdicaoUC = service.findAllEdicaoByUCCode(UCCode);

        if (opEdicaoUC.isEmpty())
        {
            throw new ListaVaziaException("Nao existem edicoes UC com o codigo "+UCCode);
        }

        return new ResponseEntity<>(opEdicaoUC, HttpStatus.OK);
    }

    /**
     * Endpoint que permite obter a lista total de Edições de Unidade Curricular existentes.
     * @return Lista todas as Edições de Unidade Curricular ou erro.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<EdicaoUCDTO>> listAll()
    {
        List<EdicaoUCDTO> opEdicaoUC = service.findAllEdicaoUC();

        if (opEdicaoUC.isEmpty())
        {
            throw new ListaVaziaException("Nao existem edicoes UC");
        }

        return new ResponseEntity<>(opEdicaoUC, HttpStatus.OK);
    }

    /**
     * Endpoint que possibilita encontrar o Edição de Unidade Curricular por id existente no service.
     * @param id objeto com dados da Edição de Unidade Curricular
     * @return Edição de Unidade Curricular OU exceção OptionalVazioException caso a Edição com o ID inserido não exista.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(name = "id") Long id)
    {
        Optional<EdicaoUCDTO> dto = service.findById(id);

        if (dto.isPresent())
        {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        throw new OptionalVazioException("Nao encontrou edicao UC com id " + id);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/inscrever/{edicaoUCID}")
    public ResponseEntity<Object> addAlunoEdicaoUC(@PathVariable("edicaoUCID") Long edicaoUCID, @RequestParam("alunoID") Long alunoID)
    {
        UtilizadorDTO dto = LoginContext.getCurrent();

        EdicaoUCAlunoDTO edicaoUCAlunoDTO = service.addAlunoEdicaoUC(dto,edicaoUCID, alunoID);

        return new ResponseEntity<>(edicaoUCAlunoDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/ativarEdicao/{edicaoUCID}")
    public ResponseEntity<Object> ativarEdicao(@PathVariable("edicaoUCID") Long edicaoUCID)
    {
        try
        {
            EdicaoUCDTO dto = service.activarEdicao(edicaoUCID);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        }
        catch (ErroGeralException e)
        {
            throw new ErroGeralException(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/desativarEdicao/{edicaoUCID}")
    public ResponseEntity<Object> desativarEdicao(@PathVariable("edicaoUCID") Long edicaoUCID)
    {
        try
        {
            EdicaoUCDTO dto = service.desativarEdicao(edicaoUCID);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        }
        catch (ErroGeralException e)
        {
            throw new ErroGeralException(e.getMessage());
        }

    }
}




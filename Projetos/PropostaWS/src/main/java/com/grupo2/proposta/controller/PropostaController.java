package com.grupo2.proposta.controller;


import com.grupo2.proposta.dto.ProjetoDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.ListaVaziaException;
import com.grupo2.proposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Classe REST Controller de Proposta. Possui endpoints para createProposta, listbyIdUtilizador, listbyTitulo,
 * listbyNif, aprovarProposta, rejeitarProposta
 */
@RestController
@RequestMapping("/proposta")
public class PropostaController
{
    @Autowired
    private PropostaService service;

    /**
     * Endpoint que possibilita criar uma proposta.
     * @param dto um objeto com os dados da proposta
     * @return proposta, ou um erro se os dados estiverem invalidos.
     */
    @PostMapping("/create")
    public ResponseEntity<PropostaDTO> createProposta(@RequestBody PropostaDTO dto)
    {
        try
        {
            PropostaDTO proposta = service.createProposta(dto);

            return new ResponseEntity<>(proposta, HttpStatus.CREATED);
        } catch (BaseDadosException e)
        {
            throw new BaseDadosException(e.getMessage());
        }
    }

/**
     * Endpoint que possibilita listar as propostas de um utilizador.
     * @param id o id do utilizador
     * @return proposta, ou um erro se os dados estiverem invalidos.
     */
    @GetMapping("/listarPorId")
    public ResponseEntity<Object> listbyIdUtilizador(Long id)
    {
        List<PropostaDTO> lista = service.findByIdUtilizador(id);

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Propostas");
        }

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    /**
     * Endpoint que possibilita listar as propostas de uma proposta por titulo.
     * @param titulo o titulo da proposta
     * @return proposta, ou um erro se os dados estiverem invalidos.
     */
    @GetMapping("/listarPorTitulo")
    public ResponseEntity<Object> listbyTitulo(@RequestParam String titulo)
    {
        List<PropostaDTO> lista = service.findByTitulo(titulo);

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Propostas");
        }

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    /**
     * Endpoint que possibilita listar as propostas por nif de organizacao.
     * @param nif o nif da organizacao
     * @return proposta, ou um erro se os dados estiverem invalidos.
     */
    @GetMapping("/listarPorNif")
    public ResponseEntity<Object> listbyNif(Integer nif)
    {
        List<PropostaDTO> lista = service.findByNif(nif);

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Propostas com esse nif de organizacao");
        }

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    /**
     * Endpoint que possibilita rejeitar uma proposta.
     * @param id o id da proposta
     * @return proposta, ou um erro se os dados estiverem invalidos ou se a proposta ja tiver sido aprovada/rejeitada.
     */
    @GetMapping("/rejeitar/{id}")
    public ResponseEntity<PropostaDTO> rejeitarProposta(@PathVariable(name = "id") Long id)
    {
        try
        {
            Optional<PropostaDTO> dto = service.rejeitarProposta(id);
            if (dto.isEmpty())
            {
                throw new IdInvalidoException("O id da proposta "+id+" e invalido.");
            }
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        }
        catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }
    }

    /**
     * Endpoint que possibilita aprovar uma proposta.
     * @param propostaID o id da proposta
     * @param orientadorID o id do orientador
     * @param alunoID o id do aluno
     * @return proposta, ou um erro se os dados estiverem invalidos ou se a proposta ja tiver sido aprovada/rejeitada.
     */
    @GetMapping("/aceitar/{id}")
    public ResponseEntity<Object> aceitarProposta(@PathVariable("id") Long propostaID,
                                                      @RequestParam("orientador") Long orientadorID, @RequestParam("aluno") Long alunoID)
    {
        try
        {
            ProjetoDTO projetoDTO = service.acceptProposta(propostaID, orientadorID, alunoID);
            return new ResponseEntity<>(projetoDTO, HttpStatus.OK);
        }
        catch (IdInvalidoException e)
        {
            throw new IdInvalidoException(e.getMessage());
        }
        catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }
    }
}
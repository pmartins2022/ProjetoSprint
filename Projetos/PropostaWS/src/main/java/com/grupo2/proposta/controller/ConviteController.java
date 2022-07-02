package com.grupo2.proposta.controller;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.ListaVaziaException;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.exception.ValidacaoInvalidaException;
import com.grupo2.proposta.security.LoginContext;
import com.grupo2.proposta.security.SecurityUtils;
import com.grupo2.proposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Classe REST Controller de Proposta. Possui endpoints para criarConvite, aceitarOrientacao, rejeitarOrientacao
 */
@RestController
@RequestMapping("/convite")
public class ConviteController
{
    /**
     * Objeto do tipo PropostaService a ser utilizador pelo ConviteController
     */
    @Autowired
    private PropostaService service;

    /**
     * Endpoint que criar um convite
     * @param convite objeto do tipo ConviteDTO
     * @return um objeto ou um erro se os dados estiverem invalidos.
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('ROLE_ALUNO')")
    @PostMapping("/create")
    public ResponseEntity<Object> criarConvite(@RequestBody ConviteDTO convite) throws Exception
    {
        try
        {
            ConviteDTO conviteDTO = service.createConvite(convite);
            return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
        } catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        } catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        } catch (IdInvalidoException e)
        {
            throw new IdInvalidoException(e.getMessage());
        }
    }

    /**
     * Endpoint que permite a um docenta aceitar uma orientacao
     * @param request
     * @return um objeto ou um erro se os dados estiverem invalidos.
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/aceitarOrientacao")
    public ResponseEntity<Object> aceitarOrientacao( @RequestBody ConviteDTO convite, HttpServletRequest request) throws Exception
    {
        String encoded =  request.getHeader(SecurityUtils.AUTH);
        ConviteDTO conviteDTO = service.acceptOrientacaoProposta(convite, encoded);
        return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint que permite a um docenta rejeitar uma orientacao
     * @param request
     * @return um objeto ou um erro se os dados estiverem invalidos.
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/rejeitarOrientacao")
    public ResponseEntity<Object> rejeitarOrientacao(@RequestBody ConviteDTO convite, HttpServletRequest request) throws Exception
    {
        String encoded =  request.getHeader(SecurityUtils.AUTH);
        ConviteDTO conviteDTO = service.rejectOrientacaoProposta(convite, encoded);
        return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint que permite a um docenta listar convites pendentes
     * @return lista de convites pendentes ou um erro se o lista estiver vazia.
     * @throws ListaVaziaException
     */
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/findAtivos")
    public ResponseEntity<Object> getConvitesPendentes()
    {
        List<ConviteDTO> list = service.findConvitesPendentes(LoginContext.getCurrent().getId());

        if (list.isEmpty())
        {
            throw new ListaVaziaException("Nao existem convites pendentes para este docente.");
        }

        return ResponseEntity.ok(list);
    }

    /**
     * Endpoint que permite a um docenta listar convites Aceites
     * @return lista de convites pendentes ou um erro se o lista estiver vazia.
     * @throws ListaVaziaException
     */
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/accepted")
    public ResponseEntity<List<ConviteDTO>> findAllConviteAccepted()
    {
        List<ConviteDTO> list = service.findAllConviteAccepted(LoginContext.getCurrent().getId());

        if (list.isEmpty())
        {
            throw new ListaVaziaException("Nao existem convites aceites para este docente.");
        }

        return ResponseEntity.ok(list);
    }
}

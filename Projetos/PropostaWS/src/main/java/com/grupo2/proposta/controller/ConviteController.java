package com.grupo2.proposta.controller;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.exception.ValidacaoInvalidaException;
import com.grupo2.proposta.security.SecurityUtils;
import com.grupo2.proposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * Classe REST Controller de Proposta. Possui endpoints para criarConvite, aceitarOrientacao, rejeitarOrientacao
 */
@RestController
@RequestMapping("/convite")
public class ConviteController
{
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
     * @param propostaID e o id da proposta
     * @param orientadorID e o id do orientador
     * @param request
     * @return um objeto ou um erro se os dados estiverem invalidos.
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/aceitarOrientacao/{id}")
    public ResponseEntity<Object> aceitarOrientacao( @PathVariable("id") Long propostaID,
                                                     @RequestParam("orientador") Long orientadorID, HttpServletRequest request) throws Exception
    {
        String encoded =  request.getHeader(SecurityUtils.AUTH);
        ConviteDTO conviteDTO = service.acceptOrientacaoProposta(propostaID, orientadorID, encoded);
        return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint que permite a um docenta rejeitar uma orientacao
     * @param propostaID e o id da proposta
     * @param orientadorID e o id do orientador
     * @param request
     * @return um objeto ou um erro se os dados estiverem invalidos.
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PostMapping("/rejeitarOrientacao/{id}")
    public ResponseEntity<Object> rejeitarOrientacao( @PathVariable("id") Long propostaID,
                                                      @RequestParam("orientador") Long orientadorID, HttpServletRequest request) throws Exception
    {
        String encoded =  request.getHeader(SecurityUtils.AUTH);
        ConviteDTO conviteDTO = service.rejectOrientacaoProposta(propostaID,orientadorID, encoded);
        return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
    }
}

package com.grupo2.proposta.controller;


import com.grupo2.proposta.dto.*;
import com.grupo2.proposta.exception.*;
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
    @PreAuthorize("hasAnyAuthority('ROLE_DOCENTE','ROLE_ALUNO')")
    @PostMapping("/create")
    public ResponseEntity<PropostaDTO> createProposta(@RequestBody PropostaDTO dto, HttpServletRequest request)
    {
        try
        {
            PropostaDTO proposta = service.createProposta(dto,request.getHeader("Authorization"));

            return new ResponseEntity<>(proposta, HttpStatus.CREATED);
        }
        catch(ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch (BaseDadosException e)
        {
            throw new BaseDadosException(e.getMessage());
        }
    }

/**
     * Endpoint que possibilita listar as propostas de um utilizador.
     * @param id o id do utilizador
     * @return proposta, ou um erro se os dados estiverem invalidos.
     */
    @PreAuthorize("hasAnyAuthority('ROLE_DOCENTE','ROLE_ALUNO')")
    @GetMapping("/listarPorId/{id}")
    public ResponseEntity<Object> listbyIdUtilizador(@PathVariable(name = "id") Long id)
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
    @PreAuthorize("hasAnyAuthority('ROLE_DOCENTE','ROLE_ALUNO')")
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
    @PreAuthorize("hasAnyAuthority('ROLE_DOCENTE','ROLE_ALUNO')")
    @GetMapping("/listarPorNif/{nif}")
    public ResponseEntity<Object> listbyNif(@PathVariable(name = "nif") Integer nif, HttpServletRequest request)
    {
        List<PropostaDTO> lista = service.findByNif(nif, request.getHeader(SecurityUtils.AUTH));

        if (lista.isEmpty())
        {
            throw new ListaVaziaException("Não existem Propostas com esse nif de organizacao");
        }

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    /**
     * Endpoint que possibilita rejeitar uma candidatura a proposta.
     * @param id o id da proposta
     * @return proposta, ou um erro se os dados estiverem invalidos ou se a proposta ja tiver sido aprovada/rejeitada.
     */
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/rejeitarCandidatura/{id}")
    public ResponseEntity<PropostaDTO> rejeitarCandidaturaProposta(@PathVariable(name = "id") Long id)
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

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @PatchMapping("/aceitarCandidaturaProposta/{idProposta}") //é patch??!!!!!
    public ResponseEntity<Object> acceptCandidaturaProposta(@PathVariable("idProposta") Long idProposta)
    {
        UtilizadorAuthDTO docente = LoginContext.getCurrent();

        PropostaDTO propostaUpdated = service.acceptCandidaturaProposta(docente.getId(), idProposta);
        return new ResponseEntity<>(propostaUpdated, HttpStatus.OK);
    }


//?????????????????????????????????????????????
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/aceitarProposta/{id}")
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

    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/aceitarOrientacao/{id}")
    public ResponseEntity<Object> aceitarOrientacao( @PathVariable("id") Long propostaID,
                                                    @RequestParam("orientador") Long orientadorID, HttpServletRequest request) throws Exception
    {
        String encoded =  request.getHeader(SecurityUtils.AUTH);
        ConviteDTO conviteDTO = service.acceptOrientacaoProposta(propostaID, orientadorID, encoded);
        return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('ROLE_DOCENTE')")
    @GetMapping("/rejeitarOrientacao/{id}")
    public ResponseEntity<Object> rejeitarOrientacao( @PathVariable("id") Long propostaID,
                                                     @RequestParam("orientador") Long orientadorID, HttpServletRequest request) throws Exception
    {
        String encoded =  request.getHeader(SecurityUtils.AUTH);
        ConviteDTO conviteDTO = service.rejectOrientacaoProposta(propostaID,orientadorID, encoded);
        return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAuthority('ROLE_ALUNO')")
    @GetMapping("/criarConvite")
    public ResponseEntity<Object> criarConvite(@RequestBody ConviteDTO convite) throws Exception
    {
        try
        {
            ConviteDTO conviteDTO = service.createConvite(convite);
            return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
        }
        catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        }
        catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch (IdInvalidoException e)
        {
            throw new IdInvalidoException(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ALUNO')")
    @PostMapping("/inscricao/{propostaID}")
    public ResponseEntity<Object> inscricaoProposta(@PathVariable(name = "propostaID") Long propostaID)
    {
        try
        {
            PropostaInscricaoDTO cand = service.inscricaoProposta(propostaID);
            return new ResponseEntity<>(cand,HttpStatus.CREATED);
        }
        catch (OptionalVazioException e)
        {
            throw new OptionalVazioException(e.getMessage());
        }
        catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }
    }
}
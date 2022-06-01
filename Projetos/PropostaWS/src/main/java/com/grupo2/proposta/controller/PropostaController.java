package com.grupo2.proposta.controller;

<<<<<<< HEAD
import com.grupo2.proposta.dto.OrganizacaoDTO;
=======
import com.grupo2.proposta.dto.ProjetoDTO;
>>>>>>> 8498491f3c0309626721882a21ce238b2f264e97
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
<<<<<<< HEAD
import com.grupo2.proposta.exception.ListaVaziaException;
=======
import com.grupo2.proposta.exception.ValidacaoInvalidaException;
>>>>>>> 8498491f3c0309626721882a21ce238b2f264e97
import com.grupo2.proposta.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.Optional;
>>>>>>> 8498491f3c0309626721882a21ce238b2f264e97

@RestController
@RequestMapping("/proposta")
public class PropostaController
{
    @Autowired
    private PropostaService service;

    @PostMapping("/create")
    public ResponseEntity<PropostaDTO> createProposta(@RequestBody PropostaDTO dto)
    {
        try
        {
            PropostaDTO proposta = service.createProposta(dto);

            return new ResponseEntity<>(proposta, HttpStatus.OK);
        } catch (BaseDadosException e)
        {
            throw new BaseDadosException(e.getMessage());
        }
    }

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

    @GetMapping("/aceitar/{id}")
    public ResponseEntity<Object> aceitarProposta(@PathVariable("id") Long propostaID,
                                                      @RequestParam("orientador") Long orientadorID, @RequestParam("aluno") Long alunoID)
    {

        try
        {
            ProjetoDTO projetoDTO = service.acceptProposta(propostaID, orientadorID, alunoID);
            return new ResponseEntity<>(projetoDTO, HttpStatus.OK);
        } catch (ValidacaoInvalidaException e)
        {
            throw new ValidacaoInvalidaException(e.getMessage());
        }
        catch (IdInvalidoException e)
        {
            throw new IdInvalidoException(e.getMessage());
        }
    }
}
package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.EdicaoUCDTO;
import com.grupo2.projeto.dto.PropostaDTO;
import com.grupo2.projeto.dto.UtilizadorDTO;
import com.grupo2.projeto.dto.mapper.AvaliacaoDTOMapper;
import com.grupo2.projeto.exception.ListaVaziaException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.model.*;
import com.grupo2.projeto.repository.*;
import com.grupo2.projeto.repository.rest.UtilizadorRestRepository;
import com.grupo2.projeto.security.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AvaliacaoService
{
    @Autowired
    private AvaliacaoDTOMapper mapper;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

    @Autowired
    private ConteudoJDBCRepository conteudoJDBCRepository;
    @Autowired
    private ProjetoJDBCRepository projetoJDBCRepository;
    @Autowired
    private AvaliacaoJDBCRepository avaliacaoJDBCRepository;
    @Autowired
    private EdicaoUCJDBCRepository edicaoUCJDBCRepository;
    @Autowired
    private PropostaJDBCRepository propostaJDBCRepository;
    @Autowired
    private AvaliacaoNotaJDBCRepository avaliacaoNotaJDBCRepository;


    public void createAndSave(AvaliacaoDTO avaliacaoDTO)
    {
        Conteudo cont = null;
        try
        {
            cont = conteudoJDBCRepository.findById(avaliacaoDTO.getConteudo());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Nao existe conteudo com esse id");
        }

        if (!cont.getIdProjeto().equals(avaliacaoDTO.getIdProjeto()))
        {
            throw new ValidacaoInvalidaException("O ID DO PROJETO DA AVALIACAO NAO E IGUAL AO ID DO PROJETO DO CONTEUDO");
        }

        if (cont.getEstadoConteudo() != EstadoConteudo.APROVADO)
        {
            throw new ValidacaoInvalidaException("O ESTADO DO CONTEUDO NAO ESTA APROVADO");
        }
        if (avaliacaoDTO.getPresidenteId().equals(avaliacaoDTO.getArguenteId()) ||
                avaliacaoDTO.getOrientadorId().equals(avaliacaoDTO.getArguenteId()) ||
                avaliacaoDTO.getOrientadorId().equals(avaliacaoDTO.getPresidenteId()))
        {
            throw new ValidacaoInvalidaException("Não pode existir o mesmo ID para diferentes Jurados");
        }
        Optional<UtilizadorDTO> opPresidente = utilizadorRestRepository.findById(avaliacaoDTO.getPresidenteId());
        Optional<UtilizadorDTO> opOrientador = utilizadorRestRepository.findById(avaliacaoDTO.getOrientadorId());
        Optional<UtilizadorDTO> opArguente = utilizadorRestRepository.findById(avaliacaoDTO.getArguenteId());
        if (opPresidente.isEmpty())
        {
            throw new ValidacaoInvalidaException("O ID DO PRESIDENTE DO JÚRI NÃO EXISTE");
        }
        if (opOrientador.isEmpty())
        {
            throw new ValidacaoInvalidaException("O ID DO ORIENTADOR DO JÚRI NÃO EXISTE");
        }
        if (opArguente.isEmpty())
        {
            throw new ValidacaoInvalidaException("O ID DO ARGUENTE DO JÚRI NÃO EXISTE");
        }
        if (opPresidente.get().getTipoUtilizador() != TipoUtilizador.DOCENTE)
        {
            throw new ValidacaoInvalidaException(opPresidente.get().getNome() + " " + opPresidente.get().getSobrenome() + " COM O ID " + opPresidente.get().getId() + " NÃO É UM DOCENTE");
        }
        if (opOrientador.get().getTipoUtilizador() != TipoUtilizador.DOCENTE)
        {
            throw new ValidacaoInvalidaException(opOrientador.get().getNome() + " " + opOrientador.get().getSobrenome() + " COM O ID " + opOrientador.get().getId() + " NÃO É UM DOCENTE");
        }
        if (opArguente.get().getTipoUtilizador() != TipoUtilizador.DOCENTE)
        {
            throw new ValidacaoInvalidaException(opArguente.get().getNome() + " " + opArguente.get().getSobrenome() + " COM O ID " + opArguente.get().getId() + " NÃO É UM DOCENTE");
        }
        Projeto opProjeto = null;
        try
        {
            opProjeto = projetoJDBCRepository.findById(avaliacaoDTO.getIdProjeto());
        } catch (Exception e)
        {
            throw new ValidacaoInvalidaException("O PROJETO NÃO EXISTE");
        }

        if (opProjeto.getOrientadorId() != opOrientador.get().getId())
        {
            throw new ValidacaoInvalidaException("O Docente não é Orientador do Projeto");
        }

        Avaliacao avaliacao = mapper.toModel(avaliacaoDTO);

        System.out.println(avaliacao);

        avaliacaoJDBCRepository.insert(avaliacao);
    }

    public AvaliacaoDTO findById(Long id)
    {
        Avaliacao avaliacao = null;
        try
        {
            avaliacao = avaliacaoJDBCRepository.findById(id);
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe Avaliação com esse ID: "+ id);
        }
        return mapper.toDTO(avaliacao);
    }

    public List<AvaliacaoDTO> findAll()
    {
        List<Avaliacao> lista = null;
        try
        {
            lista = avaliacaoJDBCRepository.findAll();
        } catch (Exception e)
        {
            throw new ListaVaziaException("Não existem Avaliações");
        }
        return lista.stream().map(mapper::toDTO).toList();
    }

    public List<AvaliacaoDTO> findAllByPresidente(Long presidenteId)
    {
        List<Avaliacao> lista = null;
        try
        {
            lista = avaliacaoJDBCRepository.findByPresidenteId(presidenteId);
        } catch (Exception e)
        {
            throw new ListaVaziaException("Não existem Avaliações com esse id de presidente: "+ presidenteId);
        }
        return lista.stream().map(mapper::toDTO).toList();
    }
}

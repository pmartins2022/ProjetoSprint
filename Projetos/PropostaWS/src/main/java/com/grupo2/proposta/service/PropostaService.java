package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.ProjetoDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.model.TipoUtilizador;
import com.grupo2.proposta.dto.UtilizadorDTO;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.repository.PropostaRepository;
import com.grupo2.proposta.repository.rest.ProjetoRestRepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropostaService
{
    @Autowired
    private PropostaRepository repository;
    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;
    @Autowired
    private ProjetoRestRepository projetoRestRepository;

    @Autowired
    private PropostaDTOMapper mapper;

    public PropostaDTO createProposta(PropostaDTO dto) throws BaseDadosException
    {
        Proposta prop = mapper.toModel(dto);

        Proposta proposta = repository.createProposta(prop);

        return mapper.toDTO(proposta);
    }

    public Optional<PropostaDTO> rejeitarProposta(Long id)
    {
        Optional<Proposta> prop = repository.findById(id);

        if (prop.isEmpty())
        {
            return Optional.empty();
        }

        try
        {
            prop.get().reprovarProposta();
        } catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }

        PropostaDTO propostaDTOSaved = atualizarProposta(prop.get());

        return Optional.of(propostaDTOSaved);
    }

    private PropostaDTO atualizarProposta(Proposta proposta)
    {
        Proposta propostaSaved = repository.atualizarProposta(proposta);

        return mapper.toDTO(propostaSaved);
    }

    public List<PropostaDTO> findByNif(Integer nif)
    {
        List<Proposta> lista = repository.findByNif(nif);

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }
    public List<PropostaDTO> findByTitulo(String titulo)
    {
        List<Proposta> lista = repository.findAllByTitulo(titulo);

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }

    public List<PropostaDTO> findByIdUtilizador(Long id)
    {
        List<Proposta> lista = repository.findByIdUtilizador(id);

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }

    public ProjetoDTO acceptProposta(Long propostaID, Long orientadorID, Long alunoID) throws AtualizacaoInvalidaException, IdInvalidoException
    {
        Optional<Proposta> proposta = repository.findById(propostaID);
        if (proposta.isEmpty())
        {
            throw new IdInvalidoException("Id de proposta " + propostaID + " nao existe.");
        }

        try
        {
            proposta.get().aprovarProposta();
        } catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }

        Optional<UtilizadorDTO> orientadorDTO = utilizadorRestRepository.findById(orientadorID);
        Optional<UtilizadorDTO> estudanteDTO = utilizadorRestRepository.findById(alunoID);

        if (orientadorDTO.isEmpty())
        {
            throw new IdInvalidoException("Id de Orientador " + orientadorID + " nao existe.");
        }
        else if ( orientadorDTO.get().getTipoUtilizador() != TipoUtilizador.ORIENTADOR)
        {
            throw new IdInvalidoException("O ID introduzido nao é um orientador.");
        }
        if (estudanteDTO.isEmpty())
        {
            throw new IdInvalidoException("Id de Estudante " + alunoID + " nao existe.");
        }
        else if (estudanteDTO.get().getTipoUtilizador() != TipoUtilizador.ALUNO)
        {
            throw new IdInvalidoException("O ID introduzido nao é um aluno.");
        }

        ProjetoDTO projetoDTO = createProject(propostaID, orientadorID, alunoID);

        atualizarProposta(proposta.get());

        return projetoDTO;
    }

    public ProjetoDTO createProject(Long propostaID, Long orientadorID, Long estudanteID)
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(propostaID, orientadorID, estudanteID);

        ProjetoDTO saved = projetoRestRepository.create(projetoDTO);

        return saved;
    }
}
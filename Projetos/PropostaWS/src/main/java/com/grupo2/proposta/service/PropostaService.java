package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.ProjetoDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.dto.factory.ProjetoDTOFactory;
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

/**
 * Classe de servico de Proposta.
 */
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

    @Autowired
    private ProjetoDTOFactory projetoDTOFactory;


    /**
     * Criar uma nova proposta.
     * @param dto Proposta a ser criada
     * @return Proposta criada
     * @throws BaseDadosException Se ocorrerem erros relativos a base de dados
     */
    public PropostaDTO createProposta(PropostaDTO dto) throws BaseDadosException
    {
        Proposta prop = mapper.toModel(dto);

        Proposta proposta = repository.createProposta(prop);

        return mapper.toDTO(proposta);
    }

    /**
     * Rejeitar uma proposta.
     * @param id Id da proposta a ser rejeitada
     * @return Proposta rejeitada ou erro se a atualizacao for invalida
     */
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

        Optional<PropostaDTO> propostaDTOSaved = atualizarProposta(prop.get());

        if (propostaDTOSaved.isEmpty())
        {
            return Optional.empty();
        }

        return propostaDTOSaved;
    }

    private Optional<PropostaDTO> atualizarProposta(Proposta proposta)
    {
        Optional<Proposta> propostaSaved = repository.atualizarProposta(proposta);

        if (propostaSaved.isEmpty())
        {
            return Optional.empty();
        }

        return Optional.of(mapper.toDTO(propostaSaved.get()));
    }

    /**
     * Encontrar lista de propostas por NIF de organizacao
     * @param nif NIF da organizacao
     * @return Lista de propostas
     */
    public List<PropostaDTO> findByNif(Integer nif)
    {
        List<Proposta> lista = repository.findByNif(nif);

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }

    /**
     * Encontrar lista de propostas por titulo
     * @param titulo Titulo da proposta
     * @return Lista de propostas
     */
    public List<PropostaDTO> findByTitulo(String titulo)
    {
        List<Proposta> lista = repository.findAllByTitulo(titulo);

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }

    /**
     * Encontrar lista de propostas por id de utilizador
     * @param id Id do utilizador
     * @return Lista de propostas
     */
    public List<PropostaDTO> findByIdUtilizador(Long id)
    {
        List<Proposta> lista = repository.findByIdUtilizador(id);

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }

    /**
     * Aprovar uma proposta. Necessita de validar o id de orientador e de aluno em repositorios externos.
     * @param propostaID Id da proposta a ser aprovada
     * @param orientadorID Id do orientador
     * @param alunoID Id do aluno
     * @return Proposta aprovada
     * @throws AtualizacaoInvalidaException Se a atualizacao for invalida
     * @throws IdInvalidoException Se o id for invalido
     */
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

    /**
     * Criar um projeto a partir de uma proposta.
     * @param propostaID Id da proposta
     * @param orientadorID Id do orientador
     * @param estudanteID Id do estudante
     * @return Projeto criado
     */
    private ProjetoDTO createProject(Long propostaID, Long orientadorID, Long estudanteID)
    {
        ProjetoDTO projetoDTO = projetoDTOFactory.createProjeto(propostaID, orientadorID, estudanteID);

        return projetoRestRepository.create(projetoDTO);
    }
}
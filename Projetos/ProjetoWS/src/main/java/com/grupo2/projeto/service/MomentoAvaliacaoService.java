package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.mapper.MomentoAvaliacaoDTOMapper;
import com.grupo2.projeto.dto.mapper.ProjetoDTOMapper;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.jpa.MomentoAvaliacaoJPA;
import com.grupo2.projeto.model.MomentoAvaliacao;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.MomentoAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de Service do Momento de Avaliação. Possui endpoints findById, findAll e CreateAndSave.
 */
@Service
public class MomentoAvaliacaoService {
    /**
     * O repository a ser utilizado por este Service.
     */
    @Autowired
    private MomentoAvaliacaoRepository repository;


    /**
     * O mapper a ser utilizado por este Service.
     */
    @Autowired
    private MomentoAvaliacaoDTOMapper mapper;

    /**
     * Endpoint que possibilita encontrar o Momento de Avaliação por id existente no serviço.
     * @param id um objeto com os dados do id
     * @return um Momento de Avaliação
     */
    public Optional<MomentoAvaliacaoDTO> findById(Long id)
    {
        Optional<MomentoAvaliacao> optionalMomentoAvaliacao = repository.findById(id);

        if (optionalMomentoAvaliacao.isPresent())
        {
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = mapper.toDTO(optionalMomentoAvaliacao.get());
            return Optional.of(momentoAvaliacaoDTO);
        } else
        {
            return Optional.empty();
        }
    }

    /**
     * Endpoint que possibilita listar todos os Momentos de Avaliação existentes no serviço.
     * @return
     */
    public List<MomentoAvaliacaoDTO> findAll()
    {
        List<MomentoAvaliacao> lista = repository.findAll();

        List<MomentoAvaliacaoDTO> listaDTO = lista.stream().map(mapper::toDTO).toList();

        return listaDTO;
    }

    /**
     * Endpoint que possibilita cirar um Momento de Avaliação
     * @param momentoAvaliacaoDTO um objeto com os dados do Momento de Avaliação
     * @return um Momento de Avaliação
     */
    public MomentoAvaliacaoDTO createAndSave(MomentoAvaliacaoDTO momentoAvaliacaoDTO) {
        MomentoAvaliacao momentoAvaliacao = mapper.toModel(momentoAvaliacaoDTO);

        MomentoAvaliacao savedMomentoAvaliacao = repository.saveMomentoAvaliacao(momentoAvaliacao);

        return mapper.toDTO(savedMomentoAvaliacao);
        }

    }


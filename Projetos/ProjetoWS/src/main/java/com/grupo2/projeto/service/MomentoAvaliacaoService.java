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

@Service
public class MomentoAvaliacaoService {
    @Autowired
    private MomentoAvaliacaoRepository repository;

    @Autowired
    private MomentoAvaliacaoDTO momentoAvaliacaoDTO;


    @Autowired
    private MomentoAvaliacaoDTOMapper mapper;

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

    public List<MomentoAvaliacaoDTO> findAll()
    {
        List<MomentoAvaliacao> lista = repository.findAll();

        List<MomentoAvaliacaoDTO> listaDTO = lista.stream().map(mapper::toDTO).toList();

        return listaDTO;
    }
    public MomentoAvaliacaoDTO createAndSave(MomentoAvaliacaoDTO momentoAvaliacaoDTO) {
        MomentoAvaliacao momentoAvaliacao = mapper.toModel(momentoAvaliacaoDTO);

        if (momentoAvaliacaoDTO.getPresidenteId().equals(momentoAvaliacaoDTO.getArguenteId()) ||
            momentoAvaliacaoDTO.getOrientadorId().equals(momentoAvaliacaoDTO.getArguenteId()) ||
            momentoAvaliacaoDTO.getOrientadorId().equals(momentoAvaliacaoDTO.getPresidenteId())) {
            throw new ErroGeralException("Não pode existir o mesmo ID para diferentes Jurados");
        }

        MomentoAvaliacao savedMomentoAvaliacao = repository.saveMomentoAvaliacao(momentoAvaliacao);

        return mapper.toDTO(savedMomentoAvaliacao);
        }

    }


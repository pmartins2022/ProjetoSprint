package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.mapper.AvaliacaoDTOMapper;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.AvaliacaoRepository;
import com.grupo2.projeto.repository.ConteudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvaliacaoService
{
    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private ConteudoRepository contRepo;

    @Autowired
    private AvaliacaoDTOMapper mapper;

    public AvaliacaoDTO createAndSave(AvaliacaoDTO avaliacaoDTO)
    {
        Optional<Conteudo> cont = contRepo.findById(avaliacaoDTO.getConteudo());

        if (cont.isEmpty())
        {
            throw new OptionalVazioException("Nao existe conteudo com esse id");
        }

        Avaliacao avaliacao = mapper.toModel(avaliacaoDTO);

        Avaliacao savedAvaliacao = repository.saveProjeto(avaliacao, cont.get());

        return mapper.toDTO(savedAvaliacao);
    }

    public Optional<AvaliacaoDTO> findById(Long id)
    {
        Optional<Avaliacao> optionalAvaliacao = repository.findById(id);

        if (optionalAvaliacao.isPresent())
        {
            AvaliacaoDTO avaliacaoDTO = mapper.toDTO(optionalAvaliacao.get());
            return Optional.of(avaliacaoDTO);
        } else
        {
            return Optional.empty();
        }
    }
}

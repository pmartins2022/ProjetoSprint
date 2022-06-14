package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.dto.mapper.AvaliacaoDTOMapper;
import com.grupo2.projeto.dto.mapper.ConteudoDTOMapper;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.AvaliacaoRepository;
import com.grupo2.projeto.repository.ConteudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConteudoService
{
    @Autowired
    private ConteudoRepository repository;

    @Autowired
    private ConteudoDTOMapper mapper;

    public ConteudoDTO createAndSave(ConteudoDTO conteudoDTO)
    {
        Conteudo conteudo = mapper.toModel(conteudoDTO);

        Conteudo saved = repository.saveConteudo(conteudo);

        return mapper.toDTO(saved);
    }

    public Optional<ConteudoDTO> findById(Long id)
    {
        Optional<Conteudo> optional = repository.findById(id);

        if (optional.isPresent())
        {
            ConteudoDTO conteudoDTO = mapper.toDTO(optional.get());
            return Optional.of(conteudoDTO);
        } else
        {
            return Optional.empty();
        }
    }
}

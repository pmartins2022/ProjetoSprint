package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.mapper.ProjetoDTOMapper;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjetoService
{
    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private ProjetoDTOMapper mapper;

    public Optional<ProjetoDTO> findById(Long id)
    {
        Optional<Projeto> optionalProjeto = repository.findById(id);

        if (optionalProjeto.isPresent())
        {
            ProjetoDTO projetoDTO = mapper.toDTO(optionalProjeto.get());
            return Optional.of(projetoDTO);
        } else
        {
            return Optional.empty();
        }
    }

    public ProjetoDTO createAndSave(ProjetoDTO projetoDTO)
    {
        Projeto projeto = mapper.toModel(projetoDTO);

        Projeto savedProjeto = repository.saveProjeto(projeto);

        return mapper.toDTO(savedProjeto);
    }
}

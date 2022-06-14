package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.mapper.ProjetoDTOMapper;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe de Service do projeto. Possui endpoints para createAndSaveProjeto e findById.
 */
@Service
public class ProjetoService
{
    /**
     * O repository a ser utilizado por este Service.
     */
    @Autowired
    private ProjetoRepository repository;


    /**
     * O mapper a ser utilizado por este Service.
     */
    @Autowired
    private ProjetoDTOMapper mapper;

    /**
     * Endpoint que possibilita encontrar o projeto por id existente no serviço.
     * @param id um objeto com os dados do id
     * @return um projeto.
     */
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

    /**
     * Endpoint que possibilita criar um projeto.
     * @param projetoDTO um objeto com os dados do projeto
     * @return um projeto
     */
    public ProjetoDTO createAndSave(ProjetoDTO projetoDTO)
    {
        Projeto projeto = mapper.toModel(projetoDTO);

        Projeto savedProjeto = repository.saveProjeto(projeto);

        return mapper.toDTO(savedProjeto);
    }
}

package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.dto.mapper.ProjetoDTOMapper;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.ProjetoJDBCRepository;
import com.grupo2.projeto.repository.jdbc.GenericJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
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
    private JdbcTemplate jdbcTemplate;


    /**
     * O mapper a ser utilizado por este Service.
     */
    @Autowired
    private ProjetoDTOMapper mapper;
    @Autowired
    private ProjetoJDBCRepository projetoJDBCRepository;

    /**
     * Endpoint que possibilita encontrar o projeto por id existente no serviço.
     * @param id um objeto com os dados do id
     * @return um projeto.
     */
    public Optional<ProjetoDTO> findById(Long id)
    {
       /* Optional<Projeto> optionalProjeto = repository.findById(id);

        if (optionalProjeto.isPresent())
        {
            ProjetoDTO projetoDTO = mapper.toDTO(optionalProjeto.get());
            return Optional.of(projetoDTO);
        } else
        {
            return Optional.empty();
        }*/

        return Optional.empty();
    }

    /**
     * Endpoint que possibilita criar um projeto.
     * @param projetoDTO um objeto com os dados do projeto
     * @return um projeto
     */
    public void createAndSave(ProjetoDTO projetoDTO)
    {
        Projeto projeto = mapper.toModel(projetoDTO);

        try
        {
            GenericJDBCRepository.from(jdbcTemplate,projeto).save();
        } catch (Exception e)
        {
            throw new ErroGeralException("Nao conseguiu guardar na DB: "+e);
        }

       /* Projeto savedProjeto = repository.saveProjeto(projeto);

        return mapper.toDTO(savedProjeto);*/
    }

    public List<ProjetoDTO> findAllByOrientadorId(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        return projetoJDBCRepository.findAllByOrientadorId(id).stream().map(mapper::toDTO).toList();
    }
}

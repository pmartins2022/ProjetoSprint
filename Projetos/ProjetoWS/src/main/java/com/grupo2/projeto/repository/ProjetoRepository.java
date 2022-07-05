package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.ProjetoJPA;
import com.grupo2.projeto.jpa.mapper.ProjetoJPAMapper;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.jpa.ProjetoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe Repository do Projeto
 */
@Repository
public class ProjetoRepository
{
    /**
     * O repositoryJPA a ser utilizado por este Repository.
     */
    @Autowired
    private ProjetoJPARepository repository;

    /**
     * O repositoryJPAMapper a ser utilizado por este Repository.
     */
    @Autowired
    private ProjetoJPAMapper mapper;

    /**
     * Endpoint que possibilita encontrar o projeto por id existente no repositorio.
     * @param id um objeto com os dados do id
     * @return um projeto.
     */
    public Optional<Projeto> findById(Long id)
    {
        Optional<ProjetoJPA> optionalProjetoJPA = repository.findById(id);

        if (optionalProjetoJPA.isPresent())
        {
            Projeto projeto = mapper.toModel(optionalProjetoJPA.get());
            return Optional.of(projeto);
        }
        else
        {
            return Optional.empty();
        }
    }

    /**
     * Endpoint que possibilita gravar o projeto existente no repositorio.
     * @param projeto um objeto com os dados
     * @return do projeto guardado
     */
    public Projeto saveProjeto(Projeto projeto)
    {
        ProjetoJPA jpa = mapper.toJpa(projeto);

        ProjetoJPA saved = repository.save(jpa);

        return mapper.toModel(saved);
    }

    public List<Projeto> findAllByOrientadorId(Long id)
    {
        List<ProjetoJPA> list = repository.findAllByOrientadorId(id);

        return list.stream().map(mapper::toModel).toList();
    }
}

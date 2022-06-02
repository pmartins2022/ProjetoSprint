package com.grupo2.projeto.repository;

import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.jpa.ProjetoJPA;
import com.grupo2.projeto.jpa.mapper.ProjetoJPAMapper;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.jpa.ProjetoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjetoRepository
{
    @Autowired
    private ProjetoJPARepository repository;

    @Autowired
    private ProjetoJPAMapper mapper;

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

    public Projeto saveProjeto(Projeto projeto)
    {

        ProjetoJPA jpa = mapper.toJpa(projeto);

        ProjetoJPA saved = repository.save(jpa);

        return mapper.toModel(saved);
    }
}

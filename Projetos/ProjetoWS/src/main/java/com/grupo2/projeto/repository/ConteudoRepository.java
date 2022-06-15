package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.ConteudoJPA;
import com.grupo2.projeto.jpa.mapper.ConteudoJPAMapper;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.jpa.ConteudoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConteudoRepository
{
    @Autowired
    private ConteudoJPARepository repository;

    @Autowired
    private ConteudoJPAMapper mapper;
    public Conteudo saveConteudo(Conteudo conteudo)
    {
        ConteudoJPA jpa = mapper.toJpa(conteudo);

        ConteudoJPA saved = repository.save(jpa);

        return mapper.toModel(saved);
    }

    public Optional<Conteudo> findById(Long id)
    {
        Optional<ConteudoJPA> optionalJPA = repository.findById(id);

        if (optionalJPA.isPresent())
        {
            Conteudo conteudo = mapper.toModel(optionalJPA.get());
            return Optional.of(conteudo);
        }
        else
        {
            return Optional.empty();
        }
    }

    public Optional<Conteudo> atualizarConteudo(Conteudo conteudo)
    {
        if (repository.findById(conteudo.getId()).isPresent())
        {
            repository.deleteById(conteudo.getId());

            ConteudoJPA jpa = mapper.toJpa(conteudo);

            ConteudoJPA saved = repository.save(jpa);

            return Optional.of(mapper.toModel(saved));
        }
        else
        {
            return Optional.empty();
        }
    }
}

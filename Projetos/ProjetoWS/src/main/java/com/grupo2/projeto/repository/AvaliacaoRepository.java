package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.AvaliacaoJPA;
import com.grupo2.projeto.jpa.ConteudoJPA;
import com.grupo2.projeto.jpa.mapper.AvaliacaoJPAMapper;
import com.grupo2.projeto.jpa.mapper.ConteudoJPAMapper;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.jpa.AvaliacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AvaliacaoRepository
{
    @Autowired
    private AvaliacaoJPARepository repository;

    @Autowired
    private AvaliacaoJPAMapper mapper;

    @Autowired
    private ConteudoJPAMapper conteudoJPAMapper;

    public Avaliacao saveProjeto(Avaliacao avaliacao, Conteudo cont)
    {
        AvaliacaoJPA jpa = new AvaliacaoJPA(avaliacao.getId(),avaliacao.getIdMomentoAvaliacao(),avaliacao.getIdProjeto(),conteudoJPAMapper.toJpa(cont), avaliacao.getNota());

        AvaliacaoJPA saved = repository.save(jpa);

        return mapper.toModel(saved);
    }

    public Optional<Avaliacao> findById(Long id)
    {
        Optional<AvaliacaoJPA> optionalAvaliacaoJPA = repository.findById(id);

        if (optionalAvaliacaoJPA.isPresent())
        {
            Avaliacao avaliacao = mapper.toModel(optionalAvaliacaoJPA.get());
            return Optional.of(avaliacao);
        }
        else
        {
            return Optional.empty();
        }
    }
}

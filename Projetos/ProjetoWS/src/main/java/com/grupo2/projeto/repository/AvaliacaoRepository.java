package com.grupo2.projeto.repository;

import com.grupo2.projeto.dataModel.jpa.AvaliacaoJPA;
import com.grupo2.projeto.dataModel.jpa.ConteudoJPA;
import com.grupo2.projeto.dataModel.jpa.factory.AvaliacaoJPAFactory;
import com.grupo2.projeto.dataModel.jpa.mapper.AvaliacaoJPAMapper;
import com.grupo2.projeto.dataModel.jpa.mapper.ConteudoJPAMapper;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.repository.jpa.AvaliacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Autowired
    private AvaliacaoJPAFactory factory;

    public Avaliacao saveAvaliacao(Avaliacao avaliacao, Conteudo cont)
    {
        ConteudoJPA conteudoJPA = conteudoJPAMapper.toJpa(cont);

        AvaliacaoJPA jpa = factory.create(avaliacao.getId(),avaliacao.getIdMomentoAvaliacao(), avaliacao.getPresidenteId(), avaliacao.getOrientadorId(), avaliacao.getArguenteId(), avaliacao.getIdProjeto(),conteudoJPA, avaliacao.getNota());

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

    public List<Avaliacao> findAll() {
            List<AvaliacaoJPA> listaJPA = repository.findAll();
            return listaJPA.stream().map(mapper::toModel).toList();
    }

}

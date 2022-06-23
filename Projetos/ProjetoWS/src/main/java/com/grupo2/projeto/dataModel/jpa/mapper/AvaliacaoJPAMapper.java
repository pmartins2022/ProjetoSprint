package com.grupo2.projeto.dataModel.jpa.mapper;

import com.grupo2.projeto.dataModel.jpa.ConteudoJPA;
import com.grupo2.projeto.exception.IdInvalidoException;
import com.grupo2.projeto.dataModel.jpa.AvaliacaoJPA;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.factory.AvaliacaoFactory;
import com.grupo2.projeto.repository.ConteudoRepository;
import com.grupo2.projeto.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AvaliacaoJPAMapper {
    @Autowired
    private AvaliacaoFactory factory;

    @Autowired
    private ConteudoJPAMapper mapper;

    @Autowired
    private ConteudoService service;

    @Autowired
    private Conteudo conteudo;

    public Avaliacao toModel(AvaliacaoJPA jpa) {
        return factory.createAvaliacao(jpa.getId(),jpa.getIdMomentoAvaliacao(), jpa.getPresidenteId(), jpa.getOrientadorId(), jpa.getArguenteId(),jpa.getIdProjeto(), jpa.getConteudo().getId(), jpa.getNota());
    }

    public AvaliacaoJPA toJPA(Avaliacao avaliacao, ConteudoRepository repo, ConteudoJPAMapper mapper)
    {
        Optional<Conteudo> id = repo.findById(avaliacao.getConteudo());

        if (id.isEmpty())
        {
            throw new IdInvalidoException("O ID DO CONTEUDO NAO EXISTE");
        }

        ConteudoJPA conteudo = mapper.toJpa(id.get());

        return new AvaliacaoJPA(avaliacao.getId(),avaliacao.getIdMomentoAvaliacao(),
                avaliacao.getPresidenteId(), avaliacao.getOrientadorId(),
                avaliacao.getArguenteId(),avaliacao.getIdProjeto(), conteudo, avaliacao.getNota());
    }
}
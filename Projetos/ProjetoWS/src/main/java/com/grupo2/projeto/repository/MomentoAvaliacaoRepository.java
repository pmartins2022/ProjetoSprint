package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.MomentoAvaliacaoJPA;
import com.grupo2.projeto.jpa.mapper.MomentoAvaliacaoJPAMapper;
import com.grupo2.projeto.model.MomentoAvaliacao;
import com.grupo2.projeto.repository.jpa.MomentoAvaliacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MomentoAvaliacaoRepository {
    @Autowired
    private MomentoAvaliacaoJPARepository repository;

    @Autowired
    private MomentoAvaliacaoJPAMapper mapper;

    public Optional<MomentoAvaliacao> findById(Long id){
        Optional<MomentoAvaliacaoJPA> optionalMomentoAvaliacaoJPA = repository.findById(id);
        if(optionalMomentoAvaliacaoJPA.isPresent())
        {
            MomentoAvaliacao momentoAvaliacao = mapper.toModel(optionalMomentoAvaliacaoJPA.get());
            return Optional.of(momentoAvaliacao);
        }
        else
        {
            return Optional.empty();
        }
    }
    public List<MomentoAvaliacao> findAll(){
        List<MomentoAvaliacaoJPA> listaJPA = repository.findAll();
        return listaJPA.stream().map(mapper::toModel).toList();
    }

    public MomentoAvaliacao saveMomentoAvaliacao(MomentoAvaliacao momentoAvaliacao)
    {
        MomentoAvaliacaoJPA jpa = mapper.toJPA(momentoAvaliacao);

        MomentoAvaliacaoJPA saved = repository.save(jpa);

        return mapper.toModel(saved);
    }
}

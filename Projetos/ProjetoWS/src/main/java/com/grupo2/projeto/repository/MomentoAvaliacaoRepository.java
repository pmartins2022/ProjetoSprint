package com.grupo2.projeto.repository;

import com.grupo2.projeto.jpa.MomentoAvaliacaoJPA;
import com.grupo2.projeto.jpa.mapper.MomentoAvaliacaoJPAMapper;
import com.grupo2.projeto.model.MomentoAvaliacao;
import com.grupo2.projeto.repository.jpa.MomentoAvaliacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe Repository do Momento de Avaliação
 */
@Repository
public class MomentoAvaliacaoRepository {
    /**
     * O repositoryJPA a ser utiliado por este Repository
     */
    @Autowired
    private MomentoAvaliacaoJPARepository repository;
    /**
     * O repositoryJPAMapper a ser utilizado por este Repository.
     */
    @Autowired
    private MomentoAvaliacaoJPAMapper mapper;

    /**
     * Endpoint que possibilita encontrar o Momento de Avaliação por id existente no repositório.
     * @param id um objeto com os dados do id
     * @return um Momento de Avaliação
     */
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

    /**
     * Endpoint que possibilita encontrar todos os Momentos de Avaliação existentes
     * @return todos os objetos existentes
     */
    public List<MomentoAvaliacao> findAll(){
        List<MomentoAvaliacaoJPA> listaJPA = repository.findAll();
        return listaJPA.stream().map(mapper::toModel).toList();
    }

    /**
     * Endpoint que possibilita gravar o Momento de Avaliação existente no repositorio
     * @param momentoAvaliacao um objeto com os dados
     * @return do Momento de Avaliação Guardado
     */
    public MomentoAvaliacao saveMomentoAvaliacao(MomentoAvaliacao momentoAvaliacao)
    {
        MomentoAvaliacaoJPA jpa = mapper.toJPA(momentoAvaliacao);

        MomentoAvaliacaoJPA saved = repository.save(jpa);

        return mapper.toModel(saved);
    }
}

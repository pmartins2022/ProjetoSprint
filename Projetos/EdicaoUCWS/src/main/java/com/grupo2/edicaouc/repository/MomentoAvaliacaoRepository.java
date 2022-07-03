package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.MomentoAvaliacaoJPA;
import com.grupo2.edicaouc.jpa.mapper.MomentoAvaliacaoJPAMapper;
import com.grupo2.edicaouc.model.MomentoAvaliacao;
import com.grupo2.edicaouc.repository.jpa.MomentoAvaliacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe MomentoAvaliacaoRepository que permite estabeler ligação com MomentoAvaliacaoJPARepository
 */
@Repository
public class MomentoAvaliacaoRepository
{
    @Autowired
    private MomentoAvaliacaoJPARepository repo;

    @Autowired
    private MomentoAvaliacaoJPAMapper mapper;

    /**
     * Devolve MomentoAvaliacao após persistência
     * @param momento MomentoAvaliacao a guardar
     * @return MomentoAvaliacao guardado
     */
    public MomentoAvaliacao createAndSaveMomentoAvaliacao(MomentoAvaliacao momento)
    {
        MomentoAvaliacaoJPA jpa = mapper.toJPA(momento);

        MomentoAvaliacaoJPA save = repo.save(jpa);

        return mapper.toModel(save);
    }

    /**
     * Devolve MomentoAvaliacao filtrado pelo ID ou Optional.Empty()
     * @param idMomentoAvaliacao id de  MomentoAvaliacao
     * @return MomentoAvaliacao ou Optional.Empty()
     */
    public Optional<MomentoAvaliacao> findById(Long idMomentoAvaliacao)
    {
        Optional<MomentoAvaliacaoJPA> id = repo.findById(idMomentoAvaliacao);

        if (id.isPresent())
        {
            return Optional.of(mapper.toModel(id.get()));
        }

        return Optional.empty();
    }

    /**
     * Apaga MomentoAvaliacao filtrado pelo id
     * @param id id
     */
    public void deleteByID(Long id)
    {
        repo.deleteById(id);
    }
}
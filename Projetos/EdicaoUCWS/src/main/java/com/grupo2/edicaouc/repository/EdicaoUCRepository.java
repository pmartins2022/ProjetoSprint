package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.exception.BaseDadosException;
import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe de EdicaoUCRepository. Possui endpoints para saveEdicaoUC, findAllEdicaoByUCCode e findById.
 */
@Repository
public class EdicaoUCRepository
{
    /**
     * O repositoryJPA a ser utilizado por este Repository.
     */
    @Autowired
    private EdicaoUCJpaRepository jpaRepository;
    /**
     * O restRepository a ser utilizado por este Repository.
     */

    @Autowired
    private EdicaoUCJPAMapper mapper;

    /**
     * Endpoint que possibilita criar e guardar EdicaoUC
     * @param edicaoUC objeto com dados de EdicaoUC
     * @return uma EdicaoUc guardada
     * @throws BaseDadosException caso Id de Ano Letivo ou Id de Unidade Curricular não existam em Sistema
     */
    public EdicaoUC saveEdicaoUC(EdicaoUC edicaoUC) throws BaseDadosException
    {
        EdicaoUCJPA jpa = mapper.toJpa(edicaoUC);
        EdicaoUCJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    /**
     * Endpoint que possibilita encontrar todas as Edições existente no repositorio com um determinado código de Unidade Curricular.
     * @param UCCode objeto com dados
     * @return Lista de Edições com determinado código de Unidade Curricular
     */

    public List<EdicaoUC> findAllEdicaoByUCCode(String UCCode)
    {
        List<EdicaoUCJPA> lista = jpaRepository.findAllByucCode(UCCode);

        return lista.stream().map(mapper::toModel).toList();
    }

    /**
     * Endpoint que possibilita encontrar uma Edição com um determinado Id
     * @param id objeto com dados
     * @return Edição com um determinado Id
     */
    public Optional<EdicaoUC> findById(Long id)
    {
        Optional<EdicaoUCJPA> jpa = jpaRepository.findById(id);

        if (jpa.isPresent())
        {
            return Optional.of(mapper.toModel(jpa.get()));
        }

        return Optional.empty();

    }

    /**
     * Encontrar todas as Edicoes UC existentes.
     * @return a lista de Edicoes UC
     */
    public List<EdicaoUC> findAll()
    {
        List<EdicaoUCJPA> lista = jpaRepository.findAll();

        return lista.stream().map(mapper::toModel).toList();
    }
}

package com.grupo2.organizacao.repository;

import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import com.grupo2.organizacao.jpa.mapper.OrganizacaoJPAMapper;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.repository.jpa.OrganizacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe de Repository do organizacao. Possui endpoints para save, findById, findAll e findByNif.
 */
@Repository
public class OrganizacaoRepository
{

    @Autowired
    private OrganizacaoJPARepository jpaRepository;

    @Autowired
    private OrganizacaoJPAMapper mapper;

    /**
     * Endpoint que possibilita encontrar o organizacao por id existente no repositorio.
     * @param id um objeto com os dados do id
     * @return uma organizacao
     */
    public Optional<Organizacao> findByID(Long id)
    {
        Optional<OrganizacaoJPA> organizacaoJPA = jpaRepository.findById(id);

        if (organizacaoJPA.isEmpty()){
            return Optional.empty();
        }
        Organizacao organizacao = mapper.toModel(organizacaoJPA.get());
        return Optional.of(organizacao);
    }

    /**
     * Endpoint que possibilita gravar o organizacao existente no repositorio.
     * @param organizacao um objeto com os dados
     * @return uma organizacao guardado
     */
    public Organizacao save(Organizacao organizacao)
    {
        OrganizacaoJPA jpa = mapper.toJpa(organizacao);
        OrganizacaoJPA jpaSaved = jpaRepository.save(jpa);
        Organizacao saved = mapper.toModel(jpaSaved);

        return saved;
    }

    /**
     * Endpoint que possibilita encontrar o organizacao existente no repositorio pelo nif.
     * @param nif e um objeto com dados
     * @return uma organizacao
     */
    public Optional<Organizacao> findByNIF(Integer nif)
    {
        Optional<OrganizacaoJPA> organizacaoJPA = jpaRepository.findBynif(nif);

        if (organizacaoJPA.isEmpty()){
            return Optional.empty();
        }
        Organizacao organizacao = mapper.toModel(organizacaoJPA.get());
        return Optional.of(organizacao);
    }

    /**
     * Endpoint que possibilita encontrar tadas organizacoes existente no repositorio.
     * @return lista de organizacoes
     */
    public List<Organizacao> findAll()
    {
        List<OrganizacaoJPA> list =jpaRepository.findAll();

        return list.stream().map(mapper::toModel).toList();
    }

    public void deleteByID(Long id)
    {
        jpaRepository.deleteById(id);
    }
}

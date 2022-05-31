package com.grupo2.organizacao.repository;

import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import com.grupo2.organizacao.jpa.mapper.OrganizacaoJPAMapper;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.repository.jpa.OrganizacaoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrganizacaoRepository
{
    @Autowired
    private OrganizacaoJPARepository jpaRepository;

    @Autowired
    private OrganizacaoJPAMapper mapper;

    public Optional<Organizacao> findByID(Long id)
    {
        Optional<OrganizacaoJPA> organizacaoJPA = jpaRepository.findById(id);

        if (organizacaoJPA.isEmpty()){
            return Optional.empty();
        }
        Organizacao organizacao = mapper.toModel(organizacaoJPA.get());
        return Optional.of(organizacao);
    }

    public Organizacao save(Organizacao organizacao)
    {
        OrganizacaoJPA jpa = mapper.toJpa(organizacao);
        OrganizacaoJPA jpaSaved = jpaRepository.save(jpa);
        Organizacao saved = mapper.toModel(jpaSaved);

        return saved;
    }

}

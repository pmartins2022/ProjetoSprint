package com.grupo2.organizacao.jpa.mapper;

import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.model.factory.OrganizacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizacaoJPAMapper
{
    @Autowired
    OrganizacaoFactory organizacaoFactory;

    public Organizacao toModel(OrganizacaoJPA jpa)
    {
        return organizacaoFactory.createOrganizacao(jpa.getId(),jpa.getDenominacao(),jpa.getNif());
    }
    public OrganizacaoJPA toJpa(Organizacao model)
    {
        return new OrganizacaoJPA(model.getId(),model.getDenominacao(), model.getNif());
    }
}
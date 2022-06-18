package com.grupo2.organizacao.jpa.mapper;

import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.model.factory.OrganizacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao entre objetos organizacao de JPA para classe de dominio.
 */
@Component
public class OrganizacaoJPAMapper
{

    @Autowired
    private OrganizacaoFactory organizacaoFactory;

    /**
     * Fazer a conversao para classe de dominio.
     * @param jpa o objeto jpa com os dados
     * @return o objeto convertido
     */
    public Organizacao toModel(OrganizacaoJPA jpa)
    {
        return organizacaoFactory.createOrganizacao(jpa.getId(),jpa.getDenominacao(),jpa.getNif());
    }

    /**
     * Fazer a conversao para classe JPA
     * @param model o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public OrganizacaoJPA toJpa(Organizacao model)
    {
        return new OrganizacaoJPA(model.getId(),model.getDenominacao(), model.getNif());
    }
}
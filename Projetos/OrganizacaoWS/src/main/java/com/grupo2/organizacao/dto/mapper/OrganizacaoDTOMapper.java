package com.grupo2.organizacao.dto.mapper;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.model.factory.OrganizacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizacaoDTOMapper
{
    @Autowired
    private OrganizacaoFactory factory;

    public Organizacao toModel(OrganizacaoDTO dto)
    {
        Organizacao organizacao = factory.createOrganizacao(dto.getId(), dto.getDenominacao(), dto.getNif());
        return organizacao;
    }
    public OrganizacaoDTO toDTO(Organizacao organizacao)
    {
        OrganizacaoDTO dto = new OrganizacaoDTO(organizacao.getId(), organizacao.getDenominacao(), organizacao.getNif());

        return dto;
    }


}

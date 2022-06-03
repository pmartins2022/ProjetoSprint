package com.grupo2.organizacao.dto.mapper;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.model.factory.OrganizacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao entre objetos Organizacao de DTO para classe de dominio.
 */
@Component
public class OrganizacaoDTOMapper
{
    /**
     * O factory a ser utilizado por este DTO Mapper.
     */
    @Autowired
    private OrganizacaoFactory factory;

    /**
     *Fazer a conversao para classe de dominio.
     * @param dto o objeto dto com os dados
     * @return o objeto convertido
     */
    public Organizacao toModel(OrganizacaoDTO dto)
    {
        Organizacao organizacao = factory.createOrganizacao(dto.getId(), dto.getDenominacao(), dto.getNif());
        return organizacao;
    }

    /**
     * Fazer a conversao para classe DTO
     * @param organizacao o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public OrganizacaoDTO toDTO(Organizacao organizacao)
    {
        OrganizacaoDTO dto = new OrganizacaoDTO(organizacao.getId(), organizacao.getDenominacao(), organizacao.getNif());

        return dto;
    }


}

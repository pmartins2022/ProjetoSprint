package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.model.annotations.IgnoreField;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;

/**
 * Classe DTO que contem informacao sobre uma organizacao.
 */
@Table(tableName = "ORGANIZACAO")
public class OrganizacaoDTO extends JDBCTable
{

    /**
     * denominacao de OrganizacaoDTO
     */
    private String denominacao;
    /**
     * nif de OrganizacaoDTO
     */
    @PrimaryKey( generated = false)
    private Long nif;

    /**
     * Inicializa OrganizacaoDTO sem parametros
     */
    public OrganizacaoDTO()
    {
    }

    /**
     * Inicializa o id, denominacao e nif da OrganizacaoDTO com o id, denominacao e nif
     * @param denominacao é a denominacao da OrganizacaoDTO
     * @param nif é o nif da OrganizacaoDTO
     */
    public OrganizacaoDTO(String denominacao, Long nif)
    {
        this.denominacao = denominacao;
        this.nif = nif;
    }

    /**
     * Devolve a denominacao da OrganizacaoDTO
     * @return denominacao da OrganizacaoDTO
     */
    public String getDenominacao()
    {
        return denominacao;
    }

    /**
     * Modifica a denominacao da OrganizacaoDTO
     * @param denominacao nova denominacao da OrganizacaoDTO
     */
    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    /**
     * Devolve o nif da OrganizacaoDTO
     * @return nif da OrganizacaoDTO
     */
    public Long getNif()
    {
        return nif;
    }

    /**
     * Modifica o nif da OrganizacaoDTO
     * @param nif novo nif da OrganizacaoDTO
     */
    public void setNif(Long nif)
    {
        this.nif = nif;
    }
}

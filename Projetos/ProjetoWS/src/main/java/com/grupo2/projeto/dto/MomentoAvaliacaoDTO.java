package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.annotations.ForeignKey;
import com.grupo2.projeto.model.annotations.IgnoreField;
import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;

@Table(tableName = "MOMENTOAVALIACAO")
public class MomentoAvaliacaoDTO extends JDBCTable
{
    @PrimaryKey( generated = false)
    private Long id;

    @ForeignKey(className = EdicaoUCDTO.class, fieldName = "ID")
    private Long edicaoUCID;
    private String denominacao;

    public MomentoAvaliacaoDTO()
    {
    }

    public MomentoAvaliacaoDTO(Long id, String denominacao, Long edicaoUCID)
    {
        this.id = id;
        this.denominacao = denominacao;
        this.edicaoUCID = edicaoUCID;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }

    public Long getEdicaoUCID()
    {
        return edicaoUCID;
    }

    public void setEdicaoUCID(Long edicaoUCID)
    {
        this.edicaoUCID = edicaoUCID;
    }

    @Override
    public String toString()
    {
        return "MomentoAvaliacaoDTO{" +
                "id=" + id +
                ", denominacao='" + denominacao + '\'' +
                '}';
    }
}

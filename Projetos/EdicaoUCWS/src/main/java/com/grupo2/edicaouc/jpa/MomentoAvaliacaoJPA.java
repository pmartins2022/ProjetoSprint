package com.grupo2.edicaouc.jpa;

import javax.persistence.*;

@Entity
@Table(name = "MomentoAvaliacao", uniqueConstraints = @UniqueConstraint(columnNames = {"denominacao"}))
public class MomentoAvaliacaoJPA
{
    @Id
    @GeneratedValue
    @Column(name = "ma_id")
    private Long id;
	private Long idEdicao;
    private String denominacao;

    public MomentoAvaliacaoJPA()
    {
    }

    public MomentoAvaliacaoJPA(Long id, Long idEdicao, String denominacao)
    {
        this.id = id;
		this.idEdicao = idEdicao;
        this.denominacao = denominacao;
    }

    public Long getId()
    {
        return id;
    }

    public String getDenominacao()
    {
        return denominacao;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setDenominacao(String denominacao)
    {
        this.denominacao = denominacao;
    }
	
	public Long getIdEdicao()
	{
		return idEdicao;
	}
	
	public void setIdEdicao(Long idEdicao)
	{
		this.idEdicao = idEdicao;
	}
}
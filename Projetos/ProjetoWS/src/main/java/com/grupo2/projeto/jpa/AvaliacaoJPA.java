package com.grupo2.projeto.jpa;

import com.grupo2.projeto.model.Conteudo;

import javax.persistence.*;

@Entity
@Table(name = "Avaliacao")
public class AvaliacaoJPA
{
    @Id
    @GeneratedValue
    private Long id;

    private Long idMomentoAvaliacao;

    private Long idProjeto;

    @ManyToOne
    private ConteudoJPA conteudo;

    private int nota;

    public AvaliacaoJPA () {}

    public AvaliacaoJPA(Long id, Long idMomentoAvaliacao, Long idProjeto, ConteudoJPA conteudo, int nota)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.idProjeto = idProjeto;
        this.conteudo = conteudo;
        this.nota = nota;
    }

    public AvaliacaoJPA(Long idMomentoAvaliacao, Long idProjeto, ConteudoJPA conteudo, int nota)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.idProjeto = idProjeto;
        this.conteudo = conteudo;
        this.nota = nota;
    }

    public Long getId()
    {
        return id;
    }

    public Long getIdMomentoAvaliacao()
    {
        return idMomentoAvaliacao;
    }

    public Long getIdProjeto()
    {
        return idProjeto;
    }

    public ConteudoJPA getConteudo()
    {
        return conteudo;
    }

    public int getNota()
    {
        return nota;
    }
}

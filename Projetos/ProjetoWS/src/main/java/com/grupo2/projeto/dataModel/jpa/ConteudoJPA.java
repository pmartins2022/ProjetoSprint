package com.grupo2.projeto.dataModel.jpa;

import com.grupo2.projeto.model.EstadoConteudo;

import javax.persistence.*;

@Entity
@Table(name = "Conteudo")
public class ConteudoJPA
{
    @Id
    @GeneratedValue
    private Long id;

    private Long idProjeto;

    private String titulo;

    @Column(name = "caminhoDocumento",unique = true)
    private String caminhoDocumento;
    @Column(columnDefinition = "LONGTEXT")
    private String documento;

    private String linguagemDocumento;

    private EstadoConteudo estadoConteudo;

    public ConteudoJPA()
    {
    }

    public ConteudoJPA(Long id, Long idProjeto, String titulo, String caminhoDocumento, String documento, String linguagemDocumento, EstadoConteudo estadoConteudo)
    {
        this.id = id;
        this.idProjeto = idProjeto;
        this.titulo = titulo;
        this.caminhoDocumento = caminhoDocumento;
        this.documento = documento;
        this.linguagemDocumento = linguagemDocumento;
        this.estadoConteudo = estadoConteudo;
    }

    public Long getId()
    {
        return id;
    }

    public Long getIdProjeto()
    {
        return idProjeto;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getDocumento()
    {
        return documento;
    }

    public String getLinguagemDocumento()
    {
        return linguagemDocumento;
    }

    public String getCaminhoDocumento()
    {
        return caminhoDocumento;
    }

    public EstadoConteudo getEstadoConteudo()
    {
        return estadoConteudo;
    }
}

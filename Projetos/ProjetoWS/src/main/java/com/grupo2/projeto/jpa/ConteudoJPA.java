package com.grupo2.projeto.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Conteudo")
public class ConteudoJPA
{
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    private String documento;

    private String linguagemDocumento;

    public ConteudoJPA()
    {
    }

    public ConteudoJPA(Long id, String titulo, String documento, String linguagemDocumento)
    {
        this.id = id;
        this.titulo = titulo;
        this.documento = documento;
        this.linguagemDocumento = linguagemDocumento;
    }

    public ConteudoJPA(String titulo, String documento, String linguagemDocumento)
    {
        this.titulo = titulo;
        this.documento = documento;
        this.linguagemDocumento = linguagemDocumento;
    }

    public Long getId()
    {
        return id;
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
}

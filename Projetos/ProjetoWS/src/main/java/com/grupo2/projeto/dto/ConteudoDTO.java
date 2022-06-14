package com.grupo2.projeto.dto;

public class ConteudoDTO
{
    private Long id;

    private String titulo;

    private String documento;

    private String linguagemDocumento;

    public ConteudoDTO()
    {
    }

    public ConteudoDTO(Long id, String titulo, String documento, String linguagemDocumento)
    {
        this.id = id;
        this.titulo = titulo;
        this.documento = documento;
        this.linguagemDocumento = linguagemDocumento;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getDocumento()
    {
        return documento;
    }

    public void setDocumento(String documento)
    {
        this.documento = documento;
    }

    public String getLinguagemDocumento()
    {
        return linguagemDocumento;
    }

    public void setLinguagemDocumento(String linguagemDocumento)
    {
        this.linguagemDocumento = linguagemDocumento;
    }
}

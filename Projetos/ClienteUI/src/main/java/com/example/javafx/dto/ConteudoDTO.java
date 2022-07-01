package com.example.javafx.dto;


import com.example.javafx.dto.enums.EstadoConteudo;

public class ConteudoDTO
{
    private Long id;

    private Long projetoId;

    private String titulo;

    private String caminhoDocumento;

    private String documento;

    private String linguagemDocumento;

    private String estadoConteudo;

    public ConteudoDTO()
    {
    }

    public ConteudoDTO(Long id, Long projetoId, String titulo, String caminhoDocumento, String documento, String linguagemDocumento, String estadoConteudo)
    {
        this.id = id;
        this.projetoId = projetoId;
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getProjetoId()
    {
        return projetoId;
    }

    public void setProjetoId(Long projetoId)
    {
        this.projetoId = projetoId;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public String getCaminhoDocumento()
    {
        return caminhoDocumento;
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

    public String getEstadoConteudo()
    {
        return estadoConteudo;
    }

    public void setEstadoConteudo(String estadoConteudo)
    {
        this.estadoConteudo = estadoConteudo;
    }
}

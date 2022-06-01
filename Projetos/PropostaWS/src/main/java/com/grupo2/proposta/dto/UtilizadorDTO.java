package com.grupo2.proposta.dto;

import com.grupo2.proposta.model.TipoUtilizador;

public class UtilizadorDTO
{
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private TipoUtilizador tipoUtilizador;

    public UtilizadorDTO()
    {
    }

    public UtilizadorDTO(Long id, String nome, String sobrenome, String email, TipoUtilizador tipoUtilizador)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSobrenome()
    {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(TipoUtilizador tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
    }
}

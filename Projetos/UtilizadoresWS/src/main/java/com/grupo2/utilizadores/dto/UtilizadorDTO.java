package com.grupo2.utilizadores.dto;

import com.grupo2.utilizadores.model.Utilizador;

public class UtilizadorDTO
{
    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private Utilizador.TipoUtilizador tipoUtilizador;

    public UtilizadorDTO()
    {
    }

    public UtilizadorDTO(long id, String nome, String sobrenome, String email, Utilizador.TipoUtilizador tipoUtilizador)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
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

    public Utilizador.TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(Utilizador.TipoUtilizador tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
    }
}
